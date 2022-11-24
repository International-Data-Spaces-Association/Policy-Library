package de.fraunhofer.iese.ids.odrl.policy.library.model.interfaces;

import de.fraunhofer.iese.ids.odrl.policy.library.model.Condition;
import de.fraunhofer.iese.ids.odrl.policy.library.model.OdrlPolicy;
import de.fraunhofer.iese.ids.odrl.policy.library.model.Rule;

public interface ITranslator {

	String translateComplexPolicy(OdrlPolicy odrlPolicy);
	ITranslateCondition translateElapsedTimeConstraint(Condition odrlConstraint);
	ITranslateCondition translateArtifactStateConstraint(Condition odrlConstraint);
	ITranslateCondition translateEncodingConstraint(Condition odrlConstraint);
	ITranslateCondition translateCountConstraint(Condition odrlConstraint);
	ITranslateCondition translateAbsoluteSpatialPositionConstraint(Condition odrlConstraint);
	ITranslateCondition translateDateTimeConstraint(Condition odrlConstraint);
	ITranslateCondition translatePurposeConstraint(Condition odrlConstraint);
	ITranslateCondition translateSystemConstraint(Condition odrlConstraint);
	ITranslateCondition translateApplicationConstraint(Condition odrlConstraint);
	ITranslateCondition translateConnectorConstraint(Condition odrlConstraint);
	ITranslateCondition translateSecurityLevelConstraint(Condition odrlConstraint);
	ITranslateCondition translateStateConstraint(Condition odrlConstraint);
	ITranslateCondition translateRoleConstraint(Condition odrlConstraint);
	ITranslateCondition translatePaymentConstraint(Condition odrlConstraint);
	ITranslateCondition translateEventConstraint(Condition odrlConstraint);
	ITranslateDuty translateNextPolicyDuty(Rule odrlDuty);
	ITranslateDuty translateInformDuty(Rule odrlDuty);
	ITranslateDuty translateLogDuty(Rule odrlDuty);
	ITranslateDuty translateAnonymizeDuty(Rule odrlDuty);
	ITranslateDuty translateDeleteDuty(Rule odrlDuty);
	ITranslateDuty translateCountDuty(Rule odrlDuty);

	ITranslateCondition translateConstraint(Condition odrlConstraint);
	ITranslateDuty translateDuty(Rule odrlDuty);
	}
