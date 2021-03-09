package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum LogLevelType {

/*    OFF("idsc:OFF"),

    ALL("idsc:ALL"),

    INFO_LEVEL("idsc:INFO_LEVEL"),

    DEBUG_LEVEL("idsc:DEBUG_LEVEL"),*/

    ON_DENY("idsc:LOG_ON_DENY"),

    ON_ALLOW("idsc:LOG_ON_ALLOW"),

    ON_DUTY_EXERCISED("idsc:LOG_ON_DUTY_EXERCISED"),

    ON_ACTION_OPERATED("idsc:LOG_ON_ACTION_OPERATED");


    private final String type;

    LogLevelType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

