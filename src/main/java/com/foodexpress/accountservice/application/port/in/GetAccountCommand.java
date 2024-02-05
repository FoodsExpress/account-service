package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.common.SelfValidating;
import lombok.Getter;

@Getter
public class GetAccountCommand extends SelfValidating<RegisterAccountCommand> {

    private String accountId;

    private String password;

    public static GetAccountCommand of(String accountId, String password) {
        GetAccountCommand getAccountCommand = new GetAccountCommand();
        getAccountCommand.accountId = accountId;
        getAccountCommand.password = password;
        return getAccountCommand;
    }

}
