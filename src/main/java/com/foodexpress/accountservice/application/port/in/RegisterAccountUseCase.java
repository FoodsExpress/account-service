package com.foodexpress.accountservice.application.port.in;

public interface RegisterAccountUseCase {

    RegisteredAccount registerAccount(RegisterAccountCommand registerAccountCommand);

}
