package com.sparta.hh99springlv3.domain.admin.entity;

public enum AdminRoleEnum {
    ADMIN(Authority.ADMIN),
    MANAGER(Authority.MANAGER);

    private final String authority;

    AdminRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority(){
        return this.authority;
    }



    public static class Authority{
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String MANAGER = "ROLE_MANAGER";
    }

}
