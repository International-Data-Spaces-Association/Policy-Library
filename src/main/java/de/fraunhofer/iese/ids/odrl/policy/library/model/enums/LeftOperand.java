package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum LeftOperand {

    ELAPSED_TIME("elapsedtime", "idsc:ELAPSED_TIME", de.fraunhofer.iais.eis.LeftOperand.ELAPSED_TIME.getId().toString(), "elapsedTime"),

    DELAY("delay","idsc:DELAY", de.fraunhofer.iais.eis.LeftOperand.DELAY.getId().toString(), "delayPeriod"),

    DATE_TIME("datetime", "idsc:DATE_TIME", de.fraunhofer.iais.eis.LeftOperand.DATE_TIME.getId().toString(), "dateTime"),

    POLICY_EVALUATION_TIME("datetime", "idsc:POLICY_EVALUATION_TIME", de.fraunhofer.iais.eis.LeftOperand.POLICY_EVALUATION_TIME.getId().toString(), "MISSING"), // TODO Missing Left operand 

    COUNT("count", "idsc:COUNT", de.fraunhofer.iais.eis.LeftOperand.COUNT.getId().toString(), "count"),

    SYSTEM("system", "idsc:SYSTEM", de.fraunhofer.iais.eis.LeftOperand.SYSTEM.getId().toString(), "systemDevice"),

    APPLICATION("application","idsc:APPLICATION", de.fraunhofer.iais.eis.LeftOperand.APPLICATION.getId().toString(), "MISSING"), // TODO Missing Left operand  

    CONNECTOR("connector", "idsc:CONNECTOR", de.fraunhofer.iais.eis.LeftOperand.CONNECTOR.getId().toString(), "idsc:CONNECTOR"),

    SECURITY_LEVEL("securityLevel","idsc:SECURITY_LEVEL", de.fraunhofer.iais.eis.LeftOperand.SECURITY_LEVEL.getId().toString(), "idsc:SECURITY_LEVEL"),

    ROLE("role","idsc:ROLE", de.fraunhofer.iais.eis.LeftOperand.ROLE.getId().toString(), "MISSING"), // TODO Missing Left operand

    EVENT("event", "idsc:EVENT", de.fraunhofer.iais.eis.LeftOperand.EVENT.getId().toString(), "event"),

//    ENCODING("encoding", "idsc:ENCODING", de.fraunhofer.iais.eis.LeftOperand.ENCODING.getId().toString()),

    STATE("state","idsc:STATE", de.fraunhofer.iais.eis.LeftOperand.STATE.getId().toString(), "MISSING"), // TODO Missing Left operand

    ARTIFACT_STATE("artifactState", "idsc:ARTIFACT_STATE", de.fraunhofer.iais.eis.LeftOperand.ARTIFACT_STATE.getId().toString(), "MISSING"), // TODO Missing Left operand

    RECIPIENT("recipient", "idsc:RECIPIENT", de.fraunhofer.iais.eis.LeftOperand.RECIPIENT.getId().toString(), "recipient"),

//    INFORMEDPARTY("informedParty", "idsc:informedParty", de.fraunhofer.iais.eis.LeftOperand.INFORMED_PARTY.getId().toString()),

    TARGET_POLICY("targetPolicy", "idsc:TARGET_POLICY", de.fraunhofer.iais.eis.LeftOperand.TARGET_POLICY.getId().toString(), "hasPolicy"),

    SYSTEM_DEVICE("systemDevice", "idsc:SYSTEM_DEVICE", de.fraunhofer.iais.eis.LeftOperand.SYSTEM_DEVICE.getId().toString(), "systemDevice"),

    PAY_AMOUNT("payamount", "idsc:PAY_AMOUNT", de.fraunhofer.iais.eis.LeftOperand.PAY_AMOUNT.getId().toString(), "pay"),

    LOG_LEVEL("logLevel", "idsc:LOG_LEVEL", de.fraunhofer.iais.eis.LeftOperand.LOG_LEVEL.getId().toString(), "MISSING"), // TODO Missing Left operand

    NOTIFICATION_LEVEL("notificationLevel", "idsc:NOTIFICATION_LEVEL", de.fraunhofer.iais.eis.LeftOperand.NOTIFICATION_LEVEL.getId().toString(), "MISSING"), // TODO Missing Left operand

    JSON_PATH("jsonPathQuery","idsc:JSON_PATH", de.fraunhofer.iais.eis.LeftOperand.JSON_PATH.getId().toString(), "MISSING"), // TODO Missing Left operand
    //SUBSET_SPECIFICATION("jsonPathQuery","idsc:SUBSET_SPECIFICATION"),

    REPLACE_WITH("replaceWith","idsc:REPLACE_WITH", de.fraunhofer.iais.eis.LeftOperand.REPLACE_WITH.getId().toString(), "MISSING"), // TODO Missing Left operand

    //MODIFICATION_METHOD("modificationMethod","idsc:MODIFICATION_METHOD"),

//    DIGIT("digit","ids:digit", de.fraunhofer.iais.eis.LeftOperand.DIGIT.getId().toString()),

    ABSOLUTE_SPATIAL_POSITION("absoluteSpatialPosition","idsc:ABSOLUTE_SPATIAL_POSITION", de.fraunhofer.iais.eis.LeftOperand.ABSOLUTE_SPATIAL_POSITION.getId().toString(), "absoluteSpatialPosition"),

    PURPOSE("purpose", "https://w3id.org/idsa/core/purpose", de.fraunhofer.iais.eis.LeftOperand.PURPOSE.getId().toString(), "purpose");

    private final String label;
    private final String idsLeftOperand;
    private final String imLeftOperand;
    private final String odrlLeftOperand;

    LeftOperand(String label, String idsLeftOperand, String imLeftOperand, String odrlLeftOperand) {
        this.label = label;
        this.idsLeftOperand = idsLeftOperand;
        this.imLeftOperand = imLeftOperand;
        this.odrlLeftOperand = odrlLeftOperand;
    }

    public String getLabel() {
        return label;
    }

    public String getIdsLeftOperand() {
        return idsLeftOperand;
    }
    
    public String getOdrlLeftOperand() {
        return odrlLeftOperand;
    }
}

