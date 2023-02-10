package de.fraunhofer.iese.ids.odrl.policy.library.model;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.EntityType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import lombok.Data;


@Data
public class RightOperandEntity {
 private EntityType entityType;
 private String value;
 private RightOperandType dataType;
 private RightOperandEntity innerEntity;

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
        return "            \""+ entityType.getType() +"\": {\n" +
               "               \"@type\": \""+ this.dataType.getType() + "\",\n" +
                innerEntity.toString() + "\n" +
            "            }";

    }else {
        return "            \""+ entityType.getType() +"\": {\n" +
              "               \"@type\": \""+ this.dataType.getType() + "\",\n" +
              "               \"@value\": \""+ this.value +"\"\n" +
              "            }";
  }
 }

}
