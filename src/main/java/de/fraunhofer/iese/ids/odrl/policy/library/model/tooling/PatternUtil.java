package de.fraunhofer.iese.ids.odrl.policy.library.model.tooling;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.fraunhofer.iese.ids.odrl.policy.library.model.*;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.*;

@SuppressWarnings("rawtypes")
public class PatternUtil {

	/*
	 * private Constructor
	 */
	private PatternUtil() {

	}

	/**
	 * analyzes a map specific Pattern and returns the specialist class of the categorized policy with the given parameters
	 *
	 * @param map
	 * @return categorized policy
	 */
	public static OdrlPolicy getPolicy(Map map, boolean providerSide) {

		try
		{

			PolicyType policyType = getPolicyType(map);

			Party provider = null;
			if(!isEmpty(getValue(map, "ids:provider")))
			{
				provider = new Party( PartyType.PROVIDER, URI.create(getValue(map, "ids:provider")));
			}

			Party consumer = null;
			if(!isEmpty(getValue(map, "ids:consumer")))
			{
				consumer = new Party(PartyType.CONSUMER, URI.create(getValue(map, "ids:consumer")));
			}

			//if there is a valid odrl policy
			if(isNotNull(policyType))
			{
				//get policy id
				String pid = getValue(map, "@id");
				URI pidUri = URI.create(pid);

				OdrlPolicy policy = addDetails(map);
				if(null != policy) {
					policy.setProviderSide(providerSide);
					policy.setPolicyId(pidUri);
					policy.setType(policyType);
					policy.setProvider(provider);
					policy.setConsumer(consumer);
				}

				return policy;
			}

			return null;
		}
		catch (IllegalArgumentException e){
			return null;
		}

	}


	/**
	 *
	 * @param map
	 * @return PolicyType of the Map (parsed from json)
	 */
	public static PolicyType getPolicyType(Map map) {
		return PolicyType.valueOf(removeIdsOrXsdTag(map.get("@type").toString()).substring(8));
	}

	public static String getValue(Map map, String attribute) {

		if(isNotNull(map.get(attribute)))
		{
			if(map.get(attribute) instanceof List)
			{	
				//TODO: unchecked cast
				Map attributeBlock = (Map) ((List) map.get(attribute)).get(0);
				return attributeBlock.get("@id").toString();
			} else {
				return map.get(attribute).toString();
			}
		}
		return null;
	}

	public static Map getRuleMap(Map map, RuleType ruleType) {
		return (Map) ((List) map.get(ruleType.getOdrlRuleType())).get(0);
	}

	public static ActionType getAction(Map map) {
		if(map.get("ids:action") instanceof List)
		{
			Map actionBlock = (Map) ((List) map.get("ids:action")).get(0);
			Map valueBlock = (Map) actionBlock.get("rdf:value");
			if (isNotNull(valueBlock))
			{
				return ActionType.valueOf(removeIdsOrXsdTag(valueBlock.get("@id").toString()));
			} else
			{
				return ActionType.valueOf(removeIdsOrXsdTag(actionBlock.get("@id").toString()));
			}
		}else{
			return ActionType.valueOf(removeIdsOrXsdTag(map.get("ids:action").toString()));
		}
	}

	public static ActionType getAbstractAction(Map map) {
		if(map.get("action") instanceof List)
		{
			Map actionBlock = (Map) ((List) map.get("action")).get(0);
			Map valueBlock = (Map) actionBlock.get("rdf:type");
			return ActionType.valueOf(removeIdsOrXsdTag(valueBlock.get("@id").toString()));
		}else{
			return null;
		}
	}

