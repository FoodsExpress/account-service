package com.foodexpress.accountservice.adapter.out.persistence;

import com.foodexpress.accountservice.domain.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "account")
public class AccountEntity extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID accountId;

    /**
     * 닉네임
     */
    private String nickname;

    /**
     * 이메일
     */
    private String email;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 계정 상태
     */
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    /**
     * 계정 유형
     */
    @Enumerated(EnumType.STRING)
    private AccountKind accountKind;

    /**
     * 로그인 유형
     */
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    /**
     * 로그인 횟수
     */
    private int loginCount;

    /**
     * 로그인 실패 횟수
     */
    private int loginFailCount;

    public static AccountEntity createNewAccount(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.accountId = UUID.randomUUID();
        accountEntity.email = account.email();
        accountEntity.nickname = account.nickname();
        accountEntity.password = account.password();
        accountEntity.accountStatus = AccountStatus.PENDING;
        accountEntity.accountKind = account.accountKind();
        accountEntity.loginType = account.loginType();
        return accountEntity;
    }

    public Account mapToDomain() {
        return Account.builder()
            .accountId(AccountId.of(this.accountId.toString()))
            .email(this.email)
            .nickname(this.nickname)
            .accountStatus(this.accountStatus)
            .accountKind(this.accountKind)
            .loginType(this.loginType)
            .loginCount(this.loginCount)
            .loginFailCount(this.loginFailCount)
            .build();
    }

}
