package com.foodexpress.accountservice.application.port.out;

import com.foodexpress.accountservice.domain.Account;

public interface GetAccountPort {

    Account getAccountByEmailAndPassword(String email, String password);

    Account getAccountByIdAndPassword(Long accountId, String password);

    Account getAccountById(Long id);

}
