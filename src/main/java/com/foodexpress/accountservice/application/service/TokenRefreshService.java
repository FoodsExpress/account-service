package com.foodexpress.accountservice.application.service;

import com.foodexpress.accountservice.application.port.in.TokenRefreshCommand;
import com.foodexpress.accountservice.application.port.in.TokenRefreshUseCase;
import com.foodexpress.accountservice.application.port.out.TokenRefreshPort;
import com.foodexpress.accountservice.common.UseCase;
import com.foodexpress.accountservice.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class TokenRefreshService implements TokenRefreshUseCase {

    private final TokenRefreshPort tokenRefreshPort;

    @Override
    public JwtToken refreshAccessToken(TokenRefreshCommand command) {
        return tokenRefreshPort.refreshAccessToken(command.getRefreshToken());
    }

}
