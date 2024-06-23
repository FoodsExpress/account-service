# 사용자 서비스

사용자 관련 기능들을 모아 놓은 서비스
로그인 및 개인 정보에 대한 내용들을 해당 서비스에서 처리할 수 있다.


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


## 2. 회원 가입

사용자는 회원가입 이후 해당 서비스를 이용 할 수 있으므로 회원 가입이 가장 최우선 된다.
회원가입은 닉네임, 이메일, 비밀번호를 입력해서 진행할 수 있으며, sns 간편로그인도 지원한다.

### 회원가입 예시

- url :
  http://{domain}/api/member/auth

```json
{
    "nickname": "seunggu",
    "email": "leesg107@naver.com",
    "password": "1234",
    "confirmPassword": "1234"
}
```

### 이미 존재할 경우

```json
{
    "success": false,
    "response": null,
    "message": "이미 존재하는 계정입니다.",
    "status": 400
}
```

### 가입 성공시

```json
{
    "accountId": "866d993e-493b-469e-8a1c-8591942fa729",
    "nickname": "seunggu",
    "email": "leesg6107@nate.com",
    "accountStatus": "NORMAL"
}
```

## 3. 로그인

사용자는 가입시 사용한 이메일과 비밀번호로 로그인 할 수 있다.
로그인을 하게 되면 `accessToken` 과 `refreshToken` 이 발급 되고 해당을 헤더에 넣어주어야 그 이후 통신이 가능하다.
헤더에 Authorization 의 값으로 넣어서 통신하면 된다.
ex)

```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJOSUNLTkFNRSI6InNldW5nZ3UiLCJBQ0NPVU5UX0lEIjoiYmZiM2ExZmQtMmUzYi00MDc3LWI2Y2EtMjg2Y2NlMTQyNGM5IiwiUk9MRVMiOltdLCJpc3MiOiJzZ3lqIiwiSUQiOjEsImV4cCI6MTcxMzY5Njk2MywiRU1BSUwiOiJsZWVzZzEwN0BuYXZlci5jb20iLCJpYXQiOjE3MTM2OTY2NjN9.ECS7ev97tYC0fCo98XNo6KiUtpZ7gyVrWU7ztsnxrCjiMHa26IEpqkkSjWS4r9fBkAWFwbfx-ki8BwRwU_plnw
```

accessToken 의 경우 만료 시간이 5분이고 refreshToken 의 경우 만료 시간이 30분이다.
accessToken 으로 계속 통신할 경우 refreshToken 은 계속 갱신되며, 30분간 accessToken 을 갱신하지 않을 경우 refreshToken 도 만료되면서 로그인은 해제된다.

### 로그인 예제

```json
{
    "email": "leesg6107@nate.com",
    "password": "1234"
}
```

### 성공시

```json
{
    "nickname": "seunggu",
    "email": "leesg6107@nate.com",
    "password": "1234",
    "confirmPassword": "1234"
}
```

### 실패시

```json
{
    "success": false,
    "response": null,
    "message": "계정이 존재하지 않거나 비밀번호가 일치하지 않습니다.",
    "status": 400
}
```

## 4. 토큰 갱신

로그인 이후 accessToken 으로 통신을 하게 되는데 위에서 언급한 바와 같이 5분이 지나면 accessToken 은 만료된다.
이때, 서버에서 만료되었다는 응답을 받게 되고, refreshToken 으로 갱신을 하면 accessToken 과 refreshToken 모두 재발급된다.

### 갱신 요청

```json
{
    "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJOSUNLTkFNRSI6InNldW5nZ3UiLCJBQ0NPVU5UX0lEIjoiYmZiM2ExZmQtMmUzYi00MDc3LWI2Y2EtMjg2Y2NlMTQyNGM5IiwiUk9MRVMiOltdLCJpc3MiOiJzZ3lqIiwiSUQiOjEsImV4cCI6MTcxOTIzOTg1NywiRU1BSUwiOiJsZWVzZzEwN0BuYXZlci5jb20iLCJpYXQiOjE3MTkxNTM0NTd9.8_3GN6o3SvS8GNu5kpZd1X5ZubwLodFFzSp9iBCH4KLmp7WLffTeBlucHEzPJZsG4zI_nV0xVGzhq6tR7r-x-w"
}
```

### 정상 응답

```json
{
    "success": true,
    "response": {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJOSUNLTkFNRSI6InNldW5nZ3UiLCJBQ0NPVU5UX0lEIjoiYmZiM2ExZmQtMmUzYi00MDc3LWI2Y2EtMjg2Y2NlMTQyNGM5IiwiUk9MRVMiOltdLCJpc3MiOiJzZ3lqIiwiSUQiOjEsImV4cCI6MTcxOTE1NTc0NywiRU1BSUwiOiJsZWVzZzEwN0BuYXZlci5jb20iLCJpYXQiOjE3MTkxNTU0NDd9.xGjWwB9VG7MhR3BhzDRSCIwYrPjhwEhqGo0Wfd5Lks_3dFHKynu3CFZOju7picjAbsB1xS25_VQ_Lg5OH7Jh0g",
        "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJOSUNLTkFNRSI6InNldW5nZ3UiLCJBQ0NPVU5UX0lEIjoiYmZiM2ExZmQtMmUzYi00MDc3LWI2Y2EtMjg2Y2NlMTQyNGM5IiwiUk9MRVMiOltdLCJpc3MiOiJzZ3lqIiwiSUQiOjEsImV4cCI6MTcxOTI0MTg0NywiRU1BSUwiOiJsZWVzZzEwN0BuYXZlci5jb20iLCJpYXQiOjE3MTkxNTU0NDd9.ROKqblrkgJlkXPtSQUd3JEcZFA9zRgrN_puypLoQe5g34Yt3mzllzfDLi3dgxJFP90NmyLUkvyA1LOLTf6dpVQ"
    },
    "message": "처리되었습니다.",
    "status": 200
}
```

### 실패 응답

```json
{
    "success": false,
    "response": null,
    "message": "The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA512",
    "status": 500
}
```

## 5. 내 정보 조회

회원은 로그인 한 이후부터 내 정보를 조회할 수 있다.

- url : http://{domain}/api/account/me

### 정상 응답

```json
{
    "success": true,
    "response": {
        "id": "bfb3a1fd-2e3b-4077-b6ca-286cce1424c9",
        "email": "leesg107@naver.com",
        "nickname": "seunggu",
        "imageUrl": ""
    },
    "message": "처리되었습니다.",
    "status": 200
}
```

### 토큰 만료 응답

```json
{
    "success": false,
    "response": {},
    "message": "인증 토큰이 유효하지 않습니다.",
    "status": 401
}
```