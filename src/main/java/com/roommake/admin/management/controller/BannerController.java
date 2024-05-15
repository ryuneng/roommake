package com.roommake.admin.management.controller;

import com.roommake.admin.management.dto.BannerForm;
import com.roommake.admin.management.service.BannerService;
import com.roommake.admin.management.vo.Banner;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/management/banner")
@RequiredArgsConstructor
@Tag(name = "배너 API", description = "배너 CRUD API를 제공한다.")
public class BannerController {

    private final BannerService bannerService;

    @Operation(summary = "배너 등록", description = "배너를 등록한다.")
    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public String create(@Login LoginUser loginUser, BannerForm bannerForm) {

        bannerService.createBanner(bannerForm, loginUser.getId());
        return "redirect:/admin/management/banner";
    }

    @Operation(summary = "배너 상세조회", description = "배너의 상세정보를 제공한다.")
    @GetMapping("/detail/{id}")
    @ResponseBody
    public Banner detail(@PathVariable("id") int id) {

        return bannerService.getBannerById(id);
    }

    @Operation(summary = "배너 수정", description = "배너를 수정한다.")
    @PostMapping("/modify/{id}")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Banner modify(@PathVariable("id") int bannerId, @Login LoginUser loginUser, BannerForm bannerForm) {

        return bannerService.modifyBanner(bannerId, bannerForm, loginUser.getId());
    }

    @Operation(summary = "배너 삭제", description = "배너를 삭제한다.")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody List<Integer> bannerIds) {

        for (Integer bannerId : bannerIds) {
            Banner banner = bannerService.getBannerById(bannerId);
            bannerService.deleteBanner(banner.getId());
        }
        return "redirect:/admin/management/notice";
    }
}
