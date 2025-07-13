package com.yinnohs.reviewts.auth.auth.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    Permissions.ADMIN_CREATE,
                    Permissions.ADMIN_UPDATE,
                    Permissions.ADMIN_DELETE,
                    Permissions.ADMIN_READ,
                    Permissions.MANAGER_CREATE,
                    Permissions.MANAGER_UPDATE,
                    Permissions.MANAGER_DELETE,
                    Permissions.MANAGER_READ
            )
    ),

    MANAGER(
            Set.of(Permissions.MANAGER_CREATE,
                    Permissions.MANAGER_UPDATE,
                    Permissions.MANAGER_DELETE,
                    Permissions.MANAGER_READ)
    );

    private final Set<Permissions> permissions;


}
