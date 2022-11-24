package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum PartyType {

    PROVIDER("ids:provider"),

    CONSUMER("ids:consumer"),

    INFORMED_PARTY("informedParty");


    private final String type;

    PartyType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

