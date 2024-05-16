package com.roommake.admin.management.controller;

import com.roommake.admin.management.service.ComplaintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin/management/complaint")
@Controller
@RequiredArgsConstructor
@Tag(name = "신고 API", description = "신고 확정과 신고 취소 API")
public class ComplaintController {

    private final ComplaintService complaintService;

    @Operation(summary = "신고확정 API", description = "신고 id를 받아서 확정한다.")
    @PostMapping("/confirm")
    public String confirm(@RequestBody List<String> complaintList) {
        for (String compalintTypeAndId : complaintList) {
            complaintService.confirmCompalint(compalintTypeAndId);
        }
        return "admin/management/complaint";
    }

    @Operation(summary = "신고 취소 API", description = "신고를 취소한다.")
    @PostMapping("/delete")
    public String delete(@RequestBody List<String> complaintList) {
        for (String compalintTypeAndId : complaintList) {
            complaintService.deleteCompalint(compalintTypeAndId);
        }
        return "admin/management/complaint";
    }
}
