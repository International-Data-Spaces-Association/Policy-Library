package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum LogLevelType {

/*    OFF("idsc:OFF"),

    ALL("idsc:ALL"),

    INFO_LEVEL("idsc:INFO_LEVEL"),

    DEBUG_LEVEL("idsc:DEBUG_LEVEL"),*/

    ON_DENY("idsc:ON_DENY"),

    ON_ALLOW("idsc:ON_ALLOW"),

    ON_DUTY_EXERCISED("idsc:ON_DUTY_EXERCISED"),

    ON_ACTION_OPERATED("idsc:ON_ACTION_OPERATED");


    private final String type;

    LogLevelType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

