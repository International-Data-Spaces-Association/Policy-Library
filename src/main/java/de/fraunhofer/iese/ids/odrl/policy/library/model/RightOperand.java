package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandId;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import lombok.Data;


@Data
public class RightOperand {
 RightOperandId id;
 String value;
 RightOperandType type;
 List<RightOperandEntity> entities;

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

    public RightOperand(RightOperandId id, String value, RightOperandType type, List<RightOperandEntity> entities) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.entities = entities;
    }

    public RightOperand(List<RightOperandEntity> entities, RightOperandType type) {
        this.entities = entities;
        this.type = type;
    }

    @Override
 public String toString() {
     if(null == this.id){
         if(null == this.entities){
             if(null == this.value) {
                 return "";
             }else{
                 return "{\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
             }
         }else{
             if(!this.entities.isEmpty())
             {
                 return getEntitiesBlock();
             }
         }
     }else{
         if(null == this.entities)
         {
             if(null == this.value)
             {
                 return "{\"@id\": \""+ id.getIdsRightOperand() +"\"}";
             }
         }
     }
     return "{\"@id\": \""+ id.getIdsRightOperand() + "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
   /*
   if(this.id == null && this.value == null && this.entities == null && this.type != null)
   {
       //done
    return "";
   } else if(this.id != null && this.entities == null && this.value == null && this.type == null)
   {
       //done
    return "{\"@id\": \""+ id.getIdsRightOperand() +"\"}";
   }else if(this.id == null && this.value == null && this.type != null && this.entities != null && !this.entities.isEmpty())
   {
       //done
       return getEntitiesBlock();
   }else if(this.id == null && this.entities == null && this.value != null && this.type != null && this.type.equals(RightOperandType.DATETIMESTAMP))
   {
       //removed
       return "{\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
   }else if(this.id == null && this.entities == null && this.value != null && this.type != null)
   {
       //done
    return "{\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
   }else if(this.id != null && this.entities == null && this.value != null && this.type != null && this.type.equals(RightOperandType.DATETIMESTAMP))
   {
       //removed
       return "{\"@id\": \""+ id.getIdsRightOperand() + "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
   }else
   {
    return "{\"@id\": \""+ id.getIdsRightOperand() + "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"}";
   }
   */
 }

    private String getEntitiesBlock() {
        String entitiesBlock = "";

        if(this.entities != null && this.entities.size() > 0)
        {
            int i=0;
            String temp= "";
            while (i < this.entities.size())
            {
                  if((this.entities.get(i).getValue() != null) || this.entities.get(i).getInnerEntity() != null)
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
                entitiesBlock = entitiesBlock.concat(String.format( "{\r\n" +
                        "         \"@type\": \""+ type.getType() +"\", \r\n" +
                        "%s \n" +
                        "       }" , temp));
            }

        }

        return entitiesBlock;
    }

}
