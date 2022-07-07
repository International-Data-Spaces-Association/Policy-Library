package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

public enum EntityType {

    BEGIN("ids:begin"),//"startDate",

    END("ids:end"),//"endDate", 

    DATETIME("ids:dateTime"),//"datetime",

    HASDURATION("ids:hasDuration");//"duration", 


    private String type;

    EntityType(String type) {
    	this.type = type;
    }


    public String getType() {
        return type;
    }
}

