# 유저 서비스
## 1. 유저 종류
- 회원
- 배달원
- 음식점 사장님
- 관리자

유저 종류에 따라 권한이 부여 되며 권한의 우선순위는 관리자가 상위에 있으며, 나머지 3개는 동일한 권한을 갖는다.

관리자 종류도 2 가지로 나뉘며, 

관리자가 하는 일은 다음과 같다.

- 배너 등록
- 이벤트 등록
- 쿠폰 등록
- 가맹점 승인
- 고객 1:1 문의 답변

### 사용자 테이블

|필드명     | 논리명  | 타입  | 비고 |
|--         |--      |--     | --  |
|id         | 고유 식별자| bigint| pk|
|account_id|uuid|String(20)||
|nickname| 사용자 이름| String(20)|
|password|비밀번호|String(10) |
|login_type|로그인유형|enum| 카카오, 네이버, 구글 등|
|account_kind|계정 유형| enum | 관리자, 상점, 사용자, 배달원 등|
|account_status|계정상태|enum | 정상, 휴면, 탈퇴 등|
|account_role| 계정 권한 | Set<Enum>| 관리자, 상점, 사용자, 배달원 등|
|login_count| 로그인 횟수 | integer| 
|login_fail_count| 로그인 실패 횟수 | integer

