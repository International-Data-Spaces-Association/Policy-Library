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
 TimeUnit timeUnit;

 public RightOperandEntity()
 {

 }

public RightOperandEntity(EntityType entityType, String value,  RightOperandType dataType) {
    this.entityType = entityType;
    this.value = value;
    this.dataType = dataType;
}

    @Override
 public String toString() {


  if(this.dataType.equals(RightOperandType.DATETIMESTAMP))
    {
        return "            \""+ entityType.getOdrlRuleType() +"\": {\n" +
            "               \"@value\": \""+ this.value +"Z\",\n" +
            "               \"@type\": \""+ this.dataType.getType() + "\"\n" +
            "            }";

    }else {
        return "            \""+ entityType.getOdrlRuleType() +"\": {\n" +
              "               \"@value\": \""+ this.value +"\",\n" +
              "               \"@type\": \""+ this.dataType.getType() + "\"\n" +
              "            }";
  }
 }

}
