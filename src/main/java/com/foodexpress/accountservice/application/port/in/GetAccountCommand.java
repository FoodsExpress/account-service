package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.common.SelfValidating;
import lombok.Getter;

@Getter
public class GetAccountCommand extends SelfValidating<GetAccountCommand> {

    private Long accountId;

    private String password;

    public static GetAccountCommand of(Long accountId, String password) {
        GetAccountCommand getAccountCommand = new GetAccountCommand();
        getAccountCommand.accountId = accountId;
        getAccountCommand.password = password;
        getAccountCommand.validateSelf();
        return getAccountCommand;
    }

}
