package com.foodexpress.accountservice.adapter.out.jwt;

import com.foodexpress.accountservice.application.port.out.AuthenticationJwtPort;
import com.foodexpress.accountservice.common.PersistenceAdapter;
import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.AccountRole;
import com.foodexpress.accountservice.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class JwtAdapter implements AuthenticationJwtPort {

    private final Jwt jwt;

    @Override
    public JwtToken authentication(Account account) {
        Jwt.Claims claims =
            Jwt.Claims.of(account.id(), account.accountId().getId(), account.email(), account.nickname(), account.roles().stream().map(
                AccountRole::name).toArray(String[]::new));
        return JwtToken.of(jwt.createAccessToken(claims), jwt.createRefreshToken(claims));
    }

}
