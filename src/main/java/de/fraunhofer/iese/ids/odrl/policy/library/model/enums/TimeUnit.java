/**
 * 
 */
package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
public enum TimeUnit {
	//every hour
	HOURS("0 0 * * * ?","H"),

	//every day
	DAYS("0 0 0 * * ?","D"),

	//every first day of the month
	MONTHS("0 0 0 1 * ?", "M"),

	//every first of January
	YEARS("0 0 0 1 1 ?", "Y");

	private final String mydataCron;
	private final String odrlXsdDuration;

	TimeUnit(String op1, String op2) {
		mydataCron = op1;
		odrlXsdDuration = op2;
	}

	public String getMydataCron() {
		return mydataCron;
	}

	public String getOdrlXsdDuration() {
		return odrlXsdDuration;
	}
}
