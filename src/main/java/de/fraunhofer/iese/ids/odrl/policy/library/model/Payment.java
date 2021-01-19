package de.fraunhofer.iese.ids.odrl.policy.library.model;


import lombok.Data;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
@Data
public class Payment {
 int value;
 String contract;
 String unit;

 public Payment()
 {

 }

 public Payment(int v, String c, String u)
 {
  value = v;
  contract = c;
  unit = u;
 }

 public void setValue(){
  this.value = 1;
 }

 public int getValue() {
  return value;
 }

 public void setContract(){
  //http://dbpedia.org/page/Sale
  this.contract = "http://dbpedia.org/page/Rent";
 }

 public String getContract() {
  return contract;
 }


 public void setUnit(){
  this.unit = "http://dbpedia.org/resource/Euro";
 }

 public String getUnit() {
  return unit;
 }
}
