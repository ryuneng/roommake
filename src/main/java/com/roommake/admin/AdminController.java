package com.roommake.admin;

import com.roommake.admin.Dashboard.dto.DashboardDto;
import com.roommake.admin.Dashboard.dto.OrderStatusData;
import com.roommake.admin.Dashboard.service.DashBoardService;
import com.roommake.admin.Dashboard.vo.SalesData;
import com.roommake.admin.order.service.AdminOrderService;
import com.roommake.admin.product.service.AdminProductService;
import com.roommake.order.vo.Order;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "관리자 API", description = "관리자 세부 페이지로 가는 CRUD API를 제공한다.")
public class AdminController {
    private final AdminProductService adminProductService;
    private final DashBoardService dashBoardService;
    private final AdminOrderService adminOrderService;

    @GetMapping("/home")
    public String adminHome(Model model) {

        DashboardDto dto = dashBoardService.getAdminHomeDto();

        model.addAttribute("yesterDay", LocalDate.now().minusDays(1));
        model.addAttribute("orderStatusDataList", dto.getOrderStatusDataList());
        model.addAttribute("salesDataList", dto.getSalesDataList());
        model.addAttribute("noAnswerQnas", dto.getNoAnswerQnas());
        model.addAttribute("newUserCnt", dto.getNewUserCnt());
        model.addAttribute("totalUserCnt", dto.getTotalUserCnt());

        return "admin/home";
    }

    @ResponseBody
    @GetMapping("/salesData")
    public List<SalesData> salesData() {

        return dashBoardService.getSalesData(LocalDate.now().minusDays(1).toString(), 7);
    }

    @ResponseBody
    @GetMapping("/orderStatusData")
    public List<OrderStatusData> orderStatusData() {

        return dashBoardService.getOrderStatusData(LocalDate.now().minusDays(1).toString());
    }

    @GetMapping("/management")
    public String notice() {
        return "admin/management/notice";
    }

    @GetMapping("/sales")
    public String sales() {
        return "admin/sales/sales";
    }

    //주문내역 리스트
    @GetMapping("/order/item")
    public String order(Model model) {
        List<Order> orders = adminOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/item";
    }

    @GetMapping("/user")
    public String user() {
        return "admin/user/user-list";
    }

    @GetMapping("/community")
    public String community() {
        return "admin/community/community";
    }

    @PostMapping("/order/item")
    @ResponseBody
    public int orderItem(Model model, @RequestBody Order order) {

        System.out.println("test code orderId : " + order.toString());

        // 송장번호 랜덤번호
        // Random random = new Random();
        // String postNum = (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100);
        // System.out.println(postNum);

        int result = adminOrderService.updateOrderStatus(order);

        return result;
    }
}
