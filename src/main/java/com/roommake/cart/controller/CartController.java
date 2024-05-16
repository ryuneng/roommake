package com.roommake.cart.controller;

import com.roommake.cart.dto.CartItemDto;
import com.roommake.cart.dto.CartListDto;
import com.roommake.cart.service.CartService;
import com.roommake.resolver.Login;
import com.roommake.user.security.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
@Tag(name = "장바구니 API", description = "장바구니에 대한 추가, 변경, 삭제, 조회 API를 제공한다.")
public class CartController {

    private final CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "장바구니", description = "장바구니를 조회한다.")
    @GetMapping()
    public String cart(@Login LoginUser loginUser, Model model) {

        List<CartItemDto> items = cartService.getCartsByUserId(loginUser.getId());
        CartListDto dto = new CartListDto(items);

        model.addAttribute("dto", dto);
        return "cart/cart";
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "장바구니 상품 옵션 조회", description = "모달에서 장바구니 상품의 옵션 정보를 조회한다.")
    @GetMapping("/option/{productId}")
    @ResponseBody
    public List<CartItemDto> getOptions(@PathVariable("productId") int productId, Model model) {

        List<CartItemDto> productDetails = cartService.getItemOptionsByProductId(productId);
        model.addAttribute("details", productDetails);

        return productDetails;
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "장바구니 상품 삭제", description = "장바구니 상품을 삭제한다.")
    @GetMapping("/delete")
    @ResponseBody
    public List<CartItemDto> deleteCart(@RequestParam("cartId") List<Integer> cartIds, @Login LoginUser loginUser) {

        cartService.deleteCart(cartIds);

        return cartService.getCartsByUserId(loginUser.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "장바구니 상품 수량 변경", description = "장바구니 상품의 수량을 변경한다.")
    @GetMapping("/update-amount")
    @ResponseBody
    public List<CartItemDto> updateCartAmount(@RequestParam("cartId") int cartId, @RequestParam("amount") int amount, @Login LoginUser loginUser) {

        cartService.updateCartAmount(cartId, amount);

        return cartService.getCartsByUserId(loginUser.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "장바구니 상품 옵션 변경", description = "장바구니 상품의 옵션을 변경한다.")
    @GetMapping("/update-option")
    @ResponseBody
    public List<CartItemDto> updateCartOption(@RequestParam("cartId") int cartId, @RequestParam("productDetailId") int productDetailId, @Login LoginUser loginUser) {

        cartService.updateCartOption(cartId, productDetailId);

        return cartService.getCartsByUserId(loginUser.getId());
    }
}
