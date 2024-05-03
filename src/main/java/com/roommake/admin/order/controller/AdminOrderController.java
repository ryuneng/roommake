package com.roommake.admin.order.controller;

import com.roommake.admin.order.service.AdminOrderService;
import com.roommake.order.vo.Exchange;
import com.roommake.order.vo.OrderCancel;
import com.roommake.order.vo.Refund;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {
    private final AdminOrderService adminOrderService;

    //교환페이지
    @GetMapping("/exchange")
    public String exchange(Model model) {
        List<Exchange> exchanges = adminOrderService.getAllExchanges();
        model.addAttribute("exchanges", exchanges);
        return "admin/order/exchange";
    }

    //교환 상세페이지
    @GetMapping("/exchange-detail")
    public String exchangedetail() {
        return "admin/order/exchange-detail";
    }

    @PostMapping("/exchange")
    public String updateExchange(Model model) {
        int id = (int) model.getAttribute("id");
        adminOrderService.updateExchange(id);
        return "redirect:admin/order/exchange";
    }

    // 환불리스트
    @GetMapping("/refund")
    public String refund(Model model) {
        List<Refund> refund = adminOrderService.getAllRefund();
        model.addAttribute("refund", refund);
        return "admin/order/refund";
    }

    //반품페이지
    @GetMapping("/return")
    public String returnpage(Model model) {

        return "admin/order/return";
    }

    // 취소 페이지
    @GetMapping("/orderCancel")
    public String channel(Model model) {
        List<OrderCancel> OrderCancels = adminOrderService.getAllorderCancel();
        model.addAttribute("OrderCancel", OrderCancels);
        return "admin/order/orderCancel";
    }
}
