package com.foodexpress.accountservice.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record Account(AccountId accountId, String nickname, String password, String email, AccountStatus accountStatus,
                      AccountKind accountKind, LoginType loginType, int loginCount, int loginFailCount, Set<AccountRole> roles) {

}
