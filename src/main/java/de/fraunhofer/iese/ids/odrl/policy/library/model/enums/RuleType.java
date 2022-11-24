package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RuleType {

    PERMISSION("ids:permission", "permission"), //"allow",

    PROHIBITION("ids:prohibition", "prohibition"),//"inhibit"

    OBLIGATION("ids:obligation", "obligation"), //"allow"

    PRE_DUTY("ids:preDuty", "duty"),//"allow"

    POST_DUTY("ids:postDuty", "duty");//"allow"

    String idsRepresentation;
    String odrlRepresentation;

    RuleType(String idsRepresentation, String odrlRepresentation) {
        this.idsRepresentation = idsRepresentation;
        this.odrlRepresentation = odrlRepresentation;
    }

    public String getIdsRepresentation() {
        return this.idsRepresentation;
    }

    public String getOdrlRepresentation() {
        return this.odrlRepresentation;
    }

    public static RuleType getFromString(String stringRepresentation) {
        for( RuleType ruleType : values()) {
            if(ruleType.getIdsRepresentation().equals(stringRepresentation) || ruleType.getOdrlRepresentation().equals(stringRepresentation)) {
                return ruleType;
            }
        }
        return null;
    }
}

