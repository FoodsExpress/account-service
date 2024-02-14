package com.foodexpress.accountservice.domain;

/**
 * 사용자 계정 상태
 */
public enum AccountStatus {
    /**
     * 정상
     */
    NORMAL,
    /**
     * 휴면
     */
    DORMANCY,
    /**
     * 탈퇴
     */
    SECESSION
}
