package de.fraunhofer.iese.ids.odrl.policy.library.model;


import java.net.URI;

import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.PartyType;
import lombok.Data;


@Data
public class Party {
 PartyType type;
 String name;
 URI uri;

 public Party()
 {

 }

 public Party(PartyType type, URI uri)
 {
  this.type = type;
  this.uri = uri;
 }

 public Party(PartyType type, String name, URI uri)
 {
  this.type = type;
  this.name = name;
  this.uri = uri;
 }

 public String getName() {
  if(null == name || name.isEmpty())
  {
   // sample: http://example.com/party/my-party
   String[] termSplit = uri.toString().split("/");
   this.name = termSplit[termSplit.length-1];
  }
  return name;
 }

 public URI getUri() {
  return uri;
 }

 @Override
 public String toString() {
  if(null != uri && null != type)
  {
   return  "  \"" + type.getType() + "\": \"" + uri.toString() + "\",    \r\n";
  }
  return "";
 }

}
