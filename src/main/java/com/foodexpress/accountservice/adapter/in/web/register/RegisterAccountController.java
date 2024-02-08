package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.application.port.in.RegisterAccountUseCase;
import com.foodexpress.accountservice.application.port.in.RegisteredAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/auth")
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @PostMapping
    public ResponseEntity<RegisteredAccount> registerAccount(@RequestBody RegisterAccountRequest registerAccountRequest) {
        return ResponseEntity.ok(registerAccountUseCase.registerAccount(registerAccountRequest.convertCommand()));
    }

}
