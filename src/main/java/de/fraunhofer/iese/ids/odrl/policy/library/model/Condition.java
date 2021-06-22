package de.fraunhofer.iese.ids.odrl.policy.library.model;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ConditionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.LeftOperand;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.Operator;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.TimeUnit;
import lombok.Data;

@Data
public class Condition {
 ConditionType type;
 Operator operator;
 LeftOperand leftOperand;
 RightOperand rightOperand;
 String comment;
 String unit;
 String contract;
 String jsonPath;
 //ModificationMethodParameter replaceWith;

 public Condition()
 {

 }

 public Condition(ConditionType conditionType, String comment) {
  this.type = conditionType;
  this.comment = comment;
 }

 public Condition(ConditionType conditionType, LeftOperand leftOperand, Operator operator, RightOperand rightOperand, String comment) {
  this.type = conditionType;
  this.operator = operator;
  this.leftOperand = leftOperand;
  this.rightOperand= rightOperand;
  this.comment = comment;
 }

 @Override
 public String toString() {
  String contractBlock = getContractBlock();
  String unitBlock = getUnitBlock();
  String commentBlock = getCommentBlock();
  String PIPBlock = getPIPBlock();
  String jsonPathBlock = getJsonPathBlock();
  //String replaceWithBlock = getReplaceWithBlock();

  return  !rightOperand.toString().isEmpty() ?"{    \r\n" +
          "        \"@type\":\"ids:Constraint\",  \n" +
          "        \"ids:leftOperand\": { \"@id\": \""+ leftOperand.getIdsLeftOperand() +"\"},  \n" +
          "        \"ids:operator\": { \"@id\": \""+ operator.getOdrlOp() +"\"},  \n" +
          "        \"ids:rightOperand\": { " + rightOperand.toString() +
          "        }" +
          jsonPathBlock +
          contractBlock +
          unitBlock +
          commentBlock +
          PIPBlock +
          " \n"+
          "      }     \r\n":"";

 }

 private String getPIPBlock() {
  if(this.leftOperand != null && !this.operator.equals(Operator.DEFINES_AS))
  {
   return  ", \n"+
           "        \"ids:pipEndpoint\":{\n" +
           "          \"ids:pipInterfaceDescription\":{\n" +
           "            \"@value\":\"https://ids.org/PIP/interfaceDescription/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
           "            \"@type\":\"anyURI\"\n" +
           "          }, \n" +
           "          \"ids:accessURI\":{\n" +
           "            \"@value\":\"https://consumer.org/PXPendpoint/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
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

/* private String getReplaceWithBlock() {
  if(this.replaceWith != null && this.replaceWith.getValue() != null && !this.replaceWith.getValue().isEmpty())
  {
   return ", \n"+
           "        \"ids:replaceWith\" : { " + replaceWith.toString() +"}";
  }
  return "";
 }*/

 private String getUnitBlock() {
  if(this.unit != null)
  {
   return ", \n"+
           "        \"ids:unit\": \""+ unit +"\"";
  }
  return "";
 }
}
