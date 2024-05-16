package com.roommake.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeliveryForm {

    @NotBlank(message = "배송지명을 입력해주세요.")
    @Size(max = 20, message = "배송지명은 최대 20글자까지 가능합니다.")
    private String name;      // 배송지 이름

    @NotBlank(message = "수령인을 입력해주세요.")
    private String recipient; // 수령인

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "유효한 전화번호 형식이 아닙니다.")
    private String phone;     // 연락처

    @NotBlank(message = "주소를 입력해주세요.")
    private String address1;  // 주소

    @NotBlank(message = "상세주소를 입력해주세요.")
    private String address2;  // 상세주소

    @NotBlank(message = "주소를 입력해주세요.")
    private String zipcode;   // 우편번호

    private String defaultYn; // 기본배송지 여부
}
