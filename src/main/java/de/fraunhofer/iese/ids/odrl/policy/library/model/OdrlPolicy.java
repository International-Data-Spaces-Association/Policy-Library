package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PartyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PolicyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.interfaces.IPolicy;
import lombok.Data;


@Data
public class OdrlPolicy implements IPolicy {
 List<Rule> rules;
 URI profile;
 URI policyId;
 PolicyType type;
 Party consumer;
 Party provider;
 boolean providerSide;

 public OdrlPolicy()
 {
  this.provider = new Party(PartyType.PROVIDER, URI.create("http://example.com/party/my-party"));
 }

 public OdrlPolicy(URI policyId, PolicyType type, Party consumer, boolean providerSide) {
  this.policyId = policyId;
  this.type = type;
  this.consumer = consumer;
  this.provider = new Party(PartyType.PROVIDER, URI.create("http://example.com/party/my-party"));
  this.providerSide = providerSide;
 }

 @Override
 public String toString() {
	 return  " {    \r\n" +
	          "   \"@context\":[\n" +
	          " \"http://www.w3.org/ns/odrl.jsonld\",\n" +
	          "      { \"dc\": \"http://purl.org/dc/terms/\",\n" +
	          "\"ids\":\"https://w3id.org/idsa/core/\",\n" +
	          "      \"idsc\" : \"https://w3id.org/idsa/code/\"}," +
	          "    \n],    \r\n" +
	          "  \"@type\": \"" + this.type.getOdrlRepresentation() +"\",    \r\n" +
	          "  \"uid\": \"" + this.policyId.toString() +"\",    \r\n" +
	          "  \"profile\": \"http://example.com/odrl:profile\",   \r\n" +
	          getRulesBlock("odrl") +
	          "} ";
 }

 public String toIdsString() {
  return  " {    \r\n" +
          "   \"@context\": {\n" +
          "      \"ids\":\"https://w3id.org/idsa/core/\",\n" +
          "      \"idsc\" : \"https://w3id.org/idsa/code/\"\n" +
          "   },    \r\n" +
          "  \"@type\": \"" + this.type.getIdsRepresentation() +"\",    \r\n" +
          "  \"@id\": \"" + this.policyId.toString() +"\",    \r\n" +
          "  \"profile\": \"http://example.com/ids-profile\",   \r\n" +
          getIdsProviderBlock() +
          getIdsConsumerBlock() +
          getRulesBlock("ids") +
          "} ";
 }

 private String getIdsConsumerBlock() {
  if(null != this.consumer && !this.type.equals(PolicyType.OFFER)) {
   return this.consumer.toString();
  }
  return "";
 }

 private String getIdsProviderBlock() {
  if(null != this.provider && !this.type.equals(PolicyType.REQUEST)) {
   return this.provider.toString();
  }
  return "";
 }

 private String getRulesBlock(String language) {
  String rulesBlock = "";
  String permissionBlock = "";
  String prohibitionBlock = "";
  String obligationBlock = "";

  String permissionElement = language == "ids"? "ids:permission": "permission";
  String prohibitionElement = language == "ids"? "ids:prohibition": "prohibition";
  String obligationElement = language == "ids"? "ids:obligation": "obligation";

  List<Rule> permissionList = new ArrayList<>();
  List<Rule> prohibitionList = new ArrayList<>();
  List<Rule> obligationList = new ArrayList<>();

  for(Rule rule: this.rules)
  {
   if(RuleType.PERMISSION.equals(rule.getRuleType()))
   {
    permissionList.add(rule);
   }else if(RuleType.PROHIBITION.equals(rule.getRuleType()))
   {
    prohibitionList.add(rule);
   }else{
    //assume it is obligation
    obligationList.add(rule);
   }
  }

  if(!permissionList.isEmpty())
  {
   String temp = language.equals("ids")? permissionList.get(0).toIdsString(): permissionList.get(0).toString();
   if(permissionList.size() > 1)
   {
    for (int i = 1; i < permissionList.size(); i++)
    {
     if(language.equals("ids"))
     {
      temp = temp.concat(", \n" + permissionList.get(i).toIdsString());
     }else{
      temp = temp.concat(", \n" + permissionList.get(i).toString());
     }
    }
   }
   if(language.equals("odrl"))
   {
    temp = addAssignerAndAssignee(temp);
   }
   permissionBlock = String.format("  \"%s\": [%s] \n" , permissionElement, temp);
   rulesBlock = rulesBlock.concat(permissionBlock);
   if(!prohibitionList.isEmpty())
   {
    rulesBlock = rulesBlock.concat(", \n");
   }
  }

  if(!prohibitionList.isEmpty())
  {
   String temp = language.equals("ids")? prohibitionList.get(0).toIdsString(): prohibitionList.get(0).toString();
   if(prohibitionList.size() > 1)
   {
    for (int i = 1; i < prohibitionList.size(); i++)
    {
     if(language.equals("ids"))
     {
      temp = temp.concat(", \n" + prohibitionList.get(i).toIdsString());
     }else{
      temp = temp.concat(", \n" + prohibitionList.get(i).toString());
     }
    }
   }
   if(language.equals("odrl"))
   {
    temp = addAssignerAndAssignee(temp);
   }
   prohibitionBlock = String.format("\"%s\": [%s] \n" ,prohibitionElement, temp);
   rulesBlock = rulesBlock.concat(prohibitionBlock);
   if(!obligationList.isEmpty())
   {
    rulesBlock = rulesBlock.concat(", \n");
   }
  }

  if(!obligationList.isEmpty())
  {
   String temp = language.equals("ids")? obligationList.get(0).toIdsString(): obligationList.get(0).toString();
   if(obligationList.size() > 1)
   {
    for (int i = 1; i < obligationList.size(); i++)
    {
     if(language.equals("ids"))
     {
      temp = temp.concat(", \n" + obligationList.get(i).toIdsString());
     }else{
      temp = temp.concat(", \n" + obligationList.get(i).toString());
     }
    }
   }
   if(language.equals("odrl"))
   {
    temp = addAssignerAndAssignee(temp);
   }
   obligationBlock = String.format("\"%s\": [%s] \n" ,obligationElement, temp);
   rulesBlock = rulesBlock.concat(obligationBlock);
  }

  return rulesBlock;
 }

 private String addAssignerAndAssignee(String temp) {
  // Add Assigner and Assigner
  String assigner = String.format("      \n\"assigner\": \"%s\", \n" , this.provider.getUri());
  String assignee = "";
  if(!this.type.equals(PolicyType.OFFER))
  {
   assignee = String.format("      \"assignee\": \"%s\", \n" , this.consumer.getUri());
  }

  String regex = "(?:\\{)+";
  String replacement = "{" + assigner + assignee;

  return temp.replaceFirst(regex, replacement);
 }

}
