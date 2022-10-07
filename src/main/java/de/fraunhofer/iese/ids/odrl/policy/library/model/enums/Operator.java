package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;


public enum Operator {

// TODO Check Odrl Operators
	
 // set
 IS_ANY_OF("isAnyOf", "idsc:IS_ANY_OF", "isAnyOf"),

 IS_ALL_OF("isAllOf", "idsc:IS_ALL_OF","isAllOf"),

 IS_NONE_OF("isNoneOf", "idsc:IS_NONE_OF", "isNoneOf"),

 // TODO: what is the equivalent MYDATA operator for idsc:IN
 IN("subsetOf", "idsc:IN", "subsetOf"),

 // Logical operators
 EQUALS("equals", "idsc:EQUALS", "equals"),

 SAME_AS("equals", "idsc:SAME_AS", "equals"),

 DEFINES_AS("equals", "idsc:DEFINES_AS", "equals"),

HAS_MEMBERSHIP("equals", "idsc:HAS_MEMBERSHIP", "equals"),

 // Arithmetic operators
 EQ("equals", "idsc:EQ", "equals"),

 LT("less", "idsc:LT", "less"),

 LTEQ("lessEqual", "idsc:LTEQ", "lessEqual"),

 GT("greater", "idsc:GT", "greater"),

 GTEQ("greaterEqual", "idsc:GTEQ", "greaterEqual"),

 // Temporal operators

 AFTER("greater", "idsc:AFTER", "greater"),

 BEFORE("less", "idsc:BEFORE", "less"),

 TEMPORAL_EQUALS("equals", "idsc:TEMPORAL_EQUALS", "equals"),

 DURING("equals", "idsc:DURING", "equals"),

 DURATION_EQ("equals", "idsc:DURATION_EQ", "equals"),

 SHORTER("less", "idsc:SHORTER", "less"),

 SHORTER_EQ("lessEqual", "idsc:SHORTER_EQ", "lessEqual"),

 LONGER("greater", "idsc:LONGER", "greater"),

 LONGER_EQ("greaterEqual", "idsc:LONGER_EQ", "greaterEqual"),

 // # Binary Operators comparing spatial entities
 INSIDE("equals", "idsc:INSIDE", "equals");

 private final String mydataOp;
 private final String idsOP;
 private final String odrlOP;

 Operator(String op1, String op2, String op3) {
  mydataOp = op1;
  idsOP = op2;
  odrlOP = op3;
 }

 public String getMydataOp() {
  return mydataOp;
 }

 public String getIdsOp() {
  return idsOP;
 }
 
 public String getOdrlOp() {
	 return odrlOP;
 }

}
