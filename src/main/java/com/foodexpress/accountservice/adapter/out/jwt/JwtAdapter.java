package com.foodexpress.accountservice.adapter.out.jwt;

import com.foodexpress.accountservice.application.port.out.AuthenticationJwtPort;
import com.foodexpress.accountservice.application.port.out.TokenRefreshPort;
import com.foodexpress.accountservice.common.PersistenceAdapter;
import com.foodexpress.accountservice.common.advice.exceptions.NotValidJwtTokenException;
import com.foodexpress.accountservice.domain.Account;
import com.foodexpress.accountservice.domain.AccountRole;
import com.foodexpress.accountservice.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class JwtAdapter implements AuthenticationJwtPort, TokenRefreshPort {

    private final Jwt jwt;

    @Override
    public JwtToken authentication(Account account) {
        Jwt.Claims claims =
            Jwt.Claims.of(account.id(), account.accountId().getId(), account.email(), account.nickname(), account.roles().stream().map(
                AccountRole::name).toArray(String[]::new));
        return JwtToken.of(jwt.createAccessToken(claims), jwt.createRefreshToken(claims));
    }

    /**
     * refresh 토큰으로 신규 토큰 재발급
     *
     * @param refreshToken 갱신 토큰
     * @return JwtToken 신규 발급된 토큰
     */
    @Override
    public JwtToken refreshAccessToken(String refreshToken) {
        if (jwt.validateToken(refreshToken)) {
            throw new NotValidJwtTokenException();
        }
        Jwt.Claims claims = jwt.verify(refreshToken);
        Jwt.Claims newClaims =
            Jwt.Claims.of(claims.getId(), claims.getAccountId(), claims.getEmail(), claims.getNickname(), claims.getRoles());
        return JwtToken.of(
            jwt.createAccessToken(newClaims),
            jwt.createRefreshToken(newClaims)
        );
    }

}
