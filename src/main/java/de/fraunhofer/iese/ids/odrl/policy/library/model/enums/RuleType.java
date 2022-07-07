package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RuleType {

    PERMISSION("ids:permission"), //"allow",

    PROHIBITION("ids:prohibition"),//"inhibit"

    OBLIGATION("ids:obligation"), //"allow"

    PREDUTY("ids:preDuty"),//"allow"

    POSTDUTY("ids:postDuty");//"allow"


    private final String type;

    RuleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

