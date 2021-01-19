package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ArtifactStateType {

    ANONYMIZED("idsc:ANONYMIZED"),

    PSEUDONYMIZED("idsc:PSEUDONYMIZED"),

    ENCRYPTED("idsc:ENCRYPTED"),

    COMBINED("idsc:COMBINED");


    private final String type;

    ArtifactStateType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

