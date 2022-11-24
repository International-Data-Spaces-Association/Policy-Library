package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.*;
import de.fraunhofer.iese.ids.odrl.policy.library.model.tooling.PatternUtil;
import lombok.Data;


@Data
public class Rule {
private RuleType ruleType;
private Target target;
private Action action;
private List<Condition> constraints;
private List<Rule> duties;

 public Rule()
 {

 }

 public Rule(RuleType type, Target target, Action action)
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
     String odrl = "odrl";
  return  "{    \r\n" +
          addDutyOrTarget(odrl) +
          action.toString() +
          getDutyBlock("odrl") +
          getConstraintBlock(odrl) +
          "\r\n" +
          "  }";
 }
 
 public String toIdsString() {
     String ids = "ids";
	  return  "{    \r\n" +
              addDutyOrTarget(ids) +
	          action.toIdsString() +
              getDutyBlock("ids") +
              getConstraintBlock(ids) +
	          "\r\n" +
	          "  }";
	 }
 
	
 private String addDutyOrTarget(String language) {
  if(this.ruleType.equals(RuleType.POST_DUTY) || this.ruleType.equals(RuleType.PRE_DUTY))
  {
   return language.equals("ids")? "      \"@type\":\"ids:Duty\",  \n": "";
  }else if(target != null){
   //only return the target
   return language.equals("ids")? target.toIdsString():target.toString();
  }
  return "";
 }

 private String getDutyBlock(String language) {
    String dutyBlock = "";

     String dutyElement = "";
     if(language.equals("ids"))
    {
        dutyElement = this.ruleType.equals(RuleType.POST_DUTY)? "ids:postDuty": "ids:preDuty";
    }else{
         dutyElement = "duty";
    }

    if(this.duties != null && this.duties.size() > 0)
    {
        String temp = language.equals("ids")? this.duties.get(0).toIdsString():this.duties.get(0).toString();
        if(this.duties.size() > 1)
        {
            for (int i = 1; i < this.duties.size(); i++)
            {
                if(language.equals("ids"))
                {
                    temp = temp.concat(", \n" + this.duties.get(i).toIdsString());
                }else{
                    temp = temp.concat(", \n" + this.duties.get(i).toString());
                }
            }
        }
        dutyBlock = dutyBlock.concat(String.format(", \r\n" +"    \"%s\": [%s] \n" , dutyElement, temp));
    }

    return dutyBlock;
 }

 private String getConstraintBlock(String language) {

  String conditionInnerBlock = "";
  String conditionElement = language.equals("ids")? "ids:constraint": "constraint";
  if (this.constraints != null)
  {

   String conditions = "";
   for(int i=0 ; i< this.constraints.size(); i++)
   {
       String tempString = language.equals("ids")? this.constraints.get(i).toIdsString():this.constraints.get(i).toString();
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

   if(language.equals("odrl"))
   {
       if(ruleType.equals(RuleType.PRE_DUTY))
       {
           Condition policyUsageCondition = getPolicyUsageCondition(Operator.LT);
           conditions = conditions.concat(policyUsageCondition.toString());
       }else if(ruleType.equals(RuleType.POST_DUTY))
       {
           Condition policyUsageCondition = getPolicyUsageCondition(Operator.GT);
           conditions = conditions.concat(policyUsageCondition.toString());
       }
   }

  if(!conditions.isEmpty()){
   conditionInnerBlock = String.format(",     \r\n" +
           "      \"%s\": [%s] " , conditionElement, conditions);
  }

  }

  return conditionInnerBlock;
 }

    private Condition getPolicyUsageCondition(Operator operator) {
        RightOperand policyUsageRightOperand = new RightOperand(RightOperandId.POLICY_USAGE);
        ArrayList<RightOperand> rightOperands = new ArrayList<>();
        rightOperands.add(policyUsageRightOperand);

        return new Condition(ConditionType.CONSTRAINT, LeftOperand.EVENT, operator, rightOperands);
    }

    private String getPolicyUsageConstraint() {

        return ", \n" + "            \"constraint\": [{\n" + "               \"leftOperand\": \"event\",\n"
                + "               \"operator\": \"lt\",\n"
                + "               \"rightOperand\": { \"@id\": \"odrl:policyUsage\" }\n" + "           }]";
    }

}
