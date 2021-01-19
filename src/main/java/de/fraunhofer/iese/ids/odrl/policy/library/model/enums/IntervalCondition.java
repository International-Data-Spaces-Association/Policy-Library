/**
 * 
 */
package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
public enum IntervalCondition {
	LT("before","lt"),

	EQ("exactly", "eq"),

	GT("after", "gt");

	private final String mydataInterval;
	private final String odrlIntervalOperator;

	IntervalCondition(String op1, String op2) {
		mydataInterval = op1;
		odrlIntervalOperator = op2;
	}

	public String getMydataInterval() {
		return mydataInterval;
	}

	public String getOdrlIntervalOperator() {
		return odrlIntervalOperator;
	}
}
