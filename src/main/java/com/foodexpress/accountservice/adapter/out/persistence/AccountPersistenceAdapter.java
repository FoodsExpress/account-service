package com.foodexpress.accountservice.adapter.out.persistence;

import com.foodexpress.accountservice.application.port.out.GetAccountPort;
import com.foodexpress.accountservice.application.port.out.RegisterAccountPort;
import com.foodexpress.accountservice.common.PersistenceAdapter;
import com.foodexpress.accountservice.common.advice.exceptions.AlreadyPresentAccountException;
import com.foodexpress.accountservice.common.advice.exceptions.NotValidAccountException;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

/**
 * 계정 JPA 용 어댑터
 * JPA 관련 로직들을 해당 클래스에 작성한다.
 *
 * @see AccountEntity
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements RegisterAccountPort, GetAccountPort {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 등록
     * 데이터 베이스에 사용자를 등록시킨 이후 도메인으로 변환해서 반환한다.
     *
     * @param account 사용자 도메인
     * @return 등록된 사용자 도메인
     */
    @Override
    public Account registerAccount(Account account) {
        accountRepository.findByEmail(account.email()).ifPresent(c -> {
            throw new AlreadyPresentAccountException("이미 존재하는 계정입니다.");
        });
        AccountEntity accountEntity = AccountEntity.createNewAccount(account, passwordEncoder);
        accountRepository.save(accountEntity);
        return accountEntity.mapToDomain();
    }

    /**
     * 이메일과 비밀번호가 일치하는 사용자가 있는지 확인
     *
     * @param email    사용자 이메일
     * @param password 비밀번호
     * @return Account 계정 정보
     */
    @Override
    @Transactional
    public Account getAccountByEmailAndPassword(String email, String password) {
        AccountEntity accountEntity = accountRepository.findByEmail(email).orElseThrow(NotValidAccountException::new);
        accountEntity.login(passwordEncoder, password);
        return accountEntity.mapToDomain();
    }

    @Override
    public Account getAccountByIdAndPassword(Long accountId, String password) {
        return accountRepository.findById(accountId).orElseThrow(NotValidAccountException::new).mapToDomain();
    }

}
