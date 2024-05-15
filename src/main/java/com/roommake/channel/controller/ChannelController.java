package com.roommake.channel.controller;

import com.roommake.channel.dto.ChannelForm;
import com.roommake.channel.dto.ChannelInfoDto;
import com.roommake.channel.service.ChannelService;
import com.roommake.channel.vo.Channel;
import com.roommake.dto.Criteria;
import com.roommake.dto.ListDto;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import com.roommake.user.service.UserService;
import com.roommake.user.vo.User;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/channel")
@Tag(name = "채널 API", description = "채널정보 추가,변경,삭제,조회 API를 제공한다.")
public class ChannelController {

    private final ChannelService channelService;
    private final UserService userService;
    private final S3Uploader s3Uploader;

    @Operation(summary = "전체 채널 조회", description = "전체 채널정보를 조회한다.")
    @GetMapping("/list")
    public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "rows", required = false, defaultValue = "8") int rows,
                       @RequestParam(name = "sort", required = false, defaultValue = "all") String sort,
                       @RequestParam(name = "opt", required = false) String opt,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       Model model, Principal principal) {
        String email = principal != null ? principal.getName() : null;

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);
        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        if (email != null) {
            User user = userService.getUserByEmail(email);
            List<Channel> participationChannelList = channelService.getChannelsByUserId(user.getId());
            model.addAttribute("participationChannelList", participationChannelList);
        }

        ListDto<ChannelInfoDto> dto = channelService.getAllChannels(criteria);
        model.addAttribute("channelList", dto.getItems());
        model.addAttribute("paging", dto.getPaging());

        return "channel/list";
    }

    @Operation(summary = "채널 등록폼", description = "채널 등록폼를 조회한다.")
    @GetMapping(path = "/create")
    @PreAuthorize("isAuthenticated()")
    public String createForm(Model model) {
        model.addAttribute("channelForm", new ChannelForm());

        return "channel/form";
    }

    @Operation(summary = "채널 추가", description = "새로운 채널을 추가한다.")
    @PostMapping(path = "/create")
    @PreAuthorize("isAuthenticated()")
    public String createChannel(@Valid ChannelForm channelForm, BindingResult errors, @Login LoginUser loginUser) {
        if (errors.hasErrors()) {
            return "channel/form";
        }
        String imageName = s3Uploader.saveFile(channelForm.getImageFile());
        if ("default".equals(imageName)) {
            imageName = "https://roommake.s3.ap-northeast-2.amazonaws.com/0545826f-b85e-4afb-9ccc-d0c433932d32.jpg";
        }
        channelService.createChannel(channelForm, imageName, loginUser.getId());

        return "redirect:/channel/list";
    }

    @Operation(summary = "채널 수정폼", description = "채널 수정폼를 조회한다.")
    @GetMapping(path = "/modify/{channelId}")
    @PreAuthorize("isAuthenticated()")
    public String modifyForm(@PathVariable("channelId") int channelId, @Login LoginUser loginUser, Model model) {
        Channel channel = channelService.getChannelByChannelId(channelId);
        if (channel.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 채널은 수정할 수 없습니다.");
        }
        ChannelForm channelForm = new ChannelForm();
        channelForm.setTitle(channel.getTitle());
        channelForm.setDescription(channel.getDescription());

        model.addAttribute("imageName", channel.getImageName());
        model.addAttribute("channelForm", channelForm);
        model.addAttribute("channelId", channelId);

        return "channel/form";
    }

    @Operation(summary = "채널 수정", description = "채널 정보를 수정한다.")
    @PostMapping(path = "/modify/{channelId}")
    @PreAuthorize("isAuthenticated()")
    public String modifyChannel(@PathVariable("channelId") int channelId, @Valid ChannelForm channelForm, BindingResult errors, @Login LoginUser loginUser) {
        if (errors.hasErrors()) {
            return "channel/form";
        }
        Channel channel = channelService.getChannelByChannelId(channelId);
        if (channel.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 채널은 수정할 수 없습니다.");
        }
        String imageName = channel.getImageName();
        if (!channelForm.getImageFile().isEmpty()) {
            imageName = s3Uploader.saveFile(channelForm.getImageFile());
        }
        channelService.modifyChannel(channelForm, imageName, channel);

        return "redirect:/channel/post/list/{channelId}";
    }

    @Operation(summary = "채널 삭제", description = "채널을 삭제한다.")
    @GetMapping(path = "/delete/{channelId}")
    @PreAuthorize("isAuthenticated()")
    public String deleteChannel(@PathVariable("channelId") int channelId, @Login LoginUser loginUser) {
        Channel channel = channelService.getChannelByChannelId(channelId);
        if (channel.getUser().getId() != loginUser.getId()) {
            throw new RuntimeException("다른 사용자의 채널은 삭제할 수 없습니다.");
        }
        channelService.deleteChannel(channel);

        return "redirect:/channel/list";
    }

    @Operation(summary = "채널 참여", description = "채널 참여자를 추가한다.")
    @PostMapping("/addUser")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> createParticipant(@RequestParam("channelId") int channelId, @Login LoginUser loginUser) {
        channelService.createChannelParticipant(channelId, loginUser.getId());

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "채널 참여취소", description = "채널 참여자를 삭제한다.")
    @GetMapping("/deleteUser")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteParticipant(@RequestParam("channelId") int channelId, @Login LoginUser loginUser) {
        channelService.deleteChannelParticipant(channelId, loginUser.getId());

        return ResponseEntity.ok().build();
    }
}
