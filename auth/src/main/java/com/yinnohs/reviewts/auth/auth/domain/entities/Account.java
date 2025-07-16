package com.yinnohs.reviewts.auth.auth.domain.entities;

import com.yinnohs.reviewts.auth.auth.domain.vos.Email;
import com.yinnohs.reviewts.auth.auth.domain.vos.Password;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
    private String id;
    private Email email;
    private Password password;
    private String refreshToken;
    private String userId;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

}
