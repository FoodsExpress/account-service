package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.application.port.in.RegisterAccountCommand;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class RegisterAccountRequest {

    private String nickname;
    private String email;
    private String password;
    private String confirmPassword;

    public RegisterAccountCommand convertCommand() {
        RegisterAccountCommand registerAccountCommand = new RegisterAccountCommand();
        copyProperties(this, registerAccountCommand);
        return registerAccountCommand;
    }

}
