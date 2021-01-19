package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RuleType {

    PERMISSION("allow","ids:permission"),

    PROHIBITION("inhibit", "ids:prohibition"),

    OBLIGATION("allow", "ids:obligation"),

    PREDUTY("allow", "ids:preDuty"),

    POSTDUTY("allow", "ids:postDuty");

    private final String mydataDecision;
    private final String odrlRuleType;

    RuleType(String op1, String op2) {
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

