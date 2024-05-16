package com.roommake.admin.sales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/sales")
@Controller
public class SalesController {

    @GetMapping("/sales")
    public String sales() {
        return "admin/sales/sales";
    }
}
