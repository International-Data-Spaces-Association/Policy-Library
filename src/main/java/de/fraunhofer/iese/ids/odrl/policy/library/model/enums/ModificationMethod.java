package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum ModificationMethod {

    DELETE("delete", "http://example.com/anonymize/delete"),

    REPLACE("replace", "http://example.com/anonymize/replace");

    private final String mydataMethod;
    private final String idsMethod;

    ModificationMethod(String m1, String m2) {
        mydataMethod = m1;
        idsMethod = m2;
    }

    public String getMydataMethod() {
        return mydataMethod;
    }

    public String getIdsMethod() {
        return idsMethod;
    }
}

