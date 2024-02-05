package com.foodexpress.accountservice.adapter.out.persistence;

import com.foodexpress.accountservice.application.port.out.GetAccountPort;
import com.foodexpress.accountservice.application.port.out.RegisterAccountPort;
import com.foodexpress.accountservice.common.PersistenceAdapter;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements RegisterAccountPort, GetAccountPort {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account registerAccount(Account account) {
        AccountEntity accountEntity = AccountEntity.createNewAccount(account);
        accountRepository.save(accountEntity);
        return accountEntity.mapToDomain();
    }

    @Override
    public Account getAccountByAccountIdAndPassword(String accountId, String password) {
        AccountEntity accountEntity = accountRepository.findByAccountId(UUID.fromString(accountId)).orElseThrow();
        if (!passwordEncoder.matches(password, accountEntity.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return accountEntity.mapToDomain();
    }

}
