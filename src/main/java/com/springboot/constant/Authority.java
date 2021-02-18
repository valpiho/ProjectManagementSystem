package com.springboot.constant;

public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read", "user:update"};
    public static final String[] MANAGER_AUTHORITIES = { "user:read", "user:update", "user:delete"};
}
