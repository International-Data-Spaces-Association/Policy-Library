package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.util.ArrayList;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.LogLevelType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandId;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import lombok.Data;


@Data
public class RightOperand {
 RightOperandId id;
 String value;
 RightOperandType type;
 ArrayList<RightOperandEntity> entities;

 public RightOperand()
 {

 }

 public RightOperand(RightOperandId id)
 {
  this.id = id;
 }

 public RightOperand(String value, RightOperandType type)
 {
  this.value = value;
  this.type = type;
 }

    public RightOperand(RightOperandId id, String value, RightOperandType type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public RightOperand(ArrayList<RightOperandEntity> entities, RightOperandType type) {
        this.entities = entities;
        this.type = type;
    }

    @Override
 public String toString() {
   if(this.id == null && this.value == null && this.entities == null && this.type != null)
   {
    return "";
   } else if(this.id != null && this.entities == null && this.value == null && this.type == null)
   {
    return "\"@id\": \""+ id.getIdsRightOperand() +"\"";
   }else if(this.id == null && this.value == null && this.type != null && this.entities != null && !this.entities.isEmpty())
   {
       return getEntitiesBlock();
   }else if(this.id == null && this.entities == null && this.value != null && this.type != null && this.type.equals(RightOperandType.DATETIMESTAMP))
   {
       return "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"";
   }else if(this.id == null && this.entities == null && this.value != null && this.type != null)
   {
    return "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"";
   }else if(this.id != null && this.entities == null && this.value != null && this.type != null && this.type.equals(RightOperandType.DATETIMESTAMP))
   {
       return "\"@id\": \""+ id.getIdsRightOperand() + "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"";
   }else
   {
    return "\"@id\": \""+ id.getIdsRightOperand() + "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"";
   }
 }

    private String getEntitiesBlock() {
        String entitiesBlock = "";

        if(this.entities != null && this.entities.size() > 0)
        {
            int i=0;
            String temp= "";
            while (i < this.entities.size())
            {
                  if(this.entities.get(i).getValue() != null)
                {
                    if(temp.isEmpty())
                    {
                        temp = this.entities.get(i).toString();
                    }else {
                        temp = temp.concat(", \n" + this.entities.get(i).toString());
                    }
                }
                i++;
            }
            if(!temp.isEmpty()){
                entitiesBlock = entitiesBlock.concat(String.format( "\r\n" +
                        "         \"@type\": \""+ type.getType() +"\", \r\n" +
                        "         \"@value\": { \n %s \n" +
                        "         } \n" , temp));
            }

        }

        return entitiesBlock;
    }

}
