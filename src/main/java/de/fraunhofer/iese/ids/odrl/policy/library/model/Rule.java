package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.util.ArrayList;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import lombok.Data;


@Data
public class Rule {
 RuleType type;
 Action action;
 ArrayList<Condition> constraints;
 ArrayList<Rule> preduties;
 ArrayList<Rule> postduties;

 public Rule()
 {

 }

 public Rule(RuleType type, Action action)
 {
  this.type = type;
  this.action = action;
 }

 @Override
 public String toString() {
  String PXPBlock = getPXPBlock();
  return  "{    \r\n" +
          getIdsType() +
          action.toString() +
          getPreobligationBlock() +
          getConstraintBlock() +
          getPostobligationBlock() +
          PXPBlock +
          "\r\n" +
          "  }";
 }

 private String getIdsType() {
  if(this.type.equals(RuleType.POSTDUTY) || this.type.equals(RuleType.PREDUTY))
  {
   return           "      \"@type\":\"ids:Duty\",  \n" ;
  }
  return "";
 }

 private String getPXPBlock() {
  if(this.type.equals(RuleType.OBLIGATION) || this.type.equals(RuleType.POSTDUTY) || this.type.equals(RuleType.PREDUTY))
  {
   if(this.action != null)
   {
    return ", \n"+
            "        \"ids:pxpEndpoint\": { \"@id\": \"https//example.com/pxp/" + this.action.getType().toString().toLowerCase() + "\" }";

   }
  }
  return "";
 }

 private String getPreobligationBlock() {
  String preobligationBlock = "";

  if(this.preduties != null && this.preduties.size() > 0)
  {
   String temp= "";
   temp = this.preduties.get(0).toString();
   if(this.preduties.size() > 1)
   {
    for (int i = 1; i < this.preduties.size(); i++)
    {
     temp = temp.concat(", \n" + this.preduties.get(i).toString());
    }
   }
   preobligationBlock = String.format(", \r\n" + "    \"ids:preDuty\": [%s] \n" , temp);
  }

  return preobligationBlock;
 }

 private String getConstraintBlock() {

  String conditionInnerBlock = "";

  if (this.constraints != null)
  {

   String conditions = "";
   for(int i=0 ; i< this.constraints.size(); i++)
   {
    String tempString = this.constraints.get(i).toString();
    if(!tempString.isEmpty())
    {
     if(conditions.isEmpty() && !tempString.isEmpty())
     {
      conditions = conditions.concat(tempString);

     }else {
      conditions = conditions.concat("," + tempString);
     }
    }
   }

  if(!conditions.isEmpty()){
   conditionInnerBlock = String.format(",     \r\n" +
           "      \"ids:constraint\": [%s] " , conditions);
  }

  }

  return conditionInnerBlock;
 }

 private String getPostobligationBlock() {
  String postobligationBlock = "";

  if(this.postduties != null && this.postduties.size() > 0)
  {
   String temp= "";
   temp = this.postduties.get(0).toString();
   if(this.postduties.size() > 1)
   {
    for (int i = 1; i < this.postduties.size(); i++)
    {
     temp = temp.concat(", \n" + this.postduties.get(i).toString());
    }
   }
   postobligationBlock = postobligationBlock.concat(String.format(", \r\n" +"    \"ids:postDuty\": [%s] \n" , temp));
  }

  return postobligationBlock;
 }

}
