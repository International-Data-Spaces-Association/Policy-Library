package de.fraunhofer.iese.ids.odrl.policy.library.model;

import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ActionType;
import lombok.Data;

@Data
public class Action {
	ActionType type;
	List<Condition> refinements;

	public Action() {

	}

	public Action(ActionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String odrl = "odrl";
		if(null != this.refinements)
		{
			return "      \"action\": [{ \n" + getRDFValue(odrl) + getRefinementBlock(odrl)  +"}]\n" + "\n" + "      ";
		}else{
			return "\"action\": \""+ type.getOdrlAction() + "\" \r\n";
		}
	}

	public String toIdsString() {
		String ids = "ids";
		if(null != this.refinements)
		{
			return "      \"ids:action\": [{ \n" + getRDFValue(ids) + getRefinementBlock(ids) + getPXPBlock() +"}]\n" + "      ";
		}else{
			return "      \"ids:action\": [{\n" + "        \"@id\":\"" + type.getIdsAction()  + "\"" + getPXPBlock()
					+ "\r\n" + "      }]";
		}
	}

	private String getRefinementBlock(String language) {

		String conditionInnerBlock = "";
		String conditionElement = language.equals("ids")? "ids:refinement": "refinement";
		if (this.refinements != null) {

			String conditions = "";
			for (int i = 0; i < this.refinements.size(); i++) {
				String tempString = language.equals("ids")? this.refinements.get(i).toIdsString() : this.refinements.get(i).toString() ;
				if (!tempString.isEmpty()) {
					if (conditions.isEmpty() && !tempString.isEmpty()) {
						conditions = conditions.concat(tempString);

					} else {
						conditions = conditions.concat("," + tempString);
					}
				}
			}

			if (!conditions.isEmpty()) {
				conditionInnerBlock = String.format(",     \r\n" + "        \"%s\": [%s] ", conditionElement, conditions);
			}
		}

		return conditionInnerBlock;
	}

	private String getRDFValue(String language) {
		return language.equals("ids")? "\"rdf:value\": { \"@id\": \""+ type.getIdsAction() +"\" }"
				: "\"rdf:value\": { \"@id\": \""+ type.getOdrlAction() +"\" }";
	}

	private String getPXPBlock() {
		if (this.type.getAbstractIdsAction().equals("DUTY")) {
			return ", \n" + "        \"ids:pxpEndpoint\":{\n" + "          \"@type\":\"ids:PXP\", \n"
					+ "          \"ids:interfaceDescription\":{\n"
					+ "            \"@value\":\"https://example.com/ids/PXP/interfaceDescription/"
					+ this.type.toString().toLowerCase() + "\", \n" + "            \"@type\":\"anyURI\"\n"
					+ "          }, \n" + "          \"ids:endpointURI\":{\n"
					+ "            \"@value\":\"https://example.com/ids/PXPendpoint/"
					+ this.type.toString().toLowerCase() + "\", \n" + "            \"@type\":\"anyURI\"\n"
					+ "          } \n" + "        }";

		}
		return "";
	}
}
