package com.roommake.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductReviewForm {

    private int userid;
    private int orderItemId;
    private String content;
    private int reviewStar;
    private MultipartFile reviewPhoto;
}
