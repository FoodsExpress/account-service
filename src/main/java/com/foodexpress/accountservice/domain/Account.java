package com.foodexpress.accountservice.domain;

import lombok.Builder;

@Builder
public record Account(AccountId accountId, String nickname, String password, String email, AccountStatus accountStatus,
                      AccountKind accountKind, LoginType loginType, int loginCount, int loginFailCount) {

}