	public static URL getUrl(String urlString) {
		URL dataURL = null;
		try {
			dataURL = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataURL;
	}

	public static OdrlPolicy addDetails(Map map) {
        OdrlPolicy policy = new OdrlPolicy();

		// get rule type
		RuleType ruleType = getRuleType(map);
		Map ruleMap = getRuleMap(map, ruleType);

		// get target
		Map target = getMap(ruleMap, "ids:target");
		String targetId = getValue(target, "@id");
		URI targetURI = URI.create(targetId);

		ActionType action = getAction(ruleMap);
        Map ruleActionMap = getMap(ruleMap, "ids:action");

		Map preDutyMap = getMap(ruleMap, "ids:preDuty");
		Map postDutyMap = getMap(ruleMap, "ids:postDuty");

		Map dutyActionMap = null;
		ActionType dutyMethod = null;

		if (isNotNull(preDutyMap) ) {
			dutyMethod = getAction(preDutyMap);
			dutyActionMap = getMap(preDutyMap, "ids:action");
		}

		if (isNotNull(postDutyMap) ) {
			dutyMethod = getAction(postDutyMap);
			dutyActionMap = getMap(postDutyMap, "ids:action");
		}

		//TODO: for all rules!
        if(null != ruleType)
		{
			Rule rule = new Rule();
			rule.setType(ruleType);

			rule.setTarget(targetURI);

			Action ruleAction = new Action(action);

			ArrayList<Rule> preobligationRules = new ArrayList<>();
			ArrayList<Rule> postobligationRules = new ArrayList<>();

			Rule preobligationRule = new Rule();
			Rule postobligationRule = new Rule();

			Action dutyAction = new Action();

			//build the rule constraints
			List<Map> ruleConstraintList = new ArrayList<>();
			if ((isNotNull(ruleMap) && isNotNull(getConditions(ruleMap, ConditionType.CONSTRAINT))))
			{
				ruleConstraintList = getConditions(ruleMap, ConditionType.CONSTRAINT);
				ArrayList<Condition> ruleConstraint = new ArrayList<Condition>();
				buildConditions(ruleConstraintList, ruleConstraint, ConditionType.CONSTRAINT);
				rule.setConstraints(ruleConstraint);
			}

			//build the rule action refinements
			List<Map> ruleActionRefinementList = new ArrayList<>();
			if (isNotNull(ruleActionMap) && isNotNull(getConditions(ruleActionMap, ConditionType.REFINEMENT)))
			{
				ruleActionRefinementList.addAll(getConditions(ruleActionMap, ConditionType.REFINEMENT));
				ArrayList<Condition> ruleActionRefinements = new ArrayList<Condition>();
				buildConditions(ruleActionRefinementList, ruleActionRefinements, ConditionType.REFINEMENT);
				ruleAction.setRefinements(ruleActionRefinements);
			}

			//build the duty action refinements
			List<Map> dutyActionRefinementList = new ArrayList<>();
			if (isNotNull(dutyActionMap) && isNotNull(getConditions(dutyActionMap, ConditionType.REFINEMENT)))
			{
				dutyActionRefinementList.addAll(getConditions(dutyActionMap, ConditionType.REFINEMENT));
				ArrayList<Condition> dutyActionRefinements = new ArrayList<Condition>();
				buildConditions(dutyActionRefinementList, dutyActionRefinements,ConditionType.REFINEMENT);
				dutyAction.setRefinements(dutyActionRefinements);
			}


			if(null != dutyMethod)
			{
				switch (dutyMethod) {
					case DELETE:
						dutyAction.setType(ActionType.DELETE);
						postobligationRule = new Rule(RuleType.POSTDUTY, dutyAction);
						addConstraintsToDutyRule(postDutyMap, postobligationRule);
						postobligationRules.add(postobligationRule);
						break;
					case INCREMENT_COUNTER:
						dutyAction.setType(ActionType.INCREMENT_COUNTER);
						postobligationRule = new Rule(RuleType.POSTDUTY, dutyAction);
						addConstraintsToDutyRule(postDutyMap, postobligationRule);
						postobligationRules.add(postobligationRule);
						break;
					case ANONYMIZE:
						dutyAction.setType(ActionType.ANONYMIZE);
						preobligationRule = new Rule(RuleType.PREDUTY, dutyAction);
						addConstraintsToDutyRule(preDutyMap, preobligationRule);
						preobligationRules.add(preobligationRule);
						break;
					case LOG:
						dutyAction.setType(ActionType.LOG);
						postobligationRule = new Rule(RuleType.PREDUTY, dutyAction);
						addConstraintsToDutyRule(postDutyMap, postobligationRule);
						postobligationRules.add(postobligationRule);
						break;
					case INFORM:
						dutyAction.setType(ActionType.INFORM);
						postobligationRule = new Rule(RuleType.PREDUTY, dutyAction);
						addConstraintsToDutyRule(postDutyMap, postobligationRule);
						postobligationRules.add(postobligationRule);
						break;
					case NOTIFY:
						dutyAction.setType(ActionType.NOTIFY);
						postobligationRule = new Rule(RuleType.PREDUTY, dutyAction);
						addConstraintsToDutyRule(postDutyMap, postobligationRule);
						postobligationRules.add(postobligationRule);
						break;
					case NEXT_POLICY:
						dutyAction.setType(ActionType.NEXT_POLICY);
						preobligationRule = new Rule(RuleType.PREDUTY, dutyAction);
						addConstraintsToDutyRule(preDutyMap, preobligationRule);
						preobligationRules.add(preobligationRule);
						break;
				}
			}
			rule.setPreduties(preobligationRules);
			rule.setPostduties(postobligationRules);

			rule.setAction(ruleAction);
			ArrayList<Rule> rules = new ArrayList<>();
			rules.add(rule);
			policy.setRules(rules);
		}

        return policy;
    }

	private static void addConstraintsToDutyRule(Map postobligationMap, Rule dutyRule) {
		//build the postobligation constraints
		List<Map> postobligationConstraintList = new ArrayList<>();
		if (isNotNull(postobligationMap) && isNotNull(getConditions(postobligationMap, ConditionType.CONSTRAINT))) {
			postobligationConstraintList.addAll(getConditions(postobligationMap, ConditionType.CONSTRAINT));
			ArrayList<Condition> postobligationConstraints = new ArrayList<Condition>();
			buildConditions(postobligationConstraintList, postobligationConstraints,ConditionType.CONSTRAINT);
			dutyRule.setConstraints(postobligationConstraints);
		}
	}

	private static void buildConditions(List<Map> ruleConstraintList, List<Condition> ruleConstraint, ConditionType conditionType) {
		if(null != ruleConstraintList) {
			for (Map conditionMap : ruleConstraintList) {
				LeftOperand leftOperand = getLeftOperand(conditionMap);
				RightOperandId rightOperandId = getRightOperandId(conditionMap);
				Operator op = getOperator(conditionMap);
				String rightOperandValue = getRightOperandValue(conditionMap);
				RightOperandType rightOperandType = getRightOperandType(conditionMap);
				RightOperand rightOperand = new RightOperand(rightOperandId, rightOperandValue, rightOperandType);

				switch (leftOperand) {
					case PURPOSE:
						Condition purposeConstraint = new Condition(conditionType, LeftOperand.PURPOSE, op, rightOperand, "");
						ruleConstraint.add(purposeConstraint);
						break;
					case SYSTEM:
						Condition systemConstraint = new Condition(conditionType, LeftOperand.SYSTEM, op, rightOperand, "");
						ruleConstraint.add(systemConstraint);
						break;
					case APPLICATION:
						Condition applicationConstraint = new Condition(conditionType, LeftOperand.APPLICATION, op, rightOperand, "");
						ruleConstraint.add(applicationConstraint);
						break;
					case CONNECTOR:
						Condition connectorConstraint = new Condition(conditionType, LeftOperand.CONNECTOR, op, rightOperand, "");
						ruleConstraint.add(connectorConstraint);
						break;
					case STATE:
						Condition stateConstraint = new Condition(conditionType, LeftOperand.STATE, op, rightOperand, "");
						ruleConstraint.add(stateConstraint);
						break;
					case SECURITY_LEVEL:
						Condition securityLevelConstraint = new Condition(conditionType, LeftOperand.SECURITY_LEVEL, op, rightOperand, "");
						ruleConstraint.add(securityLevelConstraint);
						break;
					case ROLE:
						Condition roleConstraint = new Condition(conditionType, LeftOperand.ROLE, op, rightOperand, "");
						ruleConstraint.add(roleConstraint);
						break;
					case EVENT:
						Condition eventConstraint = new Condition(conditionType, LeftOperand.EVENT, op, rightOperand, "");
						ruleConstraint.add(eventConstraint);
						break;
					case COUNT:
						Condition countConstraint = new Condition(conditionType, LeftOperand.COUNT, op, rightOperand, "");
						ruleConstraint.add(countConstraint);
						break;
					case ENCODING:
						Condition encodingConstraint = new Condition(conditionType, LeftOperand.ENCODING, op, rightOperand, "");
						ruleConstraint.add(encodingConstraint);
						break;
					case LOG_LEVEL:
						Condition logLevelConstraint = new Condition(conditionType, LeftOperand.LOG_LEVEL, op, rightOperand, "");
						ruleConstraint.add(logLevelConstraint);
						break;
					case NOTIFICATION_LEVEL:
						Condition notificationLevelConstraint = new Condition(conditionType, LeftOperand.NOTIFICATION_LEVEL, op, rightOperand, "");
						ruleConstraint.add(notificationLevelConstraint);
						break;
					case ARTIFACT_STATE:
						Condition artifactStateConstraint = new Condition(conditionType, LeftOperand.ARTIFACT_STATE, op, rightOperand, "");
						ruleConstraint.add(artifactStateConstraint);
						break;
					case DATE_TIME:
						String endTimeEntityValue = getRightOperandValueEntity(conditionMap, EntityType.END);
						RightOperandEntity endTimeEntity = new RightOperandEntity(EntityType.END, endTimeEntityValue, RightOperandType.DATETIMESTAMP);
						ArrayList<RightOperandEntity> dateTimeEntity = new ArrayList<>();
						dateTimeEntity.add(endTimeEntity);
						RightOperand dateTimeRightOperand =new RightOperand(dateTimeEntity, RightOperandType.INSTANT);
						Condition dateTimeCondition = new Condition(conditionType, LeftOperand.DATE_TIME, op, dateTimeRightOperand, "");
						ruleConstraint.add(dateTimeCondition);
						break;
					case POLICY_EVALUATION_TIME:
						String beginEntityValue = getRightOperandValueEntity(conditionMap, EntityType.BEGIN);
						RightOperandEntity beginEntity = new RightOperandEntity(EntityType.BEGIN, beginEntityValue, RightOperandType.DATETIMESTAMP);
						String endEntityValue = getRightOperandValueEntity(conditionMap, EntityType.END);
						RightOperandEntity endEntity = new RightOperandEntity(EntityType.END, endEntityValue, RightOperandType.DATETIMESTAMP);
						ArrayList<RightOperandEntity> timeIntervalEntities = new ArrayList<>();
						timeIntervalEntities.add(beginEntity);
						timeIntervalEntities.add(endEntity);
						RightOperand timeIntervalRightOperand =new RightOperand(timeIntervalEntities, RightOperandType.INTERVAL);
						Condition timeIntervalCondition = new Condition(conditionType, LeftOperand.POLICY_EVALUATION_TIME, op, timeIntervalRightOperand, "");
						ruleConstraint.add(timeIntervalCondition);
						break;
					case DELAY:
						String delayEntityValue = getRightOperandValueEntity(conditionMap, EntityType.HASDURATION);
						RightOperandEntity dEntity = new RightOperandEntity(EntityType.HASDURATION, delayEntityValue, RightOperandType.DURATION);
						ArrayList<RightOperandEntity> delayPeriodEntities = new ArrayList<>();
						delayPeriodEntities.add(dEntity);
						RightOperand delayPeriodRightOperand =new RightOperand(delayPeriodEntities, RightOperandType.DURATIONENTITY);
						Condition delayPeriodConstraint = new Condition(conditionType, LeftOperand.DELAY, op, delayPeriodRightOperand, "");
						ruleConstraint.add(delayPeriodConstraint);
						break;
					case ELAPSED_TIME:
						String durationEntityValue = getRightOperandValueEntity(conditionMap, EntityType.HASDURATION);
						RightOperandEntity durationEntity = new RightOperandEntity(EntityType.HASDURATION, durationEntityValue, RightOperandType.DURATION);
						ArrayList<RightOperandEntity> elapsedTimeEntities = new ArrayList<>();
						elapsedTimeEntities.add(durationEntity);
						String bEntityValue = getRightOperandValueEntity(conditionMap, EntityType.BEGIN);
						if(!bEntityValue.isEmpty()){
							RightOperandEntity bEntity = new RightOperandEntity(EntityType.BEGIN, bEntityValue, RightOperandType.DATETIMESTAMP);
							elapsedTimeEntities.add(bEntity);
						}
						RightOperand elapsedTimeRightOperand =new RightOperand(elapsedTimeEntities, RightOperandType.DURATIONENTITY);
						Condition elapsedTimeConstraint = new Condition(conditionType, LeftOperand.ELAPSED_TIME, op, elapsedTimeRightOperand, "");
						ruleConstraint.add(elapsedTimeConstraint);
						break;
					case RECIPIENT:
						Condition recipientConstraint = new Condition(conditionType, LeftOperand.RECIPIENT, op, rightOperand, "");
						ruleConstraint.add(recipientConstraint);
						break;
					case MODIFICATION_METHOD:
						Condition modificationMethodRefinement = new Condition(conditionType, LeftOperand.MODIFICATION_METHOD, op, rightOperand, "");
						// add jsonPath
						modificationMethodRefinement.setJsonPath(getValue(conditionMap, "ids:jsonPath"));
						// add replaceWith parameter
						String replaceWithValue = getReplaceWithValue(conditionMap);
						RightOperandType replaceWithType = getReplaceWithType(conditionMap);
						ModificationMethodParameter replaceWithParam = new ModificationMethodParameter(replaceWithValue, replaceWithType);
						modificationMethodRefinement.setReplaceWith(replaceWithParam);
						ruleConstraint.add(modificationMethodRefinement);
						break;
					case PAY_AMOUNT:
						Condition paymentConstraint = new Condition(conditionType, LeftOperand.PAY_AMOUNT, op, rightOperand, "");
						paymentConstraint.setContract(getValue(conditionMap, "ids:contract"));
						paymentConstraint.setUnit(getValue(conditionMap, "ids:unit"));
						ruleConstraint.add(paymentConstraint);
						break;
					case ABSOLUTE_SPATIAL_POSITION:
						Condition absoluteSpatialPositionConstraint = new Condition(conditionType, LeftOperand.ABSOLUTE_SPATIAL_POSITION, op, rightOperand, "");
						ruleConstraint.add(absoluteSpatialPositionConstraint);
						break;
					case SYSTEM_DEVICE:
						Condition systemDeviceRefinement = new Condition(conditionType, LeftOperand.SYSTEM_DEVICE, op, rightOperand, "");
						ruleConstraint.add(systemDeviceRefinement);
						break;
					case INFORMEDPARTY:
						Condition informedPartyRefinement = new Condition(conditionType, LeftOperand.INFORMEDPARTY, op, rightOperand, "");
						ruleConstraint.add(informedPartyRefinement);
						break;
					case TARGET_POLICY:
						Condition thirdPartyRefinement = new Condition(conditionType, LeftOperand.TARGET_POLICY, op, rightOperand, "");
						ruleConstraint.add(thirdPartyRefinement);
						break;
				default:
					break;
				}
			}
		}
	}

	public static RuleType getRuleType(Map map) {
		return isNotNull(map.get(RuleType.PERMISSION.getOdrlRuleType()))? RuleType.PERMISSION :
				(isNotNull(map.get(RuleType.PROHIBITION.getOdrlRuleType()))? RuleType.PROHIBITION :
						(isNotNull(map.get(RuleType.OBLIGATION.getOdrlRuleType()))? RuleType.OBLIGATION : null ));
	}


	private static boolean isAnonymizeInTransit(Map ruleMap) {
		Map dutyMap = getMap(ruleMap, "ids:duty");
		if(isNotNull(dutyMap))
		{
			ActionType abstractAction = getAbstractAction(dutyMap);
			return (abstractAction.equals(ActionType.ANONYMIZE));
		}
		return false;
	}

	private static boolean isAction(Map permissionMap, Action action) {
		return getAction(permissionMap).equals(action);
	}

	public static LeftOperand getLeftOperand(Map conditionMap) {
		try {
			return isNotNull(conditionMap) && isNotNull(getValue(conditionMap, "ids:leftOperand")) ? LeftOperand.valueOf(removeIdsOrXsdTag(getValue(conditionMap, "ids:leftOperand"))) : null;
		}catch (Exception e){
			Map leftOperandMap = (Map) conditionMap.get("ids:leftOperand");
			return isNotNull (conditionMap) && isNotNull(leftOperandMap) && isNotNull(getValue(leftOperandMap, "@id")) ? LeftOperand.valueOf(removeIdsOrXsdTag(getValue(leftOperandMap, "@id"))): null;
		}
		}

/*	public static String getPartyFunctionValue (Map dutyMap, PartyFunction partyFunction) {
		return isNotNull (dutyMap)? dutyMap.get(partyFunction.getIdsPartyFunction()).toString(): "";
	}*/
	public static Operator getOperator(Map conditionMap) {
		try {
			return isNotNull (conditionMap)? Operator.valueOf(removeIdsOrXsdTag(getValue(conditionMap, "ids:operator"))): null;
		}catch (Exception e){
			Map operatorMap = (Map) conditionMap.get("ids:operator");
			return isNotNull (conditionMap) && isNotNull(operatorMap)? Operator.valueOf(removeIdsOrXsdTag(getValue(operatorMap, "@id"))): null;
		}
	}

	public static IntervalCondition getIntervalOperator(Map conditionMap) {
		return isNotNull (conditionMap) && isNotNull(getValue(conditionMap, "ids:operator")) ? IntervalCondition.valueOf(getValue(conditionMap, "ids:operator").toUpperCase()): null;
	}

	public static String getReplaceWithValue(Map conditionMap) {
		Map replaceWithMap = (Map) conditionMap.get("ids:replaceWith");
		return isNotNull (replaceWithMap)? getValue(replaceWithMap, "@value"): "";
	}

	public static RightOperandType getReplaceWithType(Map conditionMap) {
		Map replaceWithMap = (Map) conditionMap.get("ids:replaceWith");
		return isNotNull (replaceWithMap) && isNotNull(getValue(replaceWithMap, "@type")) ? RightOperandType.valueOf(removeIdsOrXsdTag(getValue(replaceWithMap, "@type"))): null;
	}

	public static String getRightOperandValue(Map conditionMap) {
		Map rightOperandMap = (Map) conditionMap.get("ids:rightOperand");
		return isNotNull (rightOperandMap)? getValue(rightOperandMap, "@value"): "";
	}

	public static String getRightOperandValueEntity(Map conditionMap, EntityType entityType) {
		Map rightOperandMap = (Map) conditionMap.get("ids:rightOperand");
		Map rightOperandValueMap = (Map) rightOperandMap.get("@value");
		Map entityMap = (Map) rightOperandValueMap.get(entityType.getOdrlRuleType());
		return isNotNull (entityMap)? getValue(entityMap, "@value"): "";
	}

	public static RightOperandType getRightOperandType(Map conditionMap) {
		Map rightOperandMap = (Map) conditionMap.get("ids:rightOperand");
		return isNotNull (rightOperandMap) && isNotNull(getValue(rightOperandMap, "@type")) ? RightOperandType.valueOf(removeIdsOrXsdTag(getValue(rightOperandMap, "@type"))): null;
	}

	public static RightOperandId getRightOperandId(Map conditionMap) {
		Map rightOperandMap = (Map) conditionMap.get("ids:rightOperand");
		return isNotNull (rightOperandMap) && isNotNull(getValue(rightOperandMap, "@id")) ? RightOperandId.valueOf(removeIdsOrXsdTag(getValue(rightOperandMap, "@id"))): null;
	}

    public static List<Map> getConditions(Map map, ConditionType conditionType) {
        Object condition = map.get(conditionType.getOdrlConditionType());
        if(condition instanceof List) {
        	List conditions = ((List)condition);
            if(!conditions.isEmpty()) {
                
                List<Map> conditionsAsMap = new ArrayList<>();
                for(Object conditionsElement : conditions)
                {
                	//TODO: check instanceof Map?!?
                    conditionsAsMap.add((Map) conditionsElement);
                }
                return conditionsAsMap;
            }
        }
        return null;
    }

	public static Map getMap(Map ruleMap, String tag) {
		Object action = ruleMap.get(tag);
		if(action instanceof List) {
			List actions = (List)action;
			if(!actions.isEmpty()) {
				Object actionsFistElement = actions.get(0);
				if(actionsFistElement instanceof Map) {
					return (Map) actionsFistElement;
				}
			}
		}
		if(action instanceof Map) {
			return (Map) action;
		}
		return null;
	}

	public static boolean isNull(Object o) {
		return null == o;
	}

	public static boolean isNotNull(Object o) {
		return null != o;
	}

	private static String removeIdsOrXsdTag(String term)
	{
		if (term.startsWith("http"))
		{
			String[] termSplit = term.split("/");
			if(termSplit[4].contains("#"))
			{
				String[] lastPartSplit = termSplit[4].split("#");
				return lastPartSplit[1].toUpperCase();
			}
			return termSplit[5].equalsIgnoreCase("action")?termSplit[6].toUpperCase():termSplit[5].toUpperCase();
		}else if (term.startsWith("idsc")) {
			String termWithoutTag = term.trim().replaceAll(" ", "_");
			return termWithoutTag.substring(5).toUpperCase();
		} else {
			//remove ids or xsd tag
		String termWithoutTag = term.trim().replaceAll(" ", "_");
		return termWithoutTag.substring(4).toUpperCase();
		}
	}

	/*private static String removeXsdTag(String term)
	{
		String termWithoutTag = term.trim().replaceAll(" ", "_");
		return termWithoutTag.substring(4).toUpperCase();
	}*/
	
	private static boolean isEmpty(String value) {
		return (null == value || value.isEmpty());
	}

}


