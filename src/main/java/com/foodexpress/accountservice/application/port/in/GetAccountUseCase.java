package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.domain.Account;

public interface GetAccountUseCase {

    Account getAccount(GetAccountCommand getAccountCommand);

}
