package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.domain.JwtToken;

public interface TokenRefreshUseCase {

    JwtToken refreshAccessToken(TokenRefreshCommand command);

}
