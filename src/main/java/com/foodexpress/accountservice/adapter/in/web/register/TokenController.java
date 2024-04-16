package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.application.port.in.TokenRefreshUseCase;
import com.foodexpress.accountservice.common.util.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.foodexpress.accountservice.common.util.ApiUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/auth/token")
public class TokenController {

    private final TokenRefreshUseCase useCase;

    @PutMapping
    public ApiUtil.ApiResult<TokenRefreshResponse> refreshAccessTokenByRefreshToken(@RequestBody TokenRefreshRequest request) {
        return success(TokenRefreshResponse.from(useCase.refreshAccessToken(request.mapToCommand())));
    }

}
