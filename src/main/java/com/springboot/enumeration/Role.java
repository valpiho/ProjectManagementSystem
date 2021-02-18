package com.springboot.enumeration;

import static com.springboot.constant.Authority.MANAGER_AUTHORITIES;
import static com.springboot.constant.Authority.USER_AUTHORITIES;

public enum Role {

    ROLE_USER(USER_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES);

    private final String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
