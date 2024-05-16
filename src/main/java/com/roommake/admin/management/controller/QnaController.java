package com.roommake.admin.management.controller;

import com.roommake.admin.management.service.QnaService;
import com.roommake.admin.management.vo.Qna;
import com.roommake.dto.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin/management/qna")
@RequiredArgsConstructor
@Tag(name = "문의사항 API", description = "문의사항에 관련된 CRUD를 제공한다.")
public class QnaController {

    private final QnaService qnaService;

    @GetMapping("/detail/{id}")
    @ResponseBody
    public Qna detail(@PathVariable("id") int id) {
        Qna qna = qnaService.getQnaById(id);
        return qna;
    }

    @PostMapping("/updateAnswer")
    public String updateAnswer(@RequestParam("answer") String answer,
                               @RequestParam("noAnswerQna") int id,
                               Principal principal,
                               RedirectAttributes redirectAttributes) throws Exception {
        qnaService.updateAnswer(id, answer, principal.getName());

        redirectAttributes.addFlashAttribute("message", new Message("문의사항 답변이 등록되었습니다."));

        return "redirect:/admin/management/qna";
    }
}
