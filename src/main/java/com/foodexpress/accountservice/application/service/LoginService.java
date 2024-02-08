package com.foodexpress.accountservice.application.service;

import com.foodexpress.accountservice.application.port.in.LoginCommand;
import com.foodexpress.accountservice.application.port.in.LoginUseCase;
import com.foodexpress.accountservice.application.port.out.AuthenticationJwtPort;
import com.foodexpress.accountservice.application.port.out.GetAccountPort;
import com.foodexpress.accountservice.common.UseCase;
import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final GetAccountPort getAccountPort;
    private final AuthenticationJwtPort authenticationJwtPort;

    @Override
    public JwtToken login(LoginCommand loginCommand) {
        Account account =
            getAccountPort.getAccountByEmailAndPassword(loginCommand.getEmail(), loginCommand.getPassword());
        return authenticationJwtPort.authentication(account);
    }

}
