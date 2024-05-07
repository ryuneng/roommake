package com.roommake.community.controller;

import com.roommake.community.dto.CommCriteria;
import com.roommake.community.dto.CommDetailDto;
import com.roommake.community.dto.CommReplyForm;
import com.roommake.community.dto.CommunityForm;
import com.roommake.community.service.CommunityService;
import com.roommake.community.vo.Community;
import com.roommake.community.vo.CommunityCategory;
import com.roommake.community.vo.CommunityReply;
import com.roommake.dto.ListDto;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import com.roommake.utils.S3Uploader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Tag(name = "커뮤니티 API", description = "커뮤니티 추가,변경,삭제,조회 API를 제공한다.")
public class CommunityController {

    private final CommunityService communityService;
    private final S3Uploader s3Uploader;

    @Operation(summary = "이미지 업로드", description = "이미지를 서버, S3에 저장한다.")
    @PostMapping("/image/upload")
    @ResponseBody
    public Map<String, Object> imageUpload(MultipartRequest request) {

        Map<String, Object> responseData = new HashMap<>();

        try {
            String s3Url = communityService.imageUpload(request);
            responseData.put("uploaded", true);
            responseData.put("url", s3Url);
            return responseData;
        } catch (IOException e) {
            responseData.put("uploaded", false);
            return responseData;
        }
    }

    @Operation(summary = "커뮤니티 목록", description = "커뮤니티 목록을 조회한다.")
    @GetMapping("/list/{commCatId}")
    public String houseList(@PathVariable("commCatId") int commCatId,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                            @RequestParam(name = "sort", required = false, defaultValue = "all") String sort,
                            @RequestParam(name = "opt", required = false) String opt,
                            @RequestParam(name = "keyword", required = false) String keyword,
                            Model model) {
        CommCriteria criteria = new CommCriteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);
        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        ListDto<Community> commListDto = communityService.getAllCommunitiesByCatId(commCatId, criteria);
        model.addAttribute("communityList", commListDto.getItems());
        model.addAttribute("paging", commListDto.getPaging());
        model.addAttribute("commCatId", commCatId);

