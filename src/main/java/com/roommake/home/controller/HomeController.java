package com.roommake.home.controller;

import com.roommake.admin.Dashboard.service.VisitorService;
import com.roommake.admin.management.vo.Banner;
import com.roommake.community.vo.Community;
import com.roommake.dto.Criteria;
import com.roommake.event.service.EventService;
import com.roommake.home.service.HomeService;
import com.roommake.product.dto.ProductDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final EventService eventService;
    private final VisitorService visitorService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        List<ProductDto> products = homeService.getNewProducts();

        Criteria criteria = new Criteria();
        criteria.setFilt("active");
        List<Banner> banners = eventService.getBanners(criteria);

        List<Community> housePosts = homeService.getCommHousePosts();
        List<Community> knowhowPosts = homeService.getCommKnowhowPosts();

        model.addAttribute("products", products);
        model.addAttribute("banners", banners);
        model.addAttribute("housePosts", housePosts);
        model.addAttribute("knowhowPosts", knowhowPosts);

        // 방문자 DB저장
        visitorService.addVisitor(request);

        return "home";
    }
}
