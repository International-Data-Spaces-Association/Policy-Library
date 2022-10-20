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
 EQUALS("equals", "idsc:EQUALS", "eq"),

 SAME_AS("equals", "idsc:SAME_AS", "eq"),

 DEFINES_AS("equals", "idsc:DEFINES_AS", "eq"),

HAS_MEMBERSHIP("equals", "idsc:HAS_MEMBERSHIP", "eq"),

 // Arithmetic operators
 EQ("equals", "idsc:EQ", "eq"),

 LT("less", "idsc:LT", "lt"),

 LTEQ("lessEqual", "idsc:LTEQ", "le"),

 GT("greater", "idsc:GT", "gt"),

 GTEQ("greaterEqual", "idsc:GTEQ", "ge"),

 // Temporal operators

 AFTER("greater", "idsc:AFTER", "gt"),

 BEFORE("less", "idsc:BEFORE", "lt"),

 TEMPORAL_EQUALS("equals", "idsc:TEMPORAL_EQUALS", "eq"),

 DURING("equals", "idsc:DURING", "eq"),

 DURATION_EQ("equals", "idsc:DURATION_EQ", "eq"),

 SHORTER("less", "idsc:SHORTER", "lt"),

 SHORTER_EQ("lessEqual", "idsc:SHORTER_EQ", "le"),

 LONGER("greater", "idsc:LONGER", "gt"),

 LONGER_EQ("greaterEqual", "idsc:LONGER_EQ", "ge"),

 // # Binary Operators comparing spatial entities
 INSIDE("equals", "idsc:INSIDE", "eq");

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
