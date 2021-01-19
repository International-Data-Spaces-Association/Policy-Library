package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum  PolicyType {

    OFFER("ids:ContractOffer"),

    REQUEST("ids:ContractRequest"),

    AGREEMENT("ids:ContractAgreement");

    String stringRepresentation;

    PolicyType(String s) {
        this.stringRepresentation = s;
    }
    public String getStringRepresentation() {
        return this.stringRepresentation;
    }

    public static PolicyType getFromIdsString(String stringRepresentation) {
        for( PolicyType policyType : values()) {
            if(policyType.getStringRepresentation().equals(stringRepresentation)) {
                return policyType;
            }
        }
        return null;
    }

}
