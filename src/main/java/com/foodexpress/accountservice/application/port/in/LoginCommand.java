package com.foodexpress.accountservice.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginCommand {

    private String email;
    private String password;

    public static LoginCommand of(String email, String password) {
        LoginCommand loginCommand = new LoginCommand();
        loginCommand.email = email;
        loginCommand.password = password;
        return loginCommand;
    }

}
