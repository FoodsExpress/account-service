package com.foodexpress.accountservice.application.service;

import com.foodexpress.accountservice.application.port.in.GetAccountCommand;
import com.foodexpress.accountservice.application.port.in.GetAccountUseCase;
import com.foodexpress.accountservice.application.port.in.GetMyInfoUseCase;
import com.foodexpress.accountservice.application.port.out.GetAccountPort;
import com.foodexpress.accountservice.common.UseCase;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetAccountService implements GetAccountUseCase, GetMyInfoUseCase {

    private final GetAccountPort getAccountPort;

    @Override
    public Account getAccount(GetAccountCommand getAccountCommand) {
        return getAccountPort.getAccountByIdAndPassword(getAccountCommand.getAccountId(), getAccountCommand.getPassword());
    }

    @Override
    public Account getMyInfo(Long id) {
        return getAccountPort.getAccountById(id);
    }
    
}
