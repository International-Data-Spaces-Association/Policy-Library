package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum LeftOperand {

    ELAPSED_TIME("elapsedtime", "idsc:ELAPSED_TIME"),

    DELAY("delay","idsc:DELAY"),

    DATE_TIME("datetime", "idsc:DATE_TIME"),

    POLICY_EVALUATION_TIME("datetime", "idsc:POLICY_EVALUATION_TIME"),

    COUNT("count", "idsc:COUNT"),

    SYSTEM("system", "idsc:SYSTEM"),

    APPLICATION("application","idsc:APPLICATION"),

    CONNECTOR("connector", "idsc:CONNECTOR"),

    SECURITY_LEVEL("securityLevel","idsc:SECURITY_LEVEL"),

    ROLE("role","idsc:ROLE"),

    EVENT("event", "idsc:EVENT"),

    ENCODING("encoding", "idsc:ENCODING"),

    STATE("state","idsc:STATE"),

    ARTIFACT_STATE("artifactState", "idsc:ARTIFACT_STATE"),

    RECIPIENT("recipient", "idsc:RECIPIENT"),

    INFORMEDPARTY("informedParty", "idsc:informedParty"),

    TARGET_POLICY("targetPolicy", "idsc:TARGET_POLICY"),

    SYSTEM_DEVICE("systemDevice", "idsc:SYSTEM_DEVICE"),

    PAY_AMOUNT("payamount", "idsc:PAY_AMOUNT"),

    LOG_LEVEL("logLevel", "idsc:LOG_LEVEL"),

    NOTIFICATION_LEVEL("notificationLevel", "idsc:NOTIFICATION_LEVEL"),

    SUBSET_SPECIFICATION("jsonPathQuery","idsc:SUBSET_SPECIFICATION"),

    REPLACE_WITH("replaceWith","idsc:REPLACE_WITH"),

    //MODIFICATION_METHOD("modificationMethod","idsc:MODIFICATION_METHOD"),

    DIGIT("digit","ids:digit"),

    ABSOLUTE_SPATIAL_POSITION("absoluteSpatialPosition","idsc:ABSOLUTE_SPATIAL_POSITION"),

    PURPOSE("purpose", "https://w3id.org/idsa/core/purpose");

    private final String mydataLeftOperand;
    private final String idsLeftOperand;

    LeftOperand(String op1, String op2) {
        mydataLeftOperand = op1;
        idsLeftOperand = op2;
    }

    public String getMydataLeftOperand() {
        return mydataLeftOperand;
    }

    public String getIdsLeftOperand() {
        return idsLeftOperand;
    }
}

