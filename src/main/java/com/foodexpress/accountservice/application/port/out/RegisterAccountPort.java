package com.foodexpress.accountservice.application.port.out;

import com.foodexpress.accountservice.domain.Account;

public interface RegisterAccountPort {

    Account registerAccount(Account account);

}
