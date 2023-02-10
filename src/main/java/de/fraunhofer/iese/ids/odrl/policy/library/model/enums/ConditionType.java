package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ConditionType {

    //applies on odrl rule
    CONSTRAINT("ids:constraint", "constraint"),

    //applies on action
    REFINEMENT("ids:refinement", "refinement");

    String idsRepresentation;
    String odrlRepresentation;

    ConditionType(String idsRepresentation, String odrlRepresentation) {
        this.idsRepresentation = idsRepresentation;
        this.odrlRepresentation = odrlRepresentation;
    }

    public String getIdsRepresentation() {
        return this.idsRepresentation;
    }

    public String getOdrlRepresentation() {
        return this.odrlRepresentation;
    }

    public static ConditionType getFromString(String stringRepresentation) {
        for( ConditionType conditionType : values()) {
            if(conditionType.getIdsRepresentation().equals(stringRepresentation) || conditionType.getOdrlRepresentation().equals(stringRepresentation)) {
                return conditionType;
            }
        }
        return null;
    }
}

