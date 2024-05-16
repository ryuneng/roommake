package com.roommake.order.vo;

import com.roommake.user.vo.User;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Delivery {

    private int id;           // 배송지 번호
    private User user;        // 유저 번호
    private String name;      // 배송지 이름
    private String recipient; // 수령인
    private String phone;     // 연락처
    private String address1;  // 주소
    private String address2;  // 상세주소
    private String zipcode;   // 우편번호
    private String defaultYn; // 기본배송지 여부
    private String deleteYn;  // 배송지 삭제 여부

    public void toDelivery(int userId) {
        User user = new User();
        user.setId(userId);

        this.user = user;
    }
}
