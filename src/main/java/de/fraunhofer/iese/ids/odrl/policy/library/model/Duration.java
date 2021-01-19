package de.fraunhofer.iese.ids.odrl.policy.library.model;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.TimeUnit;
import lombok.Data;
/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
@Data
public class Duration {
 int value;
 TimeUnit timeUnit;

 public  Duration ()
 {

 }

 public Duration(int v, TimeUnit t)
 {
  value = v;
  timeUnit = t;
 }

 public void setValue(){
  this.value = 1;
 }

 public int getValue() {
  return value;
 }

 public void setTimeUnit(){
  this.timeUnit = TimeUnit.HOURS;
 }

 public TimeUnit getTimeUnit() {
  return timeUnit;
 }
}
