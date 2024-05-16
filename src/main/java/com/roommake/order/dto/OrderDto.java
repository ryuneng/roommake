package com.roommake.order.dto;

import com.roommake.order.vo.Delivery;
import com.roommake.order.vo.Payment;
import com.roommake.product.vo.ProductDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderDto {

    private int orderId;                 // 주문 번호
    private Date createDate;             // 주문 생성일자
    private Date updateDate;             // 주문 수정일자
    private int totalPrice;              // 총 주문금액
    private int paymentPrice;            // 결제금액
    private int usePoint;                // 사용 포인트
    private int statusId;                // 주문상태 번호
    private String statusName;           // 주문상태 이름
    private String deliveryMemo;         // 배송메모
    private String invoiceNumber;        // 송장번호
    private String imageName;            // 상품 이미지명
    private Payment payment;             // 결제
    private Delivery delivery;           // 배송지
    private OrderItemDto item;           // 상품정보, 상품상세정보가 담긴 객체 단품 (부분취소, 반품, 교환 시 사용)
    private List<OrderItemDto> items;    // 상품정보, 상품상세정보가 담긴 객체 리스트
    private List<ProductDetail> details; // 교환 옵션변경에서 조회할 상품상세 객체 리스트
    private ProductDetail detail;        // 교환에서 조회할 상품상세 객체
}
