package com.rizky.cinematic.backend.model.enums;

import java.util.Arrays;

public enum ERole {

    ADMIN ("admin") ,
    CUSTOMER ("customer");

    private final String roleName;

    ERole (String s) {roleName = s; }

    public boolean equalsname (String otherName) {
        return roleName.equals(otherName);
    }

    public static String getAllRoles() {
        return Arrays.toString(ERole.class.getEnumConstants());
    }

}
