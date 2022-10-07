package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum  PolicyType {

    OFFER("ids:ContractOffer", "Offer"),

    REQUEST("ids:ContractRequest", "Request"),

    AGREEMENT("ids:ContractAgreement", "Agreement");

    String idsRepresentation;
    String odrlRepresentation;

    PolicyType(String idsRepresentation, String odrlRepresentation) {
        this.idsRepresentation = idsRepresentation;
        this.odrlRepresentation = odrlRepresentation;
    }
    
    public String getIdsRepresentation() {
        return this.idsRepresentation;
    }
    
    public String getOdrlRepresentation() {
        return this.odrlRepresentation;
    }

    public static PolicyType getFromString(String stringRepresentation) {
        for( PolicyType policyType : values()) {
            if(policyType.getIdsRepresentation().equals(stringRepresentation) || policyType.getOdrlRepresentation().equals(stringRepresentation)) {
                return policyType;
            }
        }
        return null;
    }
}
