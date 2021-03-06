package de.fraunhofer.iese.ids.odrl.policy.library.model.enums;


public enum Operator {


 // set
 IS_ANY_OF("isAnyOf", "idsc:IS_ANY_OF"),

 IS_ALL_OF("isAllOf", "idsc:IS_ALL_OF"),

 IS_NONE_OF("isNoneOf", "idsc:IS_NONE_OF"),

 // TODO: what is the equivalent MYDATA operator for idsc:IN
 IN("subsetOf", "idsc:IN"),

 // Logical operators
 EQUALS("equals", "idsc:EQUALS"),

 SAME_AS("equals", "idsc:SAME_AS"),

 DEFINES_AS("equals", "idsc:DEFINES_AS"),

HAS_MEMBERSHIP("equals", "idsc:HAS_MEMBERSHIP"),

 // Arithmetic operators
 EQ("equals", "idsc:EQ"),

 LT("less", "idsc:LT"),

 LTEQ("lessEqual", "idsc:LTEQ"),

 GT("greater", "idsc:GT"),

 GTEQ("greaterEqual", "idsc:GTEQ"),

 // Temporal operators

 AFTER("greater", "idsc:AFTER"),

 BEFORE("less", "idsc:BEFORE"),

 TEMPORAL_EQUALS("equals", "idsc:TEMPORAL_EQUALS"),

 DURING("equals", "idsc:DURING"),

 DURATION_EQ("equals", "idsc:DURATION_EQ"),

 SHORTER("less", "idsc:SHORTER"),

 SHORTER_EQ("lessEqual", "idsc:SHORTER_EQ"),

 LONGER("greater", "idsc:LONGER"),

 LONGER_EQ("greaterEqual", "idsc:LONGER_EQ"),

 // # Binary Operators comparing spatial entities
 INSIDE("equals", "idsc:INSIDE");

 private final String mydataOp;
 private final String odrlOp;

 Operator(String op1, String op2) {
  mydataOp = op1;
  odrlOp = op2;
 }

 public String getMydataOp() {
  return mydataOp;
 }

 public String getOdrlOp() {
  return odrlOp;
 }

}
