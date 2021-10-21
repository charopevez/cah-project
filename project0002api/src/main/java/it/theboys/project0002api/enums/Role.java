package it.theboys.project0002api.enums;

public enum Role {
    USER("user"),
    GUEST("guest"),
    ADMIN("admin");

    private String role;

    private Role(String role){
        this.role=role;
    }
}
