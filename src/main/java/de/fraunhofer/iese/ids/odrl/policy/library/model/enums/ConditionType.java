package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ConditionType {

    //applies on odrl rule
    CONSTRAINT("ids:constraint"),

    //applies on action
    REFINEMENT("ids:refinement");

    private final String cType;

    ConditionType(String c) {
        cType = c;
    }

    public String getOdrlConditionType() {
        return cType;
    }
}

