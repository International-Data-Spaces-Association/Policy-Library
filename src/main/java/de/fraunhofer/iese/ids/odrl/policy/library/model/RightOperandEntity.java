package de.fraunhofer.iese.ids.odrl.policy.library.model;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.EntityType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandId;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.TimeUnit;
import lombok.Data;


@Data
public class RightOperandEntity {
 EntityType entityType;
 String value;
 RightOperandType dataType;
 RightOperandEntity innerEntity;

 public RightOperandEntity()
 {

 }

    public RightOperandEntity(EntityType entityType, String value,  RightOperandType dataType) {
        this.entityType = entityType;
        this.value = value;
        this.dataType = dataType;
    }

    public RightOperandEntity(EntityType entityType, RightOperandEntity innerEntity,  RightOperandType dataType) {
        this.entityType = entityType;
        this.innerEntity = innerEntity;
        this.dataType = dataType;
    }

    @Override
 public String toString() {


  if(null != this.innerEntity)
    {
        return "            \""+ entityType.getOdrlRuleType() +"\": {\n" +
               "               \"@type\": \""+ this.dataType.getType() + "\",\n" +
                innerEntity.toString() + "\n" +
            "            }";

    }else {
        return "            \""+ entityType.getOdrlRuleType() +"\": {\n" +
              "               \"@type\": \""+ this.dataType.getType() + "\",\n" +
              "               \"@value\": \""+ this.value +"\"\n" +
              "            }";
  }
 }

}
