package com.roommake.order.controller;

import com.roommake.order.dto.DeliveryForm;
import com.roommake.order.service.DeliveryService;
import com.roommake.order.vo.Delivery;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order/delivery")
@Tag(name = "배송지 API", description = "배송지에 대한 추가, 변경, 삭제, 조회 API를 제공한다.")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 리스트 팝업", description = "배송지 리스트를 조회한다.")
    @GetMapping("/list/{type}")
    public String deliveryList(@Login LoginUser loginUser, @PathVariable("type") String type, Model model) {
        List<Delivery> deliveries = deliveryService.getDeliveriesByUserId(loginUser.getId());
        model.addAttribute("deliveries", deliveries);
        model.addAttribute("type", type); // 회수지/배송지 구분 타입

        return "order/deliverylist";
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 추가 폼 팝업", description = "배송지 추가 폼을 조회한다.")
    @GetMapping("/create/{type}")
    public String createDelivery(@PathVariable("type") String type, Model model) {
        model.addAttribute("deliveryForm", new DeliveryForm());
        model.addAttribute("type", type); // 회수지/배송지 구분 타입

        return "order/deliveryform";
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 추가", description = "배송지 추가 후 배송지 리스트 페이지로 이동한다.")
    @PostMapping("/create/{type}")
    public String createDelivery(@PathVariable("type") String type, @Login LoginUser loginUser, @Valid DeliveryForm deliveryForm, BindingResult errors) {
        if (errors.hasErrors()) {
            return "order/deliveryform";
        }

        deliveryService.createDelivery(deliveryForm, loginUser.getId());
        return "redirect:/order/delivery/list/" + type;
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 삭제", description = "배송지를 삭제한다.")
    @GetMapping("/delete/{type}/{id}")
    public String deleteDelivery(@PathVariable("type") String type, @Login LoginUser loginUser, @PathVariable("id") int id) {

        deliveryService.deleteDelivery(id);

        return "redirect:/order/delivery/list/" + type;
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 수정 폼 팝업", description = "배송지 수정 폼을 조회한다.")
    @GetMapping("/modify/{type}/{id}")
    public String modifyDelivery(@PathVariable("type") String type, @Login LoginUser loginUser, @PathVariable("id") int id, Model model) {

        Delivery delivery = deliveryService.getDeliveryById(id);

        DeliveryForm deliveryForm = new DeliveryForm();
        deliveryForm.setName(delivery.getName());
        deliveryForm.setRecipient(delivery.getRecipient());
        deliveryForm.setPhone(delivery.getPhone());
        deliveryForm.setAddress1(delivery.getAddress1());
        deliveryForm.setAddress2(delivery.getAddress2());
        deliveryForm.setZipcode(delivery.getZipcode());
        deliveryForm.setDefaultYn(delivery.getDefaultYn());

        model.addAttribute("deliveryForm", deliveryForm);

        return "order/deliveryform";
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "배송지 수정", description = "배송지 수정 후 배송지 리스트 페이지로 이동한다.")
    @PostMapping("/modify/{type}/{id}")
    public String modifyDelivery(@PathVariable("type") String type,
                                 @Login LoginUser loginUser,
                                 @PathVariable("id") int id,
                                 @Valid DeliveryForm deliveryForm,
                                 BindingResult errors) {

        if (errors.hasErrors()) {
            return "order/deliveryform";
        }

        deliveryService.modifyDelivery(id, deliveryForm);

        return "redirect:/order/delivery/list/" + type;
    }
}
