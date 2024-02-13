package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.common.SelfValidating;
import com.foodexpress.accountservice.common.advice.exceptions.NotMatchedPasswordException;
import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.LoginType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class RegisterAccountCommand extends SelfValidating<RegisterAccountCommand> {

    @NotNull
    private String nickname;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    private LoginType loginType;

    public Account toDomain() {
        this.validateSelf();
        if (!password.equals(confirmPassword)) {
            throw new NotMatchedPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return Account.builder().nickname(this.nickname)
            .password(this.password)
            .email(this.email)
            .loginType(this.loginType)
            .build();
    }

}
