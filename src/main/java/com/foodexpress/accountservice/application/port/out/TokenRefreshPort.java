package com.foodexpress.accountservice.application.port.out;

import com.foodexpress.accountservice.domain.JwtToken;

public interface TokenRefreshPort {

    JwtToken refreshAccessToken(String refreshToken);

}
