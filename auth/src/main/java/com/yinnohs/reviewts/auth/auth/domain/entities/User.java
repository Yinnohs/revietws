package com.yinnohs.reviewts.auth.auth.domain.entities;

import com.yinnohs.reviewts.auth.user.domain.vo.Email;
import lombok.*;

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
}
