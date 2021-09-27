package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RightOperandType {

    DATE("xsd:date"),

    DATETIMESTAMP("xsd:datetimeStamp"),

    INTERVAL("ids:Interval"),

    INSTANT("ids:Instant"),

    DURATIONENTITY("ids:durationEntity"),

    INTEGER("xsd:integer"),

    STRING("xsd:string"),

    DECIMAL("xsd:decimal"),

    DOUBLE("http://www.w3.org/2001/XMLSchema#double"),

    DURATION("xsd:duration"),

    ANYURI("xsd:anyURI");



    private final String type;

    RightOperandType(String t) {
        this.type = t;
    }

    public String getType() {
        return type;
    }
}

