package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ActionType {
    ACTION("odrl:action", ""),

    USE("idsc:USE","ACTION"),

    READ("idsc:READ", "ACTION"),

    DISPLAY("idsc:DISPLAY", "ACTION"),

    DELETE("idsc:DELETE", "ACTION"),

    INFORM("idsc:INFORM", "ACTION"),

    NOTIFY("idsc:NOTIFY", "ACTION"),

    PRINT("idsc:PRINT", "ACTION"),

    ANONYMIZE("idsc:ANONYMIZE", "ACTION"),

    REPRODUCE("idsc:REPRODUCE", "ACTION"),

    NEXT_POLICY("idsc:NEXT_POLICY", "ACTION"),

    DISTRIBUTE("idsc:DISTRIBUTE", "ACTION"),

    ROUND("idsc:ROUND", "ANONYMIZE"),

    COUNT("idsc:COUNT", "ACTION"),

    LOG("idsc:LOG", "ACTION");

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

