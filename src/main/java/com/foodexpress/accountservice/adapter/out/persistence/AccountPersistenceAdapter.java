package com.foodexpress.accountservice.adapter.out.persistence;

import com.foodexpress.accountservice.application.port.out.RegisterAccountPort;
import com.foodexpress.accountservice.common.PersistenceAdapter;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements RegisterAccountPort {

    private final AccountRepository accountRepository;

    @Override
    public Account registerAccount(Account account) {
        AccountEntity accountEntity = AccountEntity.createNewAccount(account);
        accountRepository.save(accountEntity);
        return accountEntity.mapToDomain();
    }

}
