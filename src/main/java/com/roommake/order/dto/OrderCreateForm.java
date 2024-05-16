package com.roommake.order.dto;

import com.roommake.cart.dto.CartCreateForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreateForm {

    private String name;         // 카카오페이에 보낼 대표 상품명
    private int totalPrice;      // 총 결제금액
    private int usePoint;        // 사용 포인트
    private int deliveryId;      // 배송지 번호
    private String deliveryMemo; // 배송메모
    List<CartCreateForm> items;  // 상품번호, 상품상세번호, 장바구니번호, 상품수량이 담긴 객체 리스트
}
