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
  String rightOperandBlock = getRightOperandBlock();
  String contractBlock = getContractBlock();
  String unitBlock = getUnitBlock();
  String commentBlock = getCommentBlock();
  String PIPBlock = getPIPBlock();
  String jsonPathBlock = getJsonPathBlock();
  //String replaceWithBlock = getReplaceWithBlock();

  return  !rightOperands.isEmpty() ?"{    \r\n" +
          "        \"@type\":\"ids:Constraint\",  \n" +
          "        \"ids:leftOperand\": { \"@id\": \""+ leftOperand.getIdsLeftOperand() +"\"},  \n" +
          "        \"ids:operator\": { \"@id\": \""+ operator.getIdsOp()  +"\"}" +
          rightOperandBlock +
          jsonPathBlock +
          contractBlock +
          unitBlock +
          commentBlock +
          PIPBlock +
          " \n"+
          "      }     \r\n":"";

 }
 
 public String toOdrlString() {
	  String rightOperandBlock = getOdrlRightOperandBlock();
	  String contractBlock = getContractBlock();
	  String unitBlock = getUnitBlock();
	  String commentBlock = getCommentBlock();
	  String PIPBlock = getOdrlPIPBlock();
	  String jsonPathBlock = getJsonPathBlock();
	  //String replaceWithBlock = getReplaceWithBlock();

	  return  !rightOperands.isEmpty() ?"{    \r\n" +
	          "        \"leftOperand\": \""+ leftOperand.getOdrlLeftOperand() +"\",  \n" +
	          "        \"operator\": \""+ operator.getOdrlOp()  +"\"" +
	          rightOperandBlock +
	          jsonPathBlock +
	          contractBlock +
	          unitBlock +
	          commentBlock +
	          PIPBlock +
	          " \n"+
	          "      }     \r\n":"";

	 }

 private String getRightOperandBlock() {
  String rightOperandBlock = "";

  if(this.rightOperands != null && this.rightOperands.size() > 0)
  {
   String temp= "";
   temp = this.rightOperands.get(0).toString();
   if(this.rightOperands.size() > 1)
   {
    for (int i = 1; i < this.rightOperands.size(); i++)
    {
     temp = temp.concat(", \n" + this.rightOperands.get(i).toString());
    }
   }
   rightOperandBlock = String.format(", \r\n" + "      \"ids:rightOperand\": [%s] \n" , temp);
  }

  return rightOperandBlock;
 }
 
 private String getOdrlRightOperandBlock() {
	  String rightOperandBlock = "";

	  if(this.rightOperands != null && this.rightOperands.size() > 0)
	  {
	   String temp= "";
	   temp = this.rightOperands.get(0).toString();
	   if(this.rightOperands.size() > 1)
	   {
	    for (int i = 1; i < this.rightOperands.size(); i++)
	    {
	     temp = temp.concat(", \n" + this.rightOperands.get(i).toString());
	    }
	   }
	   rightOperandBlock = String.format(", \r\n" + "      \"rightOperand\": [%s] \n" , temp);
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

 private String getOdrlPIPBlock() {
	  if(this.leftOperand != null && !this.operator.equals(Operator.DEFINES_AS))
	  {
	   return  ", \n"+
	           "        \"ids:pipEndpoint\":{\n" +
	           "          \"@type\":\"ids:PIP\", \n" +
	           "          \"ids:interfaceDescription\":{\n" +
	           "            \"@value\":\"https://example.com/ids/PIP/interfaceDescription/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
	           "            \"@type\":\"xsd:anyURI\"\n" +
	           "          }, \n" +
	           "          \"ids:endpointURI\":{\n" +
	           "            \"@value\":\"https://example.com/ids/PXPendpoint/"+ this.leftOperand.toString().toLowerCase() + "\", \n" +
	           "            \"@type\":\"xsd:anyURI\"\n" +
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
