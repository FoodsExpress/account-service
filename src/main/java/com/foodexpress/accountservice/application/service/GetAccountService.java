package com.foodexpress.accountservice.application.service;

import com.foodexpress.accountservice.application.port.in.GetAccountCommand;
import com.foodexpress.accountservice.application.port.in.GetAccountUseCase;
import com.foodexpress.accountservice.common.UseCase;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetAccountService implements GetAccountUseCase {

    @Override
    public Account getAccount(GetAccountCommand getAccountCommand) {
        return null;
    }

}
