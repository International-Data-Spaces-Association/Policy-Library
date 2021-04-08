package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum SecurityLevelType {

    BASE_SECURITY_PROFILE("idsc:BASE_SECURITY_PROFILE"),

    TRUST_SECURITY_PROFILE("idsc:TRUST_SECURITY_PROFILE"),

    TRUST_PLUS_SECURITY_PROFILE("idsc:TRUST_PLUS_SECURITY_PROFILE");


    private final String type;

    SecurityLevelType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

