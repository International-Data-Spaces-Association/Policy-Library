package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum LeftOperand {

    ELAPSED_TIME("elapsedtime", "idsc:ELAPSED_TIME"),

    DELAY("delay","idsc:DELAY"),

    DATE_TIME("datetime", "idsc:DATE_TIME"),

    POLICY_EVALUATION_TIME("datetime", "idsc:POLICY_EVALUATION_TIME"),

    COUNT("count", "ids:count"),

    SYSTEM("system", "ids:system"),

    APPLICATION("application","ids:application"),

    CONNECTOR("connector", "idsc:connector"),

    EVENT("event", "ids:event"),

    ENCODING("encoding", "ids:encoding"),

    ARTIFACT_STATE("artifactState", "idsc:ARTIFACT_STATE"),

    RECIPIENT("recipient", "ids:RECIPIENT"),

    INFORMEDPARTY("informedParty", "ids:informedParty"),

    TARGET_POLICY("targetPolicy", "idsc:TARGET_POLICY"),

    SYSTEM_DEVICE("systemDevice", "ids:SYSTEM_DEVICE"),

    PAY_AMOUNT("payamount", "idsc:PAY_AMOUNT"),

    LOG_LEVEL("logLevel", "idsc:LOG_LEVEL"),

    NOTIFICATION_LEVEL("notificationLevel", "idsc:NOTIFICATION_LEVEL"),

    //JSONPATH("jsonPathQuery","ids:jsonPath"),

    MODIFICATIONMETHOD("modificationMethod","ids:modificationMethod"),

    DIGIT("digit","ids:digit"),

    ABSOLUTESPATIALPOSITION("absoluteSpatialPosition","https://w3id.org/idsa/core/absoluteSpatialPosition"),

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

