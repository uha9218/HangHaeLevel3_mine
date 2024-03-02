package com.sparta.hh99springlv3.global.entity;

import lombok.Getter;

@Getter
public enum AuthEnum {
    MANAGER(Authority.MANAGER),
    STAFF(Authority.STAFF);

    private final String authority;

    AuthEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        public static final String MANAGER = "AUTH_MANAGER";
        public static final String STAFF = "AUTH_STAFF";
    }

}
