package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.util.ArrayList;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ActionType;
import lombok.Data;


@Data
public class Action {
    ActionType type;
    ArrayList<Condition> refinements;

    public Action()
    {

    }
    public Action(ActionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String refinementBlock = this.getRefinementBlock();
        if(refinementBlock != "")
        {
            return  "    \"ids:action\": [{\n" +
                    "      \"rdf:value\": { \"@id\": \""+ type.getIdsAction() +"\" }" + refinementBlock + "\r\n" +
                    "    }]";
        }else {
            return "      \"ids:action\": [{\n" +
                    "        \"@id\":\"" + type.getIdsAction() +"\"\n" +
                    "      }]";
        }
    }

    private String getRefinementBlock() {

        String conditionInnerBlock = "";

        if (this.refinements != null)
        {

            String conditions = "";
            for(int i=0 ; i< this.refinements.size(); i++)
            {
                String tempString = this.refinements.get(i).toString();
                if(!tempString.isEmpty())
                {
                    if(conditions.isEmpty() && !tempString.isEmpty())
                    {
                        conditions = conditions.concat(tempString);

                    }else {
                        conditions = conditions.concat("," + tempString);
                    }
                }
            }

            if(!conditions.isEmpty()) {
                conditionInnerBlock = String.format(",     \r\n" +
                        "      \"ids:refinement\": [%s] ", conditions);
            }
        }

        return conditionInnerBlock;
    }

}
