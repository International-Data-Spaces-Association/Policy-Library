package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ActionType {

    USE("idsc:USE","ACTION"),

    READ("idsc:READ", "ACTION"),

    DISPLAY("idsc:DISPLAY", "ACTION"),

    DELETE("idsc:DELETE", "DUTY"),

    INFORM("idsc:INFORM", "DUTY"),

    NOTIFY("idsc:NOTIFY", "DUTY"),

    PRINT("idsc:PRINT", "ACTION"),

    ANONYMIZE("idsc:ANONYMIZE", "DUTY"),

    REPRODUCE("idsc:REPRODUCE", "ACTION"),

    NEXT_POLICY("idsc:NEXT_POLICY", "DUTY"),

    DISTRIBUTE("idsc:DISTRIBUTE", "ACTION"),

    ROUND("idsc:ROUND", "DUTY"),

    REPLACE("idsc:REPLACE", "DUTY"),

    INCREMENT_COUNTER("idsc:INCREMENT_COUNTER", "DUTY"),

    LOG("idsc:LOG", "DUTY");

    private final String idsAction;
    private final String abstractAction;

    ActionType(String a, String b) {

        idsAction = a;
        abstractAction = b;
    }

    public String getIdsAction() {
        return idsAction;
    }
    public String getAbstractIdsAction() {
        return abstractAction;
    }

}

