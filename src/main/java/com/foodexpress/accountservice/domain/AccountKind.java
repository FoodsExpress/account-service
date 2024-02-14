package com.foodexpress.accountservice.domain;

/**
 * 계정 종류
 * 로그인 유형으로 간주해도 된다.
 */
public enum AccountKind {
    /**
     * 관리자
     */
    ADMIN,
    /**
     * 상점( 가게 )
     */
    STORE,
    /**
     * 사용자
     */
    USER,
    /**
     * 배달원
     */
    RIDER,
    ;
}
