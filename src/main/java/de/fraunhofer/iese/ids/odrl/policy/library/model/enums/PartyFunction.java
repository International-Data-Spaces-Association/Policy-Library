package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum PartyFunction {

    INFORMED_PARTY("ids:informedParty", "informedParty");

    String idsRepresentation;
    String odrlRepresentation;

    PartyFunction(String idsRepresentation, String odrlRepresentation) {
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

