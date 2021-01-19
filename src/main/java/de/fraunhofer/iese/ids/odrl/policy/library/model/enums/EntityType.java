package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum EntityType {

    BEGIN("startDate","ids:begin"),

    END("endDate", "ids:end"),

    HASDURATION("duration", "ids:hasDuration");

    private final String mydataDecision;
    private final String odrlRuleType;

    EntityType(String op1, String op2) {
        mydataDecision = op1;
        odrlRuleType = op2;
    }

    public String getMydataDecision() {
        return mydataDecision;
    }

    public String getOdrlRuleType() {
        return odrlRuleType;
    }
}

