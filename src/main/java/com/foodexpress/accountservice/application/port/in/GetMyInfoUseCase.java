package com.foodexpress.accountservice.application.port.in;

import com.foodexpress.accountservice.domain.Account;

public interface GetMyInfoUseCase {

    Account getMyInfo(Long id);

}
