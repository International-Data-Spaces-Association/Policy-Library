package de.fraunhofer.iese.ids.odrl.policy.library.model;

import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandId;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.tooling.PatternUtil;
import lombok.Data;

@Data
public class RightOperand {
	private RightOperandId id;
	private String value;
	private RightOperandType type;
	private List<RightOperandEntity> rightOperandEntities;

//	Constructors
	//Empty Constructor
	public RightOperand() {
	}

	public RightOperand(RightOperandId id, String value, RightOperandType type,
			List<RightOperandEntity> rightOperandEntities) {
		this.id = id;
		this.value = value;
		this.type = type;
		this.rightOperandEntities = rightOperandEntities;
	}
	
	public RightOperand(RightOperandId id, String value, RightOperandType type) {
		this(id, value, type, null);
	}
	
	public RightOperand(String value, RightOperandType type) {
		this(null, value, type, null);
	}
	
	public RightOperand(List<RightOperandEntity> entities, RightOperandType type) {
		this(null, null, type, entities);
	}

	public RightOperand(RightOperandId id) {
		this(id, null, null, null);
	}

// End of Constructors
	
	public boolean hasRightOperandEntity() {
    	return !(null == rightOperandEntities || rightOperandEntities.isEmpty());
    }

	@Override
	public String toString() {
		if (null == this.id) {
			if (null == this.value) {
				return "";
			} else {
				return "{\"@value\": \"" + value + "\", \"@type\": \"" + type.getType() + "\"}";
			}
		} else {
			if (null == this.value) {
				return "{\"@id\": \"" + id.getOdrlRepresentation() + "\"}";
			}
		}
		return "{\"@id\": \"" + id.getOdrlRepresentation() + "\"@value\": \"" + value + "\", \"@type\": \""
				+ type.getType() + "\"}";
	}

	public String toIdsString() {
		if (null == this.id) {
			if (PatternUtil.isEmpty(rightOperandEntities)) {
				if (null == this.value) {
					return "";
				} else {
					return "{\"@value\": \"" + value + "\", \"@type\": \"" + type.getType() + "\"}";
				}
			} else {
				if (this.hasRightOperandEntity()) {
					return getEntitiesBlock();
				}
			}
		} else {
			if (null == this.rightOperandEntities) {
				if (null == this.value) {
					return "{\"@id\": \"" + id.getIdsRepresentation() + "\"}";
				}
			}
		}
		return "{\"@id\": \"" + id.getIdsRepresentation() + "\"@value\": \"" + value + "\", \"@type\": \""
				+ type.getType() + "\"}";
	}

	private String getEntitiesBlock() {		
		String entitiesBlock = "";

		if (this.hasRightOperandEntity()) {
			StringBuilder stringBuilder = new StringBuilder();
			for(RightOperandEntity rightOperandEntity : rightOperandEntities) {
				if(null != rightOperandEntity.getValue() || null != rightOperandEntity.getInnerEntity()) {
					
					boolean isNotFirstElement = stringBuilder.toString().isEmpty() && rightOperandEntities.size() > 1;
					
					stringBuilder.append(rightOperandEntity.toString());
					
					if(isNotFirstElement) {
						stringBuilder.append(", ").append(System.lineSeparator());
					}				
				}
			}
			if (!stringBuilder.toString().isEmpty()) {
				entitiesBlock = entitiesBlock.concat(String.format(
						"{\r\n" + "         \"@type\": \"" + type.getType() + "\", \r\n" + "%s \n" + "       }", stringBuilder.toString()));
			}
		}
		return entitiesBlock;
	}
	

}
