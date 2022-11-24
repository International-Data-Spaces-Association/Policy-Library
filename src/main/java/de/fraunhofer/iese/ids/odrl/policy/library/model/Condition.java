package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ConditionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.LeftOperand;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.Operator;
import lombok.Data;

@Data
public class Condition {
 ConditionType type;
 Operator operator;
 LeftOperand leftOperand;
 List<RightOperand> rightOperands;
 String comment;
 String unit;
 String contract;
 String jsonPath;
 //ModificationMethodParameter replaceWith;

 
 //Constructors
 public Condition()
 {

 }

 public Condition(ConditionType conditionType, LeftOperand leftOperand, Operator operator, List<RightOperand> rightOperands, String comment) {
  this.type = conditionType;
  this.operator = operator;
  this.leftOperand = leftOperand;
  this.rightOperands= rightOperands;
  this.comment = comment;
 }
 
 public Condition(ConditionType conditionType, LeftOperand leftOperand, Operator operator, List<RightOperand> rightOperands) {
	 this(conditionType, leftOperand, operator, rightOperands, "");
 }
 
 public Condition(ConditionType conditionType, String comment) {
	 this(conditionType, null, null, null, comment);
 }

 //End of constructors
 
 @Override
 public String toString() {
    String odrl = "odrl";
  return  !rightOperands.isEmpty() ?"{    \r\n" +
          "        \"leftOperand\": \""+ leftOperand.getOdrlLeftOperand() +"\"," +
          "        \"operator\": \""+ operator.getOdrlOp() +"\"" +
          getRightOperandBlock(odrl) +
          getJsonPathBlock() +
          getUnitBlock(odrl) +
          " \n"+
          "      }     \r\n":"";

 }

    public String toIdsString() {
     String ids = "ids";
        return  !rightOperands.isEmpty() ?"{    \r\n" +
                "        \"@type\":\"ids:Constraint\",  \n" +
                "        \"ids:leftOperand\": { \"@id\": \""+ leftOperand.getIdsLeftOperand() +"\"},  \n" +
                "        \"ids:operator\": { \"@id\": \""+ operator.getIdsOp()  +"\"}" +
                getRightOperandBlock(ids) +
                getJsonPathBlock() +
                getContractBlock() +
                getUnitBlock(ids) +
                getCommentBlock() +
                getPIPBlock() +
                " \n"+
                "      }     \r\n":"";

    }

 private String getRightOperandBlock(String language) {
  String rightOperandBlock = "";
  String rightOperandElement = language.equals("ids")? "ids:rightOperand": "rightOperand";
  if(this.rightOperands != null && this.rightOperands.size() > 0)
  {
   String temp = language.equals("ids")? this.rightOperands.get(0).toIdsString():this.rightOperands.get(0).toString();
   if(this.rightOperands.size() > 1)
   {
    for (int i = 1; i < this.rightOperands.size(); i++)
    {
        if(language.equals("ids"))
        {
            temp = temp.concat(", \n" + this.rightOperands.get(i).toIdsString());
        }else{
            temp = temp.concat(", \n" + this.rightOperands.get(i).toString());
        }
    }
   }
   rightOperandBlock = String.format(", \r\n" + "      \"%s\": [%s] \n" , rightOperandElement, temp);
  }

  return rightOperandBlock;
 }

 private String getPIPBlock() {
  if(this.leftOperand != null && !this.operator.equals(Operator.DEFINES_AS))
  {
   return  ", \n"+
           "        \"ids:pipEndpoint\":{\n" +
           "          \"@type\":\"ids:PIP\", \n" +
           "          \"ids:interfaceDescription\":{\n" +
           "            \"@value\":\"https://example.com/ids/PIP/interfaceDescription/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
           "            \"@type\":\"anyURI\"\n" +
           "          }, \n" +
           "          \"ids:endpointURI\":{\n" +
           "            \"@value\":\"https://example.com/ids/PXPendpoint/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
           "            \"@type\":\"anyURI\"\n" +
           "          } \n" +
           "        }";
  }
  return "";
 }
 
 private String getCommentBlock() {
  if(this.comment != null)
  {
   return ", \n"+
           "        \"ids:comment\": \" "+ comment +" \"";
  }
  return "";
 }

 private String getContractBlock() {
  if(this.contract != null)
  {
   return ", \n"+
           "        \"ids:contract\": \""+ contract +"\"";
  }
  return "";
 }

 private String getJsonPathBlock() {
  if(this.jsonPath != null)
  {
   return ", \n"+
           "        \"ids:jsonPath\": \""+ jsonPath +"\"";
  }
  return "";
 }

 private String getUnitBlock(String language) {
     String unitElement = language.equals("ids")? "ids:unit":"unit";
  if(this.unit != null)
  {
   return ", \n"+
           "        \""+ unitElement +"\": \""+ unit +"\"";
  }
  return "";
 }
}
