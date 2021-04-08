package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RoleType {

    ADMIN("idsc:ADMIN"),

    END_USER("idsc:END_USER"),

    DEVELOPER("idsc:DEVELOPER");


    private final String type;

    RoleType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

