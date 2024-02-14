package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.application.port.in.LoginCommand;
import com.foodexpress.accountservice.application.port.in.LoginUseCase;
import com.foodexpress.accountservice.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/auth/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public JwtToken login(@RequestBody LoginRequest loginRequest) {
        return loginUseCase.login(LoginCommand.of(loginRequest.getEmail(), loginRequest.getPassword()));
    }

}
