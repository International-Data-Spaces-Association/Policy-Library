package de.fraunhofer.iese.ids.odrl.policy.library.model;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PartyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PolicyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.TargetType;
import lombok.Data;

import java.net.URI;
import java.util.List;

@Data
public class Target {
    TargetType targetType;
    URI uri;
    List<Condition> refinements;

    public Target(URI uri) {
        this.uri = uri;
    }

    public Target(TargetType targetType, URI uri) {
        this.targetType = targetType;
        this.uri = uri;
    }

    @Override
    public String toString() {
        if (null != this.targetType && this.targetType.equals(TargetType.ASSET_COLLECTION))
        {
            if(null != this.refinements)
            {
                return  "      \"target\": {\n" +
                        "          \"@type\": \""+ this.targetType.getOdrlRepresentation() +"\"," +
                        "          \"source\":\"" + this.uri.toString() + "\"\n" +
                        getRefinementBlock() +
                        "       },    \r\n";
            }else{
                return  "      \"target\": {\n" +
                        "          \"@type\": \""+ this.targetType.getOdrlRepresentation() +"\"," +
                        "          \"uid\":\"" + this.uri.toString() + "\"\n" +
                        "       },    \r\n";
            }
        }else{
            return "      \"target\": \"" + this.uri.toString() + "\", \r\n";
        }
    }

    public String toIdsString() {
        return  "      \"ids:target\": {\n" +
                "          \"@id\":\"" + this.uri.toString() + "\"\n" +
                "       },    \r\n";
    }

    private String getRefinementBlock() {

        String conditionInnerBlock = "";

        if (this.refinements != null) {

            String conditions = "";
            for (int i = 0; i < this.refinements.size(); i++) {
                String tempString = this.refinements.get(i).toString();
                if (!tempString.isEmpty()) {
                    if (conditions.isEmpty() && !tempString.isEmpty()) {
                        conditions = conditions.concat(tempString);

                    } else {
                        conditions = conditions.concat("," + tempString);
                    }
                }
            }

            if (!conditions.isEmpty()) {
                conditionInnerBlock = String.format(",     \r\n" + "        \"refinement\": [%s] ", conditions);
            }
        }

        return conditionInnerBlock;
    }
}
