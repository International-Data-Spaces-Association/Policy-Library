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
		String PXPBlock = getPXPBlock();
		String refinementBlock = this.getRefinementBlock();
		return "      \"ids:action\": [{\n" + "        \"@id\":\"" + type.getIdsAction() + "\"" + refinementBlock
				+ PXPBlock + "\r\n" + "      }]";
	}

	public String toOdrlString() {
		String PXPBlock = getPXPBlock();
		String refinementBlock = this.getRefinementBlock();
		return "      \"action\": \n" + "       \"" + type.getOdrlAction() + "\"" + refinementBlock + PXPBlock + "\n"
				+ "      ";
	}

	public String toOdrlDutyString() {
		String rdfValue = getRDFValue();
		String refinementBlock = this.getDutyRefinementBlock();
		String constraint = getDutyConstraint();
		return "      \"action\": [{ \n" + rdfValue + refinementBlock +"}]\n"+ constraint + "\n" + "      ";
	}

	private String getDutyConstraint() {

		return ", \n" + "            \"constraint\": [{\n" + "               \"leftOperand\": \"event\",\n"
				+ "               \"operator\": \"lt\",\n"
				+ "               \"rightOperand\": { \"@id\": \"odrl:policyUsage\" }\n" + "           }]";
	}

	private String getDutyRefinementBlock() {

		String conditionInnerBlock = "";

		if (this.refinements != null) {

			String conditions = "";
			for (int i = 0; i < this.refinements.size(); i++) {
				String tempString = this.refinements.get(i).toOdrlString();
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
				conditionInnerBlock = String.format(",     \r\n" + "        \"ids:refinement\": [%s] ", conditions);
			}
		}

		return conditionInnerBlock;
	}

	private String getRDFValue() {
		if (this.type.getAbstractIdsAction().equals("DUTY")) {
			return "        \"rdf:value\": {\"@id\"" + ":\"idsc:" + this.type.toString().toLowerCase() + "\"}";
		}
		return "";
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
