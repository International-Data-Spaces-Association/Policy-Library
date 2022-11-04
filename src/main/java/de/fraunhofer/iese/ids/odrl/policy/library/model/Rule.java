package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.net.URI;
import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import lombok.Data;


@Data
public class Rule {
private RuleType ruleType;
private URI target;
private Action action;
private List<Condition> constraints;
private List<Rule> preduties;
private List<Rule> postduties;

 public Rule()
 {

 }

 public Rule(RuleType type,URI target, Action action)
 {
  this.ruleType = type;
  this.target = target;
  this.action = action;
 }

 // to create a duty rule
 public Rule(RuleType type, Action action)
 {
  this.ruleType = type;
  this.action = action;
 }

 @Override
 public String toString() {
  return  "{    \r\n" +
          getIdsType() +
          action.toString() +
          getPreobligationBlock() +
          getConstraintBlock() +
          getPostobligationBlock() +
          "\r\n" +
          "  }";
 }
 
 public String toOdrlString() {
	  return  "{    \r\n" +
	          getOdrlType() +
	          action.toOdrlString() +
	          getOdrlPreobligationBlock() +
	          getOdrlConstraintBlock() +
	          getOdrlPostobligationBlock() +
	          "\r\n" +
	          "  }";
	 }
 
 public String toOdrlDutyString() {
	  return  "{    \r\n" +
	          getOdrlType() +
	          action.toOdrlDutyString() +
	          getOdrlPreobligationBlock() +
	          getOdrlConstraintBlock() +
	          getOdrlPostobligationBlock() +
	          "\r\n" +
	          "  }";
	 }
 
	
 private String getIdsType() {
  if(this.ruleType.equals(RuleType.POSTDUTY) || this.ruleType.equals(RuleType.PREDUTY))
  {
   return           "      \"@type\":\"ids:Duty\",  \n" ;
  }else if(target != null){
   //only return the target
   return  "      \"ids:target\": {\n" +
           "          \"@id\":\"" + this.target.toString() + "\"\n" +
           "       },    \r\n";
  }
  return "";
 }
 
 private String getOdrlType() {
	 if(target != null){
	   //only return the target
	   return  "      \"target\":" +
	           "          \"" + this.target.toString() + "\",\n" +
	           "       \r\n";
	  }
	  return "";
	 }

 
 private String getOdrlPreobligationBlock() {
	  String preobligationBlock = "";

	  if(this.preduties != null && this.preduties.size() > 0)
	  {
	   String temp= "";
	   temp = this.preduties.get(0).toOdrlDutyString();
	   if(this.preduties.size() > 1)
	   {
	    for (int i = 1; i < this.preduties.size(); i++)
	    {
	     temp = temp.concat(", \n" + this.preduties.get(i).toOdrlDutyString());
	    }
	   }
	   preobligationBlock = String.format(", \r\n" + "    \"duty\": [%s] \n" , temp);
	  }

	  return preobligationBlock;
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
 
 private String getOdrlConstraintBlock() {

	  String conditionInnerBlock = "";

	  if (this.constraints != null)
	  {

	   String conditions = "";
	   for(int i=0 ; i< this.constraints.size(); i++)
	   {
	    String tempString = this.constraints.get(i).toOdrlString();
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
	           "      \"constraint\": [%s] " , conditions);
	  }

	  }

	  return conditionInnerBlock;
	 }

 
 private String getOdrlPostobligationBlock() {
	  String postobligationBlock = "";

	  if(this.postduties != null && this.postduties.size() > 0)
	  {
	   String temp= "";
	   temp = this.postduties.get(0).toOdrlDutyString();
	   if(this.postduties.size() > 1)
	   {
	    for (int i = 1; i < this.postduties.size(); i++)
	    {
	     temp = temp.concat(", \n" + this.postduties.get(i).toOdrlDutyString());
	    }
	   }
	   postobligationBlock = postobligationBlock.concat(String.format(", \r\n" +"    \"duty\": [%s] \n" , temp));
	  }

	  return postobligationBlock;
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
