package com.yinnohs.reviewts.auth.auth.domain.ports.out;


import com.yinnohs.reviewts.auth.auth.domain.entities.Account;

import java.util.List;

public interface AccountService {
    Long save(Account account);
    Account findByEmail(String email);
    List<String> getTokens(String email, String password);
}
