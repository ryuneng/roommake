package com.roommake.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 입력 폼
 */
@Getter
@Setter
public class UserSignupForm {

    private String email1;
    private String email2;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 최소 8글자 이상~16자 이하만 가능합니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력값입니다.")
    private String confirmPassword;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 최소 2글자 이상, 최대 20글자 이하만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,20}$", message = "닉네임은 특수문자를 제외한 2~20자리여야 합니다.")
    private String nickname;

    @Size(max = 8, message = "추천코드는 최대 8글자까지 입력 가능합니다.")
    @Pattern(regexp = "^[A-Z0-9]*$", message = "추천코드는 대문자와 숫자로만 구성되어야 합니다.")
    private String optionRecommendCode;

    private String termAgreements1;
    private String termAgreements2;
    private String termAgreements3;
}
