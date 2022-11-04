package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

import java.net.URI;

import de.fraunhofer.iais.eis.Action;

public enum ActionType {

    USE("idsc:USE", Action.USE.getId().toString(), "ACTION", "use"),

    READ("idsc:READ", Action.READ.getId().toString(), "ACTION", "MISSING"), // TODO Check  operand

    DISPLAY("idsc:DISPLAY", "http://www.w3.org/ns/odrl/2/display", "ACTION", "MISSING"), // TODO Check  operand
    
    COMPENSATE("idsc:COMPENSATE", Action.COMPENSATE.getId().toString().toString(), "DUTY", "MISSING"), // TODO Check  operand

    DELETE("idsc:DELETE", Action.DELETE.getId().toString(), "DUTY", "MISSING"), // TODO Check  operand

    INFORM("idsc:INFORM", "http://www.w3.org/ns/odrl/2/inform", "DUTY", "MISSING"), // TODO Check  operand

    NOTIFY("idsc:NOTIFY", Action.NOTIFY.getId().toString(), "DUTY", "MISSING"), // TODO Check  operand

    PRINT("idsc:PRINT", "http://www.w3.org/ns/odrl/2/print","ACTION", "MISSING"), // TODO Check  operand

    ANONYMIZE("idsc:ANONYMIZE", Action.ANONYMIZE.getId().toString() , "DUTY", "MISSING"), // TODO Check  operand

    REPRODUCE("idsc:REPRODUCE", "http://www.w3.org/ns/odrl/2/reproduce" ,"ACTION", "MISSING"), // TODO Check  operand

    NEXT_POLICY("idsc:NEXT_POLICY", Action.NEXT_POLICY.getId().toString(), "DUTY", "MISSING"), // TODO Check  operand

    DISTRIBUTE("idsc:DISTRIBUTE", Action.DISTRIBUTE.getId().toString(),"ACTION", "MISSING"), // TODO Check  operand

    ROUND("idsc:ROUND", "http://example.com/iese/fraunhofer/de/action/round", "DUTY", "MISSING"), // TODO Check  operand

    REPLACE("idsc:REPLACE", Action.REPLACE.getId().toString() ,"DUTY", "MISSING"), // TODO Check  operand

    INCREMENT_COUNTER("idsc:INCREMENT_COUNTER", Action.INCREMENT_COUNTER.getId().toString(), "DUTY", "MISSING"), // TODO Check  operand

    LOG("idsc:LOG", Action.LOG.getId().toString(),  "DUTY", "MISSING"); // TODO Check  operand

    private final String idsAction;
    private final String imActionUri;
    private final String abstractAction;
    private final String odrlAction;

    ActionType(String idsAction, String imActionUri, String abstractAction, String odrlAction) {

        this.idsAction = idsAction;
        this.imActionUri = imActionUri;
        this.abstractAction = abstractAction;
        this.odrlAction = odrlAction;  
    }

    public String getIdsAction() {
        return idsAction;
    }
    public String getOdrlAction() {
        return odrlAction;
    }
    public String getAbstractIdsAction() {
        return abstractAction;
    }
    
    public String getImActionUri() {
    	return imActionUri;
    }

}

