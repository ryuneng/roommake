package com.roommake.order.controller;

import com.roommake.dto.ListDto;
import com.roommake.order.dto.MyOrderCriteria;
import com.roommake.order.dto.OrderListDto;
import com.roommake.order.dto.UserOrderInfoDto;
import com.roommake.order.service.MyOrderService;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/myorder")
public class MyOrderController {

    private final MyOrderService myOrderService;

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "주문내역 조회", description = "로그인한 유저의 주문내역을 조회한다.")
    @GetMapping()
    public String myorder(@RequestParam(name = "beginDate", required = false) String beginDate,
                          @RequestParam(name = "endDate", required = false) String endDate,
                          @RequestParam(name = "statusId", required = false, defaultValue = "") String statusId,
                          @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(name = "rows", required = false, defaultValue = "10") int rows,
                          @Login LoginUser loginUser, Model model) {

        MyOrderCriteria criteria = new MyOrderCriteria();
        criteria.setPage(page);
        criteria.setRows(rows);
        criteria.setBeginDate(beginDate);
        criteria.setEndDate(endDate);
        if (StringUtils.hasText(statusId)) {
            criteria.setStatusId(statusId);
        }

        ListDto<OrderListDto> dto = myOrderService.getAllOrdersByUserId(loginUser.getId(), criteria);
        UserOrderInfoDto orderInfo = myOrderService.getUserOrderInfo(loginUser.getId());

        model.addAttribute("orderList", dto.getItems());
        model.addAttribute("criteria", criteria);
        model.addAttribute("paging", dto.getPaging());
        model.addAttribute("orderInfo", orderInfo);

        return "user/mypage-order";
    }
}
