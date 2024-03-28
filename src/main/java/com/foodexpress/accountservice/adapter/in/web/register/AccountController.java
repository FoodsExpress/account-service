package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.adapter.out.jwt.JwtAuthentication;
import com.foodexpress.accountservice.application.port.in.GetMyInfoUseCase;
import com.foodexpress.accountservice.common.util.ApiUtil;
import com.foodexpress.accountservice.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.foodexpress.accountservice.common.util.ApiUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final GetMyInfoUseCase getMyInfoUseCase;

    @GetMapping("/me")
    public ApiUtil.ApiResult<MyInfo> getMyInfo(@AuthenticationPrincipal JwtAuthentication authentication) {
        Account account = getMyInfoUseCase.getMyInfo(authentication.id());
        return success(MyInfo.from(account));
    }

}
