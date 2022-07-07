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
  this.profile = URI.create("http://example.com/ids-profile");
  this.provider = new Party(PartyType.PROVIDER, URI.create("http://example.com/party/my-party"));
 }

 public OdrlPolicy(URI policyId, PolicyType type, Party consumer, boolean providerSide) {
  this.profile = URI.create("http://example.com/ids-profile");
  this.policyId = policyId;
  this.type = type;
  this.consumer = consumer;
  this.provider = new Party(PartyType.PROVIDER, URI.create("http://example.com/party/my-party"));
  this.providerSide = providerSide;
 }

 @Override
 public String toString() {
  return  " {    \r\n" +
          "   \"@context\": {\n" +
          "      \"ids\":\"https://w3id.org/idsa/core/\",\n" +
          "      \"idsc\" : \"https://w3id.org/idsa/code/\"\n" +
          "   },    \r\n" +
          "  \"@type\": \"" + this.type.getStringRepresentation() +"\",    \r\n" +
          "  \"@id\": \"" + this.policyId.toString() +"\",    \r\n" +
          "  \"profile\": \""+ this.profile.toString() +"\",    \r\n" +
          getProviderBlock() +
          getConsumerBlock() +
          getRulesBlock() +
          "} ";
 }

 private String getConsumerBlock() {
  if(null != this.consumer && !this.type.equals(PolicyType.OFFER)) {
   return this.consumer.toString();
  }
  return "";
 }

 private String getProviderBlock() {
  if(null != this.provider && !this.type.equals(PolicyType.REQUEST)) {
   return this.provider.toString();
  }
  return "";
 }

 private String getRulesBlock() {
  String rulesBlock = "";
  String permissionBlock = "";
  String prohibitionBlock = "";
  String obligationBlock = "";

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
   String temp= "";
   temp = permissionList.get(0).toString();
   if(permissionList.size() > 1)
   {
    for (int i = 1; i < permissionList.size(); i++)
    {
     temp = temp.concat(", \n" + permissionList.get(i).toString());
    }
   }
   permissionBlock = String.format("  \"ids:permission\": [%s] \n" , temp);
   rulesBlock = rulesBlock.concat(permissionBlock);
   if(!prohibitionList.isEmpty())
   {
    rulesBlock = rulesBlock.concat(", \n");
   }
  }

  if(!prohibitionList.isEmpty())
  {
   String temp= "";
   temp = prohibitionList.get(0).toString();
   if(prohibitionList.size() > 1)
   {
    for (int i = 1; i < prohibitionList.size(); i++)
    {
     temp = temp.concat(", \n" + prohibitionList.get(i).toString());
    }
   }
   prohibitionBlock = String.format("\"ids:prohibition\": [%s] \n" , temp);
   rulesBlock = rulesBlock.concat(prohibitionBlock);
   if(!obligationList.isEmpty())
   {
    rulesBlock = rulesBlock.concat(", \n");
   }
  }

  if(!obligationList.isEmpty())
  {
   String temp= "";
   temp = obligationList.get(0).toString();
   if(obligationList.size() > 1)
   {
    for (int i = 1; i < obligationList.size(); i++)
    {
     temp = temp.concat(", \n" + obligationList.get(i).toString());
    }
   }
   obligationBlock = String.format("\"ids:obligation\": [%s] \n" , temp);
   rulesBlock = rulesBlock.concat(obligationBlock);
  }

  return rulesBlock;
 }

}
