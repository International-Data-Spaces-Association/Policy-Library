package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

import java.net.URI;

import de.fraunhofer.iais.eis.Action;

public enum ActionType {

    USE("idsc:USE", Action.USE.getId().toString(), "ACTION"),

    READ("idsc:READ", Action.READ.getId().toString(), "ACTION"),

    DISPLAY("idsc:DISPLAY", "http://www.w3.org/ns/odrl/2/display", "ACTION"),
    
    COMPENSATE("idsc:COMPENSATE", Action.COMPENSATE.getId().toString().toString(), "DUTY"),

    DELETE("idsc:DELETE", Action.DELETE.getId().toString(), "DUTY"),

    INFORM("idsc:INFORM", "http://www.w3.org/ns/odrl/2/inform", "DUTY"),

    NOTIFY("idsc:NOTIFY", Action.NOTIFY.getId().toString(), "DUTY"),

    PRINT("idsc:PRINT", "http://www.w3.org/ns/odrl/2/print","ACTION"),

    ANONYMIZE("idsc:ANONYMIZE", Action.ANONYMIZE.getId().toString() , "DUTY"),

    REPRODUCE("idsc:REPRODUCE", "http://www.w3.org/ns/odrl/2/reproduce" ,"ACTION"),

    NEXT_POLICY("idsc:NEXT_POLICY", Action.NEXT_POLICY.getId().toString(), "DUTY"),

    DISTRIBUTE("idsc:DISTRIBUTE", Action.DISTRIBUTE.getId().toString(),"ACTION"),

    ROUND("idsc:ROUND", "http://example.com/iese/fraunhofer/de/action/round", "DUTY"),

    REPLACE("idsc:REPLACE", Action.REPLACE.getId().toString() ,"DUTY"),

    INCREMENT_COUNTER("idsc:INCREMENT_COUNTER", Action.INCREMENT_COUNTER.getId().toString(), "DUTY"),

    LOG("idsc:LOG", Action.LOG.getId().toString(),  "DUTY");

    private final String idsAction;
    private final String imActionUri;
    private final String abstractAction;

    ActionType(String idsAction, String imActionUri, String abstractAction) {

        this.idsAction = idsAction;
        this.imActionUri = imActionUri;
        this.abstractAction = abstractAction;
        
        
    }

    public String getIdsAction() {
        return idsAction;
    }
    public String getAbstractIdsAction() {
        return abstractAction;
    }
    
    public String getImActionUri() {
    	return imActionUri;
    }

}

