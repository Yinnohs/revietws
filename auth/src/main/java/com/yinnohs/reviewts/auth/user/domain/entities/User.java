package com.yinnohs.reviewts.auth.user.domain.entities;

import com.yinnohs.reviewts.auth.user.domain.vo.Email;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Email email;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
}
