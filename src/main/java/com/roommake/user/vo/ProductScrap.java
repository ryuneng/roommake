package com.roommake.user.vo;

import com.roommake.product.vo.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ProductScrap {

    private Product productId;          // 상품 번호
    private User userId;                // 유저 번호
    private Date createDate;            // 상품 스크랩 최초 생성일
    private Date updateDate;            // 상품 스크랩 수정일
    private Date deleteDate;            // 상품 스크랩 삭제일
    private String deleteYn;            // 상품 삭제여부
    private ScrapFolder scrapFolderId;  // 스크랩 폴더 번호
}
