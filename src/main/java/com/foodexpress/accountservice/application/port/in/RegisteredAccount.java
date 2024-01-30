package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.AccountKind;
import com.foodexpress.accountservice.domain.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisteredAccount {

    private String accountId;

    /**
     * 닉네임
     */
    private String nickname;

    /**
     * 이메일
     */
    private String email;

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

    public static RegisteredAccount mapToDto(Account account) {
        RegisteredAccount registeredAccount = new RegisteredAccount();
        registeredAccount.accountId = account.accountId().getId();
        registeredAccount.accountKind = account.accountKind();
        registeredAccount.accountStatus = account.accountStatus();
        registeredAccount.nickname = account.nickname();
        registeredAccount.email = account.email();
        return registeredAccount;
    }

}
