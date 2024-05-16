package com.roommake.admin.management.controller;

import com.roommake.admin.management.dto.ComplaintDto;
import com.roommake.admin.management.service.*;
import com.roommake.admin.management.vo.*;
import com.roommake.dto.Criteria;
import com.roommake.dto.ListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/admin/management")
@Controller
@RequiredArgsConstructor
@Tag(name = "관리 API", description = "관리자 페이지 중 사이트 관리 관련 CRUD API를 제공한다.")
public class ManagementController {

    private final NoticeService noticeService;
    private final BannerService bannerService;
    private final FaqService faqService;
    private final QnaService qnaService;
    private final ComplaintService complaintService;

    @GetMapping("/notice")
    @Operation(summary = "전체 공지사항 조회", description = "전체 공지사항을 조회한다.")
    public String notice(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                         @RequestParam(name = "sort", required = false, defaultValue = "date") String sort,
                         @RequestParam(name = "opt", required = false) String opt,
                         @RequestParam(name = "keyword", required = false) String keyword,
                         Model model) {
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);

        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        ListDto<Notice> dto = noticeService.getNotices(criteria);

        model.addAttribute("noticeList", dto.getItems());
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("criteria", criteria);

        return "admin/management/notice";
    }

    @GetMapping("/faq")
    @Operation(summary = "전체 자주묻는질문 조회", description = "전체 자주묻는질문 조회한다.")
    public String faq(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                      @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                      @RequestParam(name = "sort", required = false, defaultValue = "date") String sort,
                      @RequestParam(name = "filt", required = false, defaultValue = "all") String filt,
                      @RequestParam(name = "opt", required = false) String opt,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      Model model) {

        List<FaqCategory> faqCategories = faqService.getFaqCategories();
        model.addAttribute("faqCategories", faqCategories);

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);
        criteria.setFilt(filt);

        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        ListDto<Faq> dto = faqService.getFaqs(criteria);

        model.addAttribute("faqs", dto.getItems());
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("criteria", criteria);
        return "admin/management/faq";
    }

    @GetMapping("/qna")
    @Operation(summary = "전체 문의사항 조회", description = "전체 문의사항 내역을 조회한다.")
    public String qna(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                      @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                      @RequestParam(name = "sort", required = false, defaultValue = "all") String sort,
                      @RequestParam(name = "filt", required = false, defaultValue = "all") String filt,
                      @RequestParam(name = "opt", required = false) String opt,
                      @RequestParam(name = "keyword", required = false) String keyword,
                      Model model) {
        List<QnaCategory> qnaCategories = qnaService.getQnaCategories();
        model.addAttribute("qnaCategories", qnaCategories);

        Criteria criteria = new Criteria();

        List<Qna> noAnswerQnas = qnaService.getNoAnswerQnas();
        model.addAttribute("noAnswerQnas", noAnswerQnas);

        criteria.setPage(page);
        criteria.setFilt(filt);
        criteria.setRows(rows);
        criteria.setSort(sort);     // 부득이하게 qna의 sort만 답변 여부 상태를 구분하는 용으로 사용한다.

        if (StringUtils.hasText(opt) && StringUtils.hasText(keyword)) {
            criteria.setOpt(opt);
            criteria.setKeyword(keyword);
        }

        ListDto<Qna> dto = qnaService.getQnas(criteria);

        model.addAttribute("qnas", dto.getItems());
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("criteria", criteria);

        return "admin/management/qna";
    }

    @GetMapping("/banner")
    @Operation(summary = "전체 배너 조회", description = "전체 배너 내역을 조회한다.")
    public String banner(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                         @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                         @RequestParam(name = "filt", required = false, defaultValue = "all") String filt,
                         @RequestParam(name = "sort", required = false, defaultValue = "new") String sort,
                         Model model) {
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setSort(sort);
        criteria.setFilt(filt);

        ListDto<Banner> dto = bannerService.getBanners(criteria);

        model.addAttribute("bannerList", dto.getItems());
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("criteria", criteria);

        return "admin/management/banner";
    }

    @GetMapping("/complaint")
    @Operation(summary = "전체 신고 조회", description = "전체 신고 내역을 조회한다.")
    public String complaint(@RequestParam(name = "filt", required = false, defaultValue = "all") String filt,
                            @RequestParam(name = "replyfilt", required = false, defaultValue = "all") String replyfilt,
                            Model model) {
        List<ComplaintDto> boardComplaints = complaintService.getBoardComplaints(filt);
        List<ComplaintDto> replyComplaints = complaintService.getReplyComplaints(replyfilt);

        model.addAttribute("boardComplaints", boardComplaints);
        model.addAttribute("replyComplaints", replyComplaints);
        model.addAttribute("filt", filt);
        model.addAttribute("replyfilt", replyfilt);
        return "admin/management/complaint";
    }
}
