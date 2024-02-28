package com.foodexpress.accountservice.adapter.in.web.register;

import com.foodexpress.accountservice.domain.Account;
import lombok.Data;

@Data
public class MyInfo {

    private String id;

    private String email;

    private String nickname;

    public static MyInfo from(Account account) {
        MyInfo info = new MyInfo();
        info.id = account.accountId().getId();
        info.email = account.email();
        info.nickname = account.nickname();
        return info;
    }

}
