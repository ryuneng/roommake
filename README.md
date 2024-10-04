# <img width="110px" src="https://github.com/ryuneng/roommake/assets/137076160/11c55ca0-27ef-4b7e-8fd6-c0626dc81d49"> &nbsp;ROOMMAKE

## 🔍 목차
1. [서비스 소개](#-서비스-소개)
2. [R&R 및 담당 기능](#-rr)
3. [프로젝트 환경](#%EF%B8%8F-프로젝트-환경)
4. [ERD](#%EF%B8%8F-erd)
5. [컨벤션 전략](#-컨벤션-전략)
6. [Commit과 PR 관리](#-commit과-pr-관리)
7. [고민한 흔적](#-고민한-흔적)

<br>

## 📋 서비스 소개
> 라이프스타일 트렌드가 변화함에 따라 개인마다의 공간에 대한 관심이 높아지고 있습니다. <br>
룸메이크는 **인테리어 관련 커머스와 커뮤니티를 결합한 플랫폼 서비스**로, <br>
마음에 드는 상품을 바로 구매할 수 있는 커머스 기능과 함께 <br>
비슷한 관심과 취향을 가진 사람들과 소통할 수 있는 커뮤니티를 제공합니다.

<br>

<img src="https://github.com/ryuneng/roommake/assets/137076160/a95b74a0-c25a-467e-82f7-f12b03a4ddca">

<br>
<br>
<br>

## 🧑🏻‍💻 R&R
<table>
  <tr>
    <td>곽유진</td>
    <td>관리자센터, 대시보드, 고객센터, 유저 문의 및 포인트내역</td>
  </tr>
  <tr>
    <td>김다영</td>
    <td>커뮤니티 집들이 및 노하우, 채널, 이벤트</td>
  </tr>
  <tr>
    <td>남현지</td>
    <td>로그인, 회원가입, 마이페이지 프로필 및 설정</td>
  </tr>
  <tr>
    <td>송종석</td>
    <td>관리자 상품, 판매자 주문 및 반품/교환, 배송관리</td>
  </tr>
  <tr>
    <td><b>유리빛나</b></td>
    <td><b>주문결제, 장바구니, 주문취소, 반품교환, 주문내역, 메인 홈</b></td>
  </tr>
  <tr>
    <td>홍원표</td>
    <td>상품 리스트, 상품 상세, 상품 리뷰 및 문의</td>
  </tr>
</table>

### 담당 기능
1. 주문/결제 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/order/service/OrderService.java">주요 소스 보기</a> `(카카오페이 Open API 사용)`
2. 장바구니 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/cart/controller/CartController.java">주요 소스 보기</a>
3. 주문취소/반품/교환 신청 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/order/controller/OrderClaimController.java">주요 소스 보기</a>
4. 배송지 관리 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/order/controller/DeliveryController.java">주요 소스 보기</a>
5. 주문내역 및 주문상세 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/order/service/MyOrderService.java">주요 소스 보기</a>
6. 메인화면 - <a href="https://github.com/ryuneng/roommake/blob/portfolio/src/main/java/com/roommake/home/service/HomeService.java">주요 소스 보기</a>

<br>

## 🛠️ 프로젝트 환경
### Front
<div>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=white"/>
  <img src="https://img.shields.io/badge/HTML-E34F26?style=flat-square&logo=html5&logoColor=white"/>
  <img src="https://img.shields.io/badge/CSS-1572B6?style=flat-square&logo=css3&logoColor=white"/>
  <img src="https://img.shields.io/badge/BootStrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white"/>
<div>

### Back
<div>
  <img src="https://img.shields.io/badge/Java-000000?style=flat-square&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
</div>

### DataBase
<div>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white"/>
</div>

### etc
</div>
  <img src="https://img.shields.io/badge/IntelliJ-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/>
  <img src="https://img.shields.io/badge/AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/>
  <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=Github&logoColor=white"/>
</div>

<br>
<br>

## ⛓️ ERD
<details>
  <summary><b>주문 관련 ERD (21개 엔티티)</b></summary>
  <a href="https://www.erdcloud.com/d/RfPs7YXxK9vAcdAzT">🔗 ERD CLOUD 이동</a><br>
  <img src="https://github.com/user-attachments/assets/38c54d4a-ca85-45a3-ab64-7162dcfcd430">
</details>
<details>
  <summary><b>전체 ERD (63개 엔티티)</b></summary><br>
  <img src="https://github.com/user-attachments/assets/64480063-2468-429c-b012-77a94a246baf">
</details>

<br>
<br>

## 🚩 컨벤션 전략
**1. 브랜치 전략**
| Commit Type | Description                      |
|:-----------:|:--------------------------------:|
| Feat        | 새로운 기능 추가                  |
| Fix         | 오류 및 문제 해결                 |
| Refactor    | 코드 리팩토링                     |
| Style       | 코드 스타일 변경                  |
| Chore       | 빌드 업무 수정, 패키지 매니저 수정 |
| Docs        | 문서 수정, 주석 추가              |

<br>

**2. 코딩 컨벤션**
<details>
  <summary>코드 작성 규칙 (메소드명)</summary><br>
  <img src="https://github.com/user-attachments/assets/6991450a-08f8-4f1f-9c2d-3d2a2dca9614">
</details>
<details>
  <summary>Java</summary><br>
  <img src="https://github.com/user-attachments/assets/035c8e40-d51b-447a-bad4-6fc4ec8f5664">
</details>
<details>
  <summary>Database</summary><br>
  <img src="https://github.com/user-attachments/assets/7f6a7353-20f2-4638-be74-c848f2f74f71">
</details>

<br>

## ✅ Commit과 PR 관리
<details>
  <summary><b>Commits</b></summary><br>
  <img src="https://github.com/user-attachments/assets/bfe890d4-3608-427f-b670-18412ed552c6">
</details>
<details>
  <summary><b>Pull requests</b></summary><br>
  <img src="https://github.com/user-attachments/assets/3c6134df-eb2c-4a6e-9496-437f7e77913e">
</details>

<br>

## 🤔 고민한 흔적
- 복잡한 주문/결제의 CRUD 프로세스를 효율적으로 파악하는 방법 - <a href="https://github.com/ryuneng/roommake/wiki/%EB%B3%B5%EC%9E%A1%ED%95%9C-%EC%A3%BC%EB%AC%B8-%EA%B2%B0%EC%A0%9C%EC%9D%98-CRUD-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4%EB%A5%BC-%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9C%BC%EB%A1%9C-%ED%8C%8C%EC%95%85%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95"> WIKI 이동 </a>
- 카카오페이 Open API 구현 과정을 효과적으로 익히는 방법 - <a href="https://github.com/ryuneng/roommake/wiki/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4-Open-API-%EA%B5%AC%ED%98%84-%EA%B3%BC%EC%A0%95%EC%9D%84-%ED%9A%A8%EA%B3%BC%EC%A0%81%EC%9C%BC%EB%A1%9C-%EC%9D%B5%ED%9E%88%EB%8A%94-%EB%B0%A9%EB%B2%95"> WIKI 이동 </a>
