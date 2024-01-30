package com.foodexpress.accountservice.application.service;

import com.foodexpress.accountservice.application.port.in.RegisterAccountCommand;
import com.foodexpress.accountservice.application.port.in.RegisterAccountUseCase;
import com.foodexpress.accountservice.application.port.in.RegisteredAccount;
import com.foodexpress.accountservice.application.port.out.RegisterAccountPort;
import com.foodexpress.accountservice.common.UseCase;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterAccountService implements RegisterAccountUseCase {

    private final RegisterAccountPort registerAccountPort;

    @Override
    public RegisteredAccount registerAccount(RegisterAccountCommand registerAccountCommand) {
        Account account = registerAccountPort.registerAccount(registerAccountCommand.toDomain());
        return RegisteredAccount.mapToDto(account);
    }

}
