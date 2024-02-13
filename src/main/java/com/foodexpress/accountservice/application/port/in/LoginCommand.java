package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.common.SelfValidating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginCommand extends SelfValidating<LoginCommand> {

    private String email;
    private String password;

    public static LoginCommand of(String email, String password) {
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.email = email;
        loginCommand.password = password;
        loginCommand.validateSelf();
        return loginCommand;
    }

}
