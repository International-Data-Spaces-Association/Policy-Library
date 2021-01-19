package de.fraunhofer.iese.ids.odrl.policy.library.model.interfaces;

import de.fraunhofer.iese.ids.odrl.policy.library.model.OdrlPolicy;

public interface ITranslator {

	String translateComplexPolicy(OdrlPolicy odrlPolicy);
	}
