package io.cloudledger.aa.domain.user;

public enum UserRole {

    ROLE_Administrator("ROLE_Administrator"),
    ROLE_Team_Leader("ROLE_Team_Leader"),
    ROLE_User("ROLE_User");

    private final String code;

    UserRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
