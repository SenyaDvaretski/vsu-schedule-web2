package com.vsuscheduleweb.security.roles;

import lombok.Getter;

import java.util.Set;



@Getter
public enum Role {
    USER(Set.of(Permission.HOTELS_READ)),
    ADMIN(Set.of(Permission.HOTELS_WRITE,Permission.HOTELS_READ));
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }


}