        return "/community/list";
    }

    @Operation(summary = "커뮤니티글 등록폼", description = "커뮤니티글 등록폼을 조회한다.")
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model) {
        List<CommunityCategory> commCatList = communityService.getAllCommCategories();
        model.addAttribute("communityForm", new CommunityForm());
        model.addAttribute("commCatList", commCatList);
        return "community/form";
    }

    @Operation(summary = "커뮤니티글 등록", description = "커뮤니티글 등록 후 커뮤니티 리스트로 이동한다.")
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createCommunity(@Valid CommunityForm communityForm, BindingResult errors, @Login LoginUser loginUser) {
        if (errors.hasErrors()) {
            return "community/form";
        }
        String s3Url = s3Uploader.saveFile(communityForm.getImageFile());
        communityService.createCommunity(communityForm, s3Url, loginUser.getId());

        return String.format("redirect:/community/list/%d", communityForm.getCategoryId());
    }

    @Operation(summary = "커뮤니티글 상세", description = "커뮤니티글 상세내용을 조회한다.")
    @GetMapping("/detail/{commId}")
    public String getCommunityDetail(@PathVariable("commId") int commId,
                                     @RequestParam(name = "page", required = false, defaultValue = "1") int replyCurrentPage,
                                     Model model, Principal principal) {
        String email = principal != null ? principal.getName() : null;

        CommDetailDto commDto = communityService.getCommunityDetail(commId, email, replyCurrentPage);
        model.addAttribute("complaintCategories", commDto.getComplaintCategories());
        model.addAttribute("community", commDto.getCommunity());
        model.addAttribute("commLike", commDto.isLike());
        model.addAttribute("commScrap", commDto.isScrap());
        model.addAttribute("totalReplyCount", commDto.getTotalReplyCount());
        model.addAttribute("communityReplies", commDto.getCommunityReplies());
        model.addAttribute("replyPaging", commDto.getReplyPagination());
        model.addAttribute("recommendCommunities", commDto.getRecommendCommunities());

        return "community/detail";
    }

    @Operation(summary = "커뮤니티글 수정폼", description = "커뮤니티글 수정폼를 조회한다.")
    @GetMapping(path = "/modify/{commId}")
    @PreAuthorize("isAuthenticated()")
    public String modifyForm(@PathVariable("commId") int commId, Model model, @Login LoginUser loginUser) {
        Community community = communityService.getCommunityByCommId(commId);
        if (community.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 글은 수정할 수 없습니다.");
        }
        List<CommunityCategory> commCatList = communityService.getAllCommCategories();
        CommunityForm communityForm = CommunityForm.builder()
                .title(community.getTitle())
                .categoryId(community.getCategory().getId())
                .content(community.getContent())
                .build();
        String imageName = community.getImageName();
        model.addAttribute("commCatList", commCatList);
        model.addAttribute("communityForm", communityForm);
        model.addAttribute("imageName", imageName);
        model.addAttribute("communityId", commId);

        return "community/form";
    }

    @Operation(summary = "커뮤니티글 수정", description = "커뮤니티글을 수정한다.")
    @PostMapping(path = "/modify/{commId}")
    @PreAuthorize("isAuthenticated()")
    public String modifyCommunity(@PathVariable("commId") int commId, @Valid CommunityForm communityForm, BindingResult errors, @Login LoginUser loginUser) {
        if (errors.hasErrors()) {
            return "community/form";
        }
        Community community = communityService.getCommunityByCommId(commId);
        if (community.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 글은 수정할 수 없습니다.");
        }
        String image = community.getImageName();
        if (communityForm.getImageFile() != null) {
            image = s3Uploader.saveFile(communityForm.getImageFile());
        }
        communityService.modifyCommunity(communityForm, image, community);

        return String.format("redirect:/community/detail/%d", commId);
    }

    @Operation(summary = "커뮤니티글 삭제", description = "커뮤니티글을 삭제한다.")
    @GetMapping(path = "/delete/{commId}")
    @PreAuthorize("isAuthenticated()")
    public String deleteCommunity(@PathVariable("commId") int commId, @Login LoginUser loginUser) {
        Community community = communityService.getCommunityByCommId(commId);
        if (community.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 글은 삭제할 수 없습니다.");
        }
        communityService.deleteCommunity(community);

        return String.format("redirect:/community/list/%d", community.getCategory().getId());
    }

    @Operation(summary = "커뮤니티글 좋아요", description = "커뮤니티글 좋아요를 추가한다.")
    @PostMapping(path = "/addLike")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public int addCommunityLike(@RequestParam("commId") int commId, @Login LoginUser loginUser) {
        int commLikeCount = communityService.addCommunityLike(commId, loginUser.getId());

        return commLikeCount;
    }

    @Operation(summary = "커뮤니티글 좋아요 취소", description = "커뮤니티글 좋아요를 삭제한다.")
    @GetMapping(path = "/deleteLike")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public int deleteCommunityLike(@RequestParam("commId") int commId, @Login LoginUser loginUser) {
        int commLikeCount = communityService.deleteCommunityLike(commId, loginUser.getId());

        return commLikeCount;
    }

    @Operation(summary = "커뮤니티글 신고", description = "커뮤니티글을 신고한다.")
    @PostMapping(path = "/complaint")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> addCommunityComplaint(@RequestParam("communityId") int commId,
                                                      @RequestParam("complaintCatId") int complaintCatId,
                                                      @Login LoginUser loginUser) {
        communityService.createCommunityComplaint(commId, complaintCatId, loginUser.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "커뮤니티글 댓글 신고", description = "커뮤니티글에 있는 댓글을 신고한다.")
    @PostMapping(path = "/reply/complaint")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> addCommunityReplyComplaint(@RequestParam("replyId") int replyId,
                                                           @RequestParam("complaintCatId") int complaintCatId,
                                                           @Login LoginUser loginUser) {
        communityService.createCommunityReplyComplaint(replyId, complaintCatId, loginUser.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "커뮤니티글 댓글 등록", description = "커뮤니티 글에 댓글을 등록한다.")
    @PostMapping(path = "/reply/create/{commId}")
    @PreAuthorize("isAuthenticated()")
    public String createCommunityReply(@PathVariable("commId") int commId, CommReplyForm replyForm, @Login LoginUser loginUser) {
        communityService.createCommunityReply(commId, replyForm, loginUser.getId());

        return String.format("redirect:/community/detail/%d", commId);
    }

    @Operation(summary = "커뮤니티글 댓글 조회", description = "커뮤니티 글의 댓글을 조회한다.")
    @GetMapping(path = "/reply/{replyId}")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public CommunityReply getCommunityReplyByReplyId(@PathVariable("replyId") int replyId) {
        return communityService.getCommunityReplyByReplyId(replyId);
    }

    @Operation(summary = "커뮤니티글 댓글 수정", description = "커뮤니티 글의 댓글을 수정한다.")
    @PostMapping(path = "/reply/modify/{replyId}")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public CommunityReply modifyCommunityReplyByReplyId(@PathVariable("replyId") int replyId,
                                                        @RequestParam("content") String content,
                                                        @Login LoginUser loginUser) {
        CommunityReply communityReply = communityService.getCommunityReplyByReplyId(replyId);
        if (communityReply.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 댓글은 수정할 수 없습니다.");
        }
        return communityService.modifyCommunityReply(communityReply, content);
    }

    @GetMapping("/popup")
    @PreAuthorize("isAuthenticated()")
    public String popup() {
        return "layout/scrap-popup";
    }
}
