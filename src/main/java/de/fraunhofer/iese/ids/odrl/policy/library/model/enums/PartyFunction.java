package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum PartyFunction {

    INFORMEDPARTY("informedParty", "ids:informedParty");

    private final String mydataPartyFunction;
    private final String idsPartyFunction;

    PartyFunction(String op1, String op2) {
        mydataPartyFunction = op1;
        idsPartyFunction = op2;
    }

    public String getMydataPartyFunction() {
        return mydataPartyFunction;
    }

    public String getIdsPartyFunction() {
        return idsPartyFunction;
    }
}

