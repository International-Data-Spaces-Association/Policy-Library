package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RightOperandId {

    POLICY_USAGE("ids:policyUsage", "policyUsage");

    String idsRepresentation;
    String odrlRepresentation;

    RightOperandId(String idsRepresentation, String odrlRepresentation) {
        this.idsRepresentation = idsRepresentation;
        this.odrlRepresentation = odrlRepresentation;
    }

    public String getIdsRepresentation() {
        return this.idsRepresentation;
    }

    public String getOdrlRepresentation() {
        return this.odrlRepresentation;
    }
}

