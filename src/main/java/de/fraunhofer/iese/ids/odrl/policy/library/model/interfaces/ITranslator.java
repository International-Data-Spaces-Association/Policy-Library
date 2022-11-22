package de.fraunhofer.iese.ids.odrl.policy.library.model.interfaces;

import de.fraunhofer.iese.ids.odrl.policy.library.model.Condition;
import de.fraunhofer.iese.ids.odrl.policy.library.model.OdrlPolicy;

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
	ITranslateDuty translateNextPolicyDuty(OdrlPolicy odrlPolicy);
	ITranslateDuty translateInformDuty(OdrlPolicy odrlPolicy);
	ITranslateDuty translateLogDuty(OdrlPolicy odrlPolicy);
	ITranslateDuty translateAnonymizeDuty(OdrlPolicy odrlPolicy);
	ITranslateDuty translateDeleteDuty(OdrlPolicy odrlPolicy);
	ITranslateDuty translateCountDuty(OdrlPolicy odrlPolicy);

	ITranslateCondition translateConstraint(Condition odrlConstraint);
	ITranslateDuty translateDuty(OdrlPolicy odrlPolicy);
	}
