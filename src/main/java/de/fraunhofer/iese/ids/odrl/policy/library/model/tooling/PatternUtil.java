package de.fraunhofer.iese.ids.odrl.policy.library.model.tooling;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.jsonldjava.utils.Obj;
import de.fraunhofer.iese.ids.odrl.policy.library.model.*;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ActionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ConditionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.EntityType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.LeftOperand;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.Operator;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PartyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PolicyType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandEntitiesType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandId;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RightOperandType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;

@SuppressWarnings("rawtypes")
public class PatternUtil{

	/*
	 * private Constructor for static util class
	 */
	private PatternUtil() {

	}

	/**
	 * analyzes a map (derived from json-ld) specific Pattern and returns the
	 * specialist class of the categorized policy with the given parameters
	 *
	 * @param map
	 * @return categorized policy
	 * @ 
	 */
	public static OdrlPolicy getPolicy(Map map, boolean providerSide)  {

		try {

			PolicyType policyType = getPolicyType(map);

			Party provider = null;
			if (!isNullOrEmpty(getValue(map, "ids:provider"))) {
				provider = new Party(PartyType.PROVIDER, URI.create(getValue(map, "ids:provider")));
			}

			Party consumer = null;
			if (!isNullOrEmpty(getValue(map, "ids:consumer"))) {
				consumer = new Party(PartyType.CONSUMER, URI.create(getValue(map, "ids:consumer")));
			}

			// if there is a valid odrl policy
			if (isNotNull(policyType)) {
				// get policy id
				String pid = getValue(map, "@id");
				if(null == pid)
				{
					pid = getValue(map, "uid");
				}
				URI pidUri = URI.create(pid);

				OdrlPolicy policy = createOdrlPolicyBody(map);
				if (null != policy) {
					policy.setProviderSide(providerSide);
					policy.setPolicyId(pidUri);
					policy.setType(policyType);
					policy.setProvider(provider);
					policy.setConsumer(consumer);
				}

				return policy;
			}

			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 *
	 * @param map
	 * @return PolicyType of the Map (parsed from json)
	 * @ 
	 */
	public static PolicyType getPolicyType(Map map)  {
		try{
			return PolicyType.valueOf(removeIdsOrXsdTag(map.get("@type").toString()).substring(8));
		}catch (IllegalArgumentException exception){
			return PolicyType.valueOf(removeIdsOrXsdTag(map.get("@type").toString()));
		}
	}

	public static String getValue(Map map, String attribute) {
		Object attributeObject = map.get(attribute);

		if (isNotNull(attributeObject)) {
			if (attributeObject instanceof List) {
				// TODO: unchecked cast
				Map attributeMap = (Map) ((List) attributeObject).get(0);
				String v = attributeMap.get("@id").toString();
				return isNullOrEmpty(v)? attributeMap.get("uid").toString(): v;
			} else if (attributeObject instanceof Map) {
				Map attributeMap = (Map) attributeObject;
				String v = attributeMap.get("@id").toString();
				return isNullOrEmpty(v)? attributeMap.get("uid").toString():v;
			} else {
				return map.get(attribute).toString();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map getRuleMap(Map map, RuleType ruleType) {
		List<Map> idsMaps = (List) map.get(ruleType.getIdsRepresentation());
		return isNotNull(idsMaps) ? idsMaps.get(0) : (Map) ((List) map.get(ruleType.getOdrlRepresentation())).get(0);
	}

	public static List getRuleMaps(Map map, RuleType ruleType) {
		List idsList = (List) map.get(ruleType.getIdsRepresentation());
		return isNotNull(idsList)? idsList: (List) map.get(ruleType.getOdrlRepresentation());
	}

	public static ActionType getAction(Map map)  {
		Object idsActionMap = map.get("ids:action");
		Object actionMap =  isNotNull(idsActionMap)? idsActionMap : map.get("action");
		if (actionMap instanceof List) {
			Map actionBlock = (Map) ((List) actionMap).get(0);
			Map valueBlock = (Map) actionBlock.get("rdf:value");
			if (isNotNull(valueBlock)) {
				return ActionType.valueOf(removeIdsOrXsdTag(valueBlock.get("@id").toString()));
			} else {
				return ActionType.valueOf(removeIdsOrXsdTag(actionBlock.get("@id").toString()));
			}
		} else {
			return ActionType.valueOf(removeIdsOrXsdTag(actionMap.toString()));
		}
	}

	public static ActionType getAbstractAction(Map map)  {
		if (map.get("action") instanceof List) {
			Map actionBlock = (Map) ((List) map.get("action")).get(0);
			Map valueBlock = (Map) actionBlock.get("rdf:type");
			return ActionType.valueOf(removeIdsOrXsdTag(valueBlock.get("@id").toString()));
		} else {
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

	@SuppressWarnings("unchecked")
	public static OdrlPolicy createOdrlPolicyBody(Map map)  {
		OdrlPolicy policy = new OdrlPolicy();

		// get rules
		List<Rule> rules = new ArrayList<>();
		List<Map> perRuleMaps = getRuleMaps(map, RuleType.PERMISSION);
		if (perRuleMaps != null) {
			for (Map ruleMap : perRuleMaps) {

				Rule rule = buildRule(ruleMap);
				rule.setRuleType(RuleType.PERMISSION);
				rules.add(rule);
			}
		}

		List<Map> proRuleMaps = getRuleMaps(map, RuleType.PROHIBITION);
		if (proRuleMaps != null) {
			for (Map ruleMap : proRuleMaps) {

				Rule rule = buildRule(ruleMap);
				rule.setRuleType(RuleType.PROHIBITION);
				rules.add(rule);
			}
		}

		List<Map> OblRuleMaps = getRuleMaps(map, RuleType.OBLIGATION);
		if (OblRuleMaps != null) {
			for (Map ruleMap : OblRuleMaps) {

				Rule rule = buildRule(ruleMap);
				rule.setRuleType(RuleType.OBLIGATION);
				rules.add(rule);
			}
		}

		policy.setRules(rules);
		return policy;
	}

	private static Rule buildRule(Map ruleMap)  {
		Rule rule = new Rule();

		// get target
//		Map target = getFirstMap(ruleMap, "ids:target");
		Target target = buildTarget(ruleMap);
		rule.setTarget(target);

		ActionType action = getAction(ruleMap);
		Map idsRuleActionMap = getFirstMap(ruleMap, "ids:action");
		Map ruleActionMap = isNotNull(idsRuleActionMap)? idsRuleActionMap: getFirstMap(ruleMap, "action");

		Action ruleAction = new Action(action);

		// build the rule constraints
		List<Condition> ruleConstraints = getConditions(ruleMap, ConditionType.CONSTRAINT);
		rule.setConstraints(ruleConstraints);

		// build the rule action refinements
		List<Condition> ruleActionRefinements = getConditions(ruleMap, ConditionType.REFINEMENT);
		ruleAction.setRefinements(ruleActionRefinements);

		rule.setAction(ruleAction);
		List<Map> odrlDutyMaps = getList(ruleMap, "duty");
		List<Map> preDutyMaps = getList(ruleMap, "ids:preDuty");
		List<Map> postDutyMaps = getList(ruleMap, "ids:postDuty");
		List<Map> dutyMaps = new ArrayList<>();
		if(isNotNull(odrlDutyMaps))
		{
			dutyMaps.addAll(odrlDutyMaps);
		}else if (isNotNull(preDutyMaps))
		{
			dutyMaps.addAll(preDutyMaps);
		}else if (isNotNull(postDutyMaps))
		{
			dutyMaps.addAll(postDutyMaps);
		}

		if (isNotNull(dutyMaps) && !dutyMaps.isEmpty()) {
			List<Rule> dutyRules = buildDuties(dutyMaps);
			rule.setDuties(dutyRules);
		}
		return rule;
	}

	private static Target buildTarget(Map ruleMap) {
		String idsTargetId = getValue(ruleMap, "ids:target");
		String targetId = isNotNull(idsTargetId)? idsTargetId: getValue(ruleMap, "target");
		return new Target(URI.create(targetId));
	}

	private static List<Rule> buildDuties(List<Map> dutyMaps)  {
		List<Rule> rules = new ArrayList<>();
		for (Map dutyMap : dutyMaps) {
			Map dutyActionMap = null;
			ActionType dutyMethod = null;
			if (isNotNull(dutyMap)) {
				dutyMethod = getAction(dutyMap);
				Map idsDutyActionMap = getFirstMap(dutyMap, "ids:action");
				dutyActionMap = isNotNull(idsDutyActionMap)?idsDutyActionMap: getFirstMap(dutyMap, "action");

				Action dutyAction = new Action();

				Rule rule = new Rule();

				// build the duty action refinements
				List<Condition> ruleActionRefinements = getConditions(dutyActionMap, ConditionType.REFINEMENT);
				dutyAction.setRefinements(ruleActionRefinements);

				if (null != dutyMethod) {
					switch (dutyMethod) {
						case DELETE:
							dutyAction.setType(ActionType.DELETE);
							rule = new Rule(RuleType.POST_DUTY, dutyAction);
							break;
						case INCREMENT_COUNTER:
							dutyAction.setType(ActionType.INCREMENT_COUNTER);
							rule = new Rule(RuleType.POST_DUTY, dutyAction);
							break;
						case LOG:
							dutyAction.setType(ActionType.LOG);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case INFORM:
							dutyAction.setType(ActionType.INFORM);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case NOTIFY:
							dutyAction.setType(ActionType.NOTIFY);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case DROP:
							dutyAction.setType(ActionType.DROP);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case ANONYMIZE:
							dutyAction.setType(ActionType.ANONYMIZE);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case REPLACE:
							dutyAction.setType(ActionType.REPLACE);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
						case NEXT_POLICY:
							dutyAction.setType(ActionType.NEXT_POLICY);
							rule = new Rule(RuleType.PRE_DUTY, dutyAction);
							break;
					}
					List<Condition> dutyConstraints = getConditions(dutyMap, ConditionType.CONSTRAINT);
					rule.setConstraints(dutyConstraints);
					rules.add(rule);
				}
			}
		}
		return rules;
	}

	private static List<Condition> getConditions(Map ruleMap, ConditionType conditionType)  {
		// build the rule or duty constraints
		if(isNotNull(ruleMap))
		{
			List<Map> idsConstraintList = getList(ruleMap, conditionType.getIdsRepresentation());
			List<Map> constraintList = isNotNull(idsConstraintList)? idsConstraintList: getList(ruleMap, conditionType.getOdrlRepresentation());
			if ( isNotNull(constraintList)) {
				return buildConditions(constraintList, conditionType);
			}
		}
		return null;
	}

	private static List<Condition> buildConditions(List<Map> ruleConstraintList, ConditionType conditionType)  {
		List<Condition> ruleConstraint = new ArrayList<>();

		if (isNotNull(ruleConstraintList)) {
			for (Map conditionMap : ruleConstraintList) {
				LeftOperand leftOperand = getLeftOperand(conditionMap);
				Operator operator = getOperator(conditionMap);
				List<RightOperand> rightOperands = getRightOperands(conditionMap);

				switch (leftOperand) {
				case PURPOSE:
					Condition purposeConstraint = new Condition(conditionType, LeftOperand.PURPOSE, operator,
							rightOperands);
					ruleConstraint.add(purposeConstraint);
					break;
				case SYSTEM:
					Condition systemConstraint = new Condition(conditionType, LeftOperand.SYSTEM, operator,
							rightOperands);
					ruleConstraint.add(systemConstraint);
					break;
				case APPLICATION:
					Condition applicationConstraint = new Condition(conditionType, LeftOperand.APPLICATION, operator,
							rightOperands);
					ruleConstraint.add(applicationConstraint);
					break;
				case CONNECTOR:
					Condition connectorConstraint = new Condition(conditionType, LeftOperand.CONNECTOR, operator,
							rightOperands);
					ruleConstraint.add(connectorConstraint);
					break;
				case STATE:
					Condition stateConstraint = new Condition(conditionType, LeftOperand.STATE, operator,
							rightOperands);
					ruleConstraint.add(stateConstraint);
					break;
				case SECURITY_LEVEL:
					Condition securityLevelConstraint = new Condition(conditionType, LeftOperand.SECURITY_LEVEL,
							operator, rightOperands);
					ruleConstraint.add(securityLevelConstraint);
					break;
				case ROLE:
					Condition roleConstraint = new Condition(conditionType, LeftOperand.ROLE, operator, rightOperands);
					ruleConstraint.add(roleConstraint);
					break;
				case EVENT:
					Condition eventConstraint = new Condition(conditionType, LeftOperand.EVENT, operator,
							rightOperands);
					ruleConstraint.add(eventConstraint);
					break;
				case COUNT:
					Condition countConstraint = new Condition(conditionType, LeftOperand.COUNT, operator,
							rightOperands);
					ruleConstraint.add(countConstraint);
					break;
//					case ENCODING:
//						Condition encodingConstraint = new Condition(conditionType, LeftOperand.ENCODING, op, rightOperands);
//						ruleConstraint.add(encodingConstraint);
//						break;
				case LOG_LEVEL:
					Condition logLevelConstraint = new Condition(conditionType, LeftOperand.LOG_LEVEL, operator,
							rightOperands);
					ruleConstraint.add(logLevelConstraint);
					break;
				case NOTIFICATION_LEVEL:
					Condition notificationLevelConstraint = new Condition(conditionType, LeftOperand.NOTIFICATION_LEVEL,
							operator, rightOperands);
					ruleConstraint.add(notificationLevelConstraint);
					break;
				case ARTIFACT_STATE:
					Condition artifactStateConstraint = new Condition(conditionType, LeftOperand.ARTIFACT_STATE,
							operator, rightOperands);
					ruleConstraint.add(artifactStateConstraint);
					break;
				case DATE_TIME:
					Condition timeIntervalCondition = new Condition(conditionType, LeftOperand.DATE_TIME, operator,
							rightOperands);
					ruleConstraint.add(timeIntervalCondition);
					break;
				case POLICY_EVALUATION_TIME:
					Condition evaluationTimeCondition = new Condition(conditionType, LeftOperand.POLICY_EVALUATION_TIME,
							operator, rightOperands);
					ruleConstraint.add(evaluationTimeCondition);
					break;
				case DELAY:
					Condition delayPeriodConstraint = new Condition(conditionType, LeftOperand.DELAY, operator,
							rightOperands);
					ruleConstraint.add(delayPeriodConstraint);
					break;
				case ELAPSED_TIME:
					Condition elapsedTimeConstraint = new Condition(conditionType, LeftOperand.ELAPSED_TIME, operator,
							rightOperands);
					ruleConstraint.add(elapsedTimeConstraint);
					break;
				case RECIPIENT:
					Condition recipientConstraint = new Condition(conditionType, LeftOperand.RECIPIENT, operator,
							rightOperands);
					ruleConstraint.add(recipientConstraint);
					break;
				case REPLACE_WITH:
					Condition replaceWithRefinement = new Condition(conditionType, LeftOperand.REPLACE_WITH, operator,
							rightOperands);
					ruleConstraint.add(replaceWithRefinement);
					break;
				case JSON_PATH:
					Condition subsetSpecificationRefinement = new Condition(conditionType, LeftOperand.JSON_PATH,
							operator, rightOperands);
					ruleConstraint.add(subsetSpecificationRefinement);
					break;
				case PAY_AMOUNT:
					Condition paymentConstraint = new Condition(conditionType, LeftOperand.PAY_AMOUNT, operator,
							rightOperands);
					paymentConstraint.setContract(getValue(conditionMap, "ids:contract"));
					String idsUnit = getValue(conditionMap, "ids:unit");
					String unit = isNullOrEmpty(idsUnit)?getValue(conditionMap, "unit"):idsUnit;
					paymentConstraint.setUnit(unit);
					ruleConstraint.add(paymentConstraint);
					break;
				case ABSOLUTE_SPATIAL_POSITION:
					Condition absoluteSpatialPositionConstraint = new Condition(conditionType,
							LeftOperand.ABSOLUTE_SPATIAL_POSITION, operator, rightOperands);
					ruleConstraint.add(absoluteSpatialPositionConstraint);
					break;
				case SYSTEM_DEVICE:
					Condition systemDeviceRefinement = new Condition(conditionType, LeftOperand.SYSTEM_DEVICE, operator,
							rightOperands);
					ruleConstraint.add(systemDeviceRefinement);
					break;
//					case INFORMEDPARTY:
//						Condition informedPartyRefinement = new Condition(conditionType, LeftOperand.INFORMEDPARTY, op, rightOperands);
//						ruleConstraint.add(informedPartyRefinement);
//						break;
				case TARGET_POLICY:
					Condition thirdPartyRefinement = new Condition(conditionType, LeftOperand.TARGET_POLICY, operator,
							rightOperands);
					ruleConstraint.add(thirdPartyRefinement);
					break;
				default:
					break;
				}
			}
		}
		return ruleConstraint;
	}

	private static List<RightOperand> getRightOperands(Map conditionMap)  {
		List<Map> rightOperandMaps = getList(conditionMap, "ids:rightOperand");
		rightOperandMaps = isNotNull(rightOperandMaps)? rightOperandMaps: getList(conditionMap, "rightOperand");
		List<RightOperand> rightOperands = new ArrayList<>();
		List<RightOperandEntity> entities = new ArrayList<>();

		for (Map rightOperandMap : rightOperandMaps) {

			RightOperandEntitiesType rightOperandEntitiesType = getRightOperandEntity(rightOperandMap);

			if (hasRightOperandEntities(rightOperandEntitiesType)) {
				if (RightOperandEntitiesType.INTERVAL.equals(rightOperandEntitiesType)) {
					Map beginEntityMap = (Map) rightOperandMap.get(EntityType.BEGIN.getType());
					if (isNotNull(beginEntityMap)) {
						String beginTimeEntityValue = getRightOperandValueEntity(beginEntityMap, EntityType.DATETIME);
						RightOperandEntity beginTimeEntity = new RightOperandEntity(EntityType.DATETIME,
								beginTimeEntityValue, RightOperandType.DATETIMESTAMP);
						RightOperandEntity beginEntity = new RightOperandEntity(EntityType.BEGIN, beginTimeEntity,
								RightOperandType.INSTANT);
						entities.add(beginEntity);
					}

					Map endEntityMap = (Map) rightOperandMap.get(EntityType.END.getType());
					if (isNotNull(endEntityMap)) {
						String endTimeEntityValue = getRightOperandValueEntity(endEntityMap, EntityType.DATETIME);
						RightOperandEntity endTimeEntity = new RightOperandEntity(EntityType.DATETIME,
								endTimeEntityValue, RightOperandType.DATETIMESTAMP);
						RightOperandEntity endEntity = new RightOperandEntity(EntityType.END, endTimeEntity,
								RightOperandType.INSTANT);
						entities.add(endEntity);
					}
				}
				if (RightOperandEntitiesType.TIMEBORDER.equals(rightOperandEntitiesType)) {
					String dateTimeEntityValue = getRightOperandValueEntity(rightOperandMap, EntityType.DATETIME);
					if (!isNullOrEmpty(dateTimeEntityValue)) {
						RightOperandEntity dateTimeEntity = new RightOperandEntity(EntityType.DATETIME,
								dateTimeEntityValue, RightOperandType.DATETIMESTAMP);
						entities.add(dateTimeEntity);
					}
				}

				if (RightOperandEntitiesType.DURATION.equals(rightOperandEntitiesType)) {
					String durationEntityValue = getRightOperandValueEntity(rightOperandMap, EntityType.HASDURATION);
					if (!isNullOrEmpty(durationEntityValue)) {
						RightOperandEntity durationEntity = new RightOperandEntity(EntityType.HASDURATION,
								durationEntityValue, RightOperandType.DURATION);
						entities.add(durationEntity);
					}
				}
			}

			RightOperandId rightOperandId = getRightOperandId(rightOperandMap);
			String rightOperandValue = getRightOperandValue(rightOperandMap);
			RightOperandType rightOperandType = getRightOperandType(rightOperandMap);

			RightOperand rightOperand = new RightOperand(rightOperandId, rightOperandValue, rightOperandType, entities);
			rightOperands.add(rightOperand);
		}
		return rightOperands;
	}

	private static RightOperandEntitiesType getRightOperandEntity(Map rightOperandMap) {
		Set keySet = rightOperandMap.keySet();

		if (keySet.contains("@type") && keySet.contains(EntityType.BEGIN.getType())
				&& keySet.contains(EntityType.END.getType())) {
			if (RightOperandType.INTERVAL.getType().equalsIgnoreCase(rightOperandMap.get("@type").toString()))
				;
			return RightOperandEntitiesType.INTERVAL;
		}
		if (keySet.contains(EntityType.DATETIME.getType())) {
			return RightOperandEntitiesType.TIMEBORDER;
		}

		return null;
	}

	private static boolean hasRightOperandEntities(RightOperandEntitiesType rightOperandEntitiesType) {
		return (null != rightOperandEntitiesType);
	}
	
	public static boolean isEmpty(Collection<?> collection) {
		return (null == collection || collection.isEmpty());
	}
	
	public static boolean isNotEmpty(Collection<?> collection) {
		return ! isEmpty(collection);
	}

	public static LeftOperand getLeftOperand(Map conditionMap)  {
		try {
			String leftOperandValue = isNotNull(conditionMap)? getValue(conditionMap, "ids:leftOperand"):null;
			leftOperandValue = isNullOrEmpty(leftOperandValue)? getValue(conditionMap, "leftOperand"): leftOperandValue;
			return isNotNull(leftOperandValue)
					? LeftOperand.valueOf(removeIdsOrXsdTag(leftOperandValue))
					: null;
		} catch (Exception e) {
			Map leftOperandMap = isNotNull(conditionMap)? (Map) conditionMap.get("ids:leftOperand"): null;
			leftOperandMap = isNotNull(leftOperandMap)? leftOperandMap : (Map) conditionMap.get("leftOperand");
			return isNotNull(leftOperandMap) && isNotNull(getValue(leftOperandMap, "@id"))
					? LeftOperand.valueOf(removeIdsOrXsdTag(getValue(leftOperandMap, "@id")))
					: null;
		}
	}

	/*
	 * public static String getPartyFunctionValue (Map dutyMap, PartyFunction
	 * partyFunction) { return isNotNull (dutyMap)?
	 * dutyMap.get(partyFunction.getIdsPartyFunction()).toString(): ""; }
	 */
	public static Operator getOperator(Map conditionMap)  {
		try {
			String operatorValue = isNotNull(conditionMap) ? getValue(conditionMap, "ids:operator"): null;
			operatorValue = isNotNull(operatorValue)? operatorValue:getValue(conditionMap, "operator");
			return isNotNull(operatorValue) ? Operator.valueOf(removeIdsOrXsdTag(operatorValue))
					: null;
		} catch (Exception e) {
			Map operatorMap = isNotNull(conditionMap)? (Map) conditionMap.get("ids:operator"): null;
			operatorMap = isNotNull(operatorMap)? operatorMap:(Map) conditionMap.get("operator");
			return isNotNull(operatorMap) && isNotNull(getValue(operatorMap, "@id"))
					? Operator.valueOf(removeIdsOrXsdTag(getValue(operatorMap, "@id")))
					: null;
		}
	}

	public static String getReplaceWithValue(Map conditionMap) {
		Map replaceWithMap = (Map) conditionMap.get("ids:replaceWith");
		return isNotNull(replaceWithMap) ? getValue(replaceWithMap, "@value") : "";
	}

	public static RightOperandType getReplaceWithType(Map conditionMap)  {
		Map replaceWithMap = (Map) conditionMap.get("ids:replaceWith");
		return isNotNull(replaceWithMap) && isNotNull(getValue(replaceWithMap, "@type"))
				? RightOperandType.valueOf(removeIdsOrXsdTag(getValue(replaceWithMap, "@type")))
				: null;
	}

	public static String getRightOperandValue(Map rightOperandMap) {
		return isNotNull(rightOperandMap) ? getValue(rightOperandMap, "@value") : "";
	}

	public static String getRightOperandValueEntity(Map rightOperandMap, EntityType entityType) {
		// Map rightOperandMap = (Map) conditionMap.get("ids:rightOperand");
		// Map rightOperandValueMap = (Map) rightOperandMap.get("@value");
		Map entityMap = (Map) rightOperandMap.get(entityType.getType());
		return isNotNull(entityMap) ? getValue(entityMap, "@value") : "";
	}

	public static RightOperandType getRightOperandType(Map rightOperandMap)  {
		return isNotNull(rightOperandMap) && isNotNull(getValue(rightOperandMap, "@type"))
				? RightOperandType.valueOf(removeIdsOrXsdTag(getValue(rightOperandMap, "@type")))
				: null;
	}

	public static RightOperandId getRightOperandId(Map rightOperandMap)  {
		return isNotNull(rightOperandMap) && isNotNull(getValue(rightOperandMap, "@id"))
				? RightOperandId.valueOf(removeIdsOrXsdTag(getValue(rightOperandMap, "@id")))
				: null;
	}

	public static List<Map> getList(Map map, String tag) {
		Object obj = map.get(tag);
		List<Map> listOfMaps = new ArrayList<>();
		if (obj instanceof List) {
			List objects = ((List) obj);
//            if(!objects.isEmpty()) {
			for (Object element : objects) {
				listOfMaps.add((Map) element);
			}
			return listOfMaps;
//            }
		} else if (obj instanceof Map) {
			listOfMaps.add((Map) obj);
			return listOfMaps;
		}
		return null;
	}

	public static Map getFirstMap(Map map, String tag) {
		Object obj = map.get(tag);
		if (obj instanceof List) {
			List objects = (List) obj;
			if (!objects.isEmpty()) {
				Object objectsFistElement = objects.get(0);
				if (objectsFistElement instanceof Map) {
					return (Map) objectsFistElement;
				}
			}
		}
		if (obj instanceof Map) {
			return (Map) obj;
		}
		return null;
	}

	public static boolean isNull(Object o) {
		return null == o;
	}

	public static boolean isNotNull(Object o) {
		return null != o;
	}

	private static String removeIdsOrXsdTag(String value)  { // TODO: space to improve to many returns
		if (value.startsWith("http")) {
			String[] splitedValue = value.split("/");
			int lastIndex = splitedValue.length-1;
			if (splitedValue[lastIndex].contains("#")) {
				String[] lastPartSplit = splitedValue[lastIndex].split("#");
				return lastPartSplit[1].toUpperCase();
			}else{
				return splitedValue[lastIndex].toUpperCase();
			}
		} else if (value.startsWith("idsc")) {
			String valueWithoutTag = value.trim().replaceAll(" ", "_");
			return valueWithoutTag.substring(5).toUpperCase();
		} else if (value.startsWith("ids") || value.startsWith("xsd")){
			String valueWithoutTag = value.trim().replaceAll(" ", "_");
			return valueWithoutTag.substring(4).toUpperCase();
		}else{
			//the value has no prefix
			value = replaceUppercaseWithUnderline(value);
			return value.toUpperCase();
		}
		//throw new IllegalArgumentException("Invalid syntax, value " + value + " is not allowed");
	}

	private static String replaceUppercaseWithUnderline(String value) {
		// TODO: find a better solution
		for(int i=1; i < value.length(); i++) {
			if(Character.isUpperCase(value.charAt(i))) {
				String s = String.valueOf(value.charAt(i));
				value = value.replace(value.substring(i,i+1), "_" + s.toLowerCase());
			}
		}
		return value;
	}

	/*
	 * private static String removeXsdTag(String term) { String termWithoutTag =
	 * term.trim().replaceAll(" ", "_"); return
	 * termWithoutTag.substring(4).toUpperCase(); }
	 */

	private static boolean isNullOrEmpty(String value) {
		return (null == value || value.isEmpty());
	}

}
