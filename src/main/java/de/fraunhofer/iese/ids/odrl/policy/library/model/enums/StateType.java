package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum StateType {

    CONTRACTNOTTERMINATED("ids:CONTRACTNOTTERMINATED"),

    FIREWALLACTIVATED("ids:FIREWALLACTIVATED");


    private final String type;

    StateType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

