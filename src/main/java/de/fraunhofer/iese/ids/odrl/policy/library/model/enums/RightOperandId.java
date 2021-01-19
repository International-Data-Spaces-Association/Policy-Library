package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum RightOperandId {

    POLICYUSAGE("policyUsage", "ids:policyUsage");


    private final String mydataRightOperand;
    private final String idsRightOperand;

    RightOperandId(String op1, String op2) {
        mydataRightOperand = op1;
        idsRightOperand = op2;
    }

    public String getMydataRightOperand() {
        return mydataRightOperand;
    }

    public String getIdsRightOperand() {
        return idsRightOperand;
    }
}

