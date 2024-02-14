package com.foodexpress.accountservice.application.port.out;

import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.JwtToken;

public interface AuthenticationJwtPort {

    JwtToken authentication(Account account);

}
