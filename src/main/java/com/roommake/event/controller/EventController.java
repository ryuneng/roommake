package com.roommake.event.controller;

import com.roommake.admin.management.vo.Banner;
import com.roommake.dto.Criteria;
import com.roommake.event.service.EventService;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
@Tag(name = "이벤트 API", description = "이벤트 조회, 출석체크 API를 제공한다.")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "전체 이벤트 조회", description = "전체 이벤트 정보를 조회한다.")
    @GetMapping("/list")
    public String banner(@RequestParam(name = "filter", required = false, defaultValue = "all") String filter,
                         Model model) {
        Criteria criteria = new Criteria();
        criteria.setFilt(filter);
        List<Banner> bannerList = eventService.getBanners(criteria);
        model.addAttribute("bannerList", bannerList);
        model.addAttribute("criteria", criteria);

        return "event/list";
    }

    @Operation(summary = "출석체크 이벤트 조회", description = "출석체크 이벤트를 조회한다.")
    @GetMapping("/dailyCheck")
    public String dailycheck() {
        return "event/dailycheck";
    }

    @Operation(summary = "출석체크", description = "출석체크 이벤트 포인트를 적립한다.")
    @PostMapping("/attendDailyCheck")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public String attendDailyCheck(HttpSession session, @Login LoginUser loginUser) {
        // 세션에서 출석체크 여부 확인
        Boolean attended = (Boolean) session.getAttribute("attended");
        if (attended == null || !attended) {
            // 출석체크 처리
            session.setAttribute("attended", true);
            // 포인트 적립
            String message = eventService.addDailyCheckPoint(loginUser.getId());
            return message;
        } else {
            return "이미 출석체크를 하셨습니다.";
        }
    }
}

