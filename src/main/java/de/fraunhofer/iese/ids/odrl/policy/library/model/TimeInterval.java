/**
 * 
 */
package de.fraunhofer.iese.ids.odrl.policy.library.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.IntervalCondition;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
public class TimeInterval {
	ZonedDateTime start;
	ZonedDateTime end;
	
	public void setBefore(String endTime){
		start = ZonedDateTime.now();
		end = ZonedDateTime.parse(endTime, DateTimeFormatter.ISO_INSTANT);
	}
	public void setAfter(String startTime){
		start = ZonedDateTime.parse(startTime, DateTimeFormatter.ISO_INSTANT);
		end = ZonedDateTime.now().plus(1, ChronoUnit.CENTURIES);
	}
	
	public void setExactly(String startTime, String endTime){
		start = ZonedDateTime.parse(startTime, DateTimeFormatter.ISO_INSTANT);
		end = ZonedDateTime.parse(endTime, DateTimeFormatter.ISO_INSTANT);
	}
	
	public void setInterval(IntervalCondition intervalCondition, String startTime, String endTime) {
		if(IntervalCondition.LT.equals(intervalCondition)) {
			setBefore(endTime);
		}
		else if(IntervalCondition.GT.equals(intervalCondition)) {
			setAfter(startTime);
		}
		else if(IntervalCondition.EQ.equals(intervalCondition)){
			setExactly(startTime, endTime);
		}
	}
}
