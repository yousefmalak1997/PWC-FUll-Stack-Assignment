package com.pwc.assignment.users;

public enum UsersRole {

    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER");

    private final String enumName;

    UsersRole(String enumName) {
        this.enumName = enumName;
    }

    public String getEnumName() {
        return enumName;
    }
}
