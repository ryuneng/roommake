package com.roommake.admin.management.controller;

import com.roommake.admin.management.dto.NoticeForm;
import com.roommake.admin.management.service.NoticeService;
import com.roommake.admin.management.vo.Notice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/management/notice")
@RequiredArgsConstructor
@Tag(name = "공지사항 API", description = "공지사항 CRUD API를 제공한다.")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록한다.")
    @PostMapping("/create")
    @ResponseBody
    public String create(@Valid NoticeForm form, Principal principal) {

        noticeService.createNotice(form, principal.getName());

        return "redirect:/admin/management/notice";
    }

    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다.")
    @PostMapping("/modify/{id}")
    @ResponseBody
    public Notice modify(@PathVariable("id") int id, NoticeForm form, Principal principal) {

        return noticeService.modifyNotice(id, form, principal.getName());
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제한다.")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody List<Integer> noticeIds) {
        for (Integer noticeId : noticeIds) {
            Notice notice = noticeService.getNoticeById(noticeId);
            noticeService.deleteNotice(notice.getId());
        }
        return "redirect:/admin/management/notice";
    }

    @Operation(summary = "공지사항 상세조회", description = "공지사항 상세정보를 제공한다.")
    @GetMapping("/detail/{id}")
    @ResponseBody
    public Notice detail(@PathVariable("id") int id) {
        return noticeService.getNoticeById(id);
    }
}
