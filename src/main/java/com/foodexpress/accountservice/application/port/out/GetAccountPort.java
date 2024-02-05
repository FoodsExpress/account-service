package com.foodexpress.accountservice.application.port.out;

import com.foodexpress.accountservice.domain.Account;

public interface GetAccountPort {

    Account getAccountByAccountIdAndPassword(String accountId, String password);

}
