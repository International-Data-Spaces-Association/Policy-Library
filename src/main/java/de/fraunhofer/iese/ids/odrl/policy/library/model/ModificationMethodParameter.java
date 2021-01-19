package de.fraunhofer.iese.ids.odrl.policy.library.model;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import lombok.Data;


@Data
public class ModificationMethodParameter {
 String value;
 RightOperandType type;

 public ModificationMethodParameter()
 {

 }

 public ModificationMethodParameter(String value, RightOperandType type)
 {
  this.value = value;
  this.type = type;
 }

    @Override
 public String toString() {
   if(this.value == null || this.value.isEmpty())
   {
    return "";
   }else
   {
    return "\"@value\": \""+ value +"\", \"@type\": \""+ type.getType() +"\"";
   }
 }

}
