package com.roommake.order.vo;

import com.roommake.product.vo.ProductDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExchangeDetail {

    private int id;                               // 교환상세번호
    private Exchange exchange;                    // 교환
    private ProductDetail BeforeProductDetail;    // 교환 전 상품상세
    private ProductDetail AfterProductDetail;     // 교환 후 상품상세
}
