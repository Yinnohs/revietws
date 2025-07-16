package com.yinnohs.reviewts.auth.auth.domain.ports.out;


import com.yinnohs.reviewts.auth.auth.domain.entities.Account;

import java.util.List;

public interface AccountService {
    String save(Account account);
    Account findByEmail(String email);
    List<String> getTokens(String email, String password);
}
