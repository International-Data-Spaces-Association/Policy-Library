package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum TargetType {

    ASSET("odrl:Asset", "Asset"),

    ASSET_COLLECTION("odrl:AssetCollection", "AssetCollection");

    String idsRepresentation;
    String odrlRepresentation;

    TargetType(String idsRepresentation, String odrlRepresentation) {
        this.idsRepresentation = idsRepresentation;
        this.odrlRepresentation = odrlRepresentation;
    }

    public String getIdsRepresentation() {
        return this.idsRepresentation;
    }

    public String getOdrlRepresentation() {
        return this.odrlRepresentation;
    }

    public static TargetType getFromString(String stringRepresentation) {
        for( TargetType targetType : values()) {
            if(targetType.getIdsRepresentation().equals(stringRepresentation) || targetType.getOdrlRepresentation().equals(stringRepresentation)) {
                return targetType;
            }
        }
        return null;
    }
}
