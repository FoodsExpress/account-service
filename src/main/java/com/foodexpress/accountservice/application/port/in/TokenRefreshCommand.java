package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class TokenRefreshCommand extends SelfValidating<TokenRefreshCommand> {

    @NotNull(message = "refreshToken 은 필수값입니다.")
    private String refreshToken;

}
