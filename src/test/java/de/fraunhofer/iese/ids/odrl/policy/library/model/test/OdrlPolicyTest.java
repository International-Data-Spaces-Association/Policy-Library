/**
 * 
 */
package de.fraunhofer.iese.ids.odrl.policy.library.model.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.jsonldjava.shaded.com.google.common.base.Strings;

import de.fraunhofer.iese.ids.odrl.policy.library.model.OdrlPolicy;
import de.fraunhofer.iese.ids.odrl.policy.library.model.tooling.IdsOdrlUtil;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */

public class OdrlPolicyTest {
	
	@Test
	public void restrictAccessIntervalTest(){
		String policy = "{"  + System.lineSeparator()  + 
				"  \"@context\" : {" + System.lineSeparator()  + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\"," + System.lineSeparator()  + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"" + System.lineSeparator()  + 
				"  }," + System.lineSeparator()  + 
				"  \"@type\" : \"ids:ContractAgreement\"," + System.lineSeparator()  + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/contractAgreement/f020ed32-9107-4a3c-a6e2-5e395c465366\"," + System.lineSeparator()  + 
				"  \"ids:permission\" : [ {" + System.lineSeparator()  + 
				"    \"@type\" : \"ids:Permission\"," + System.lineSeparator()  + 
				"    \"@id\" : \"https://w3id.org/idsa/autogen/permission/6ecb9f11-283e-4892-8c70-f6a893948411\"," + System.lineSeparator()  + 
				"    \"ids:target\" : {" + System.lineSeparator()  + 
				"      \"@id\" : \"http://w3id.org/engrd/connector/artifact/1\"" + System.lineSeparator()  + 
				"    }," + System.lineSeparator()  + 
				"    \"ids:description\" : [ {" + System.lineSeparator()  + 
				"      \"@value\" : \"Description of contract agreement\"," + System.lineSeparator()  + 
				"      \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"" + System.lineSeparator()  + 
				"    } ]," + System.lineSeparator()  + 
				"    \"ids:assignee\" : [ {" + System.lineSeparator()  + 
				"      \"@id\" : \"https://assignee.com\"" + System.lineSeparator()  + 
				"    } ]," + System.lineSeparator()  + 
				"    \"ids:title\" : [ {" + System.lineSeparator()  + 
				"      \"@value\" : \"Permission title for artifact 1\"," + System.lineSeparator()  + 
				"      \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"" + System.lineSeparator()  + 
				"    } ]," + System.lineSeparator()  + 
				"    \"ids:preDuty\" : [ ]," + System.lineSeparator()  + 
				"    \"ids:action\" : [ {" + System.lineSeparator()  + 
				"      \"@id\" : \"https://w3id.org/idsa/code/USE\"" + System.lineSeparator()  + 
				"    } ]," + System.lineSeparator()  + 
				"    \"ids:postDuty\" : [ ]," + System.lineSeparator()  + 
				"    \"ids:assigner\" : [ {" + System.lineSeparator()  + 
				"      \"@id\" : \"https://assigner.com\"" + System.lineSeparator()  + 
				"    } ]," + System.lineSeparator()  + 
				"    \"ids:constraint\" : [ {" + System.lineSeparator()  + 
				"      \"@type\" : \"ids:Constraint\"," + System.lineSeparator()  + 
				"      \"@id\" : \"https://w3id.org/idsa/autogen/constraint/698cec3e-c4e0-4fd0-9c08-42e3b75ebc70\"," + System.lineSeparator()  + 
				"      \"ids:rightOperand\" : {" + System.lineSeparator()  + 
				"        \"@value\" : \"2022-05-31T09:26:36Z\"," + System.lineSeparator()  + 
				"        \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"" + System.lineSeparator()  + 
				"      }," + System.lineSeparator()  + 
				"      \"ids:leftOperand\" : {" + System.lineSeparator()  + 
				"        \"@id\" : \"https://w3id.org/idsa/code/POLICY_EVALUATION_TIME\"" + System.lineSeparator()  + 
				"      }," + System.lineSeparator()  + 
				"      \"ids:operator\" : {" + System.lineSeparator()  + 
				"        \"@id\" : \"https://w3id.org/idsa/code/AFTER\"" + System.lineSeparator()  + 
				"      }" + System.lineSeparator()  + 
				"    }, {" + System.lineSeparator()  + 
				"      \"@type\" : \"ids:Constraint\"," + System.lineSeparator()  + 
				"      \"@id\" : \"https://w3id.org/idsa/autogen/constraint/fa2f3691-b378-48af-bf99-e74174a3a25a\"," + System.lineSeparator()  + 
				"      \"ids:rightOperand\" : {" + System.lineSeparator()  + 
				"        \"@value\" : \"2022-07-07T09:26:36Z\"," + System.lineSeparator()  + 
				"        \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"" + System.lineSeparator()  + 
				"      }," + System.lineSeparator()  + 
				"      \"ids:leftOperand\" : {" + System.lineSeparator()  + 
				"        \"@id\" : \"https://w3id.org/idsa/code/POLICY_EVALUATION_TIME\"" + System.lineSeparator()  + 
				"      }," + System.lineSeparator()  + 
				"      \"ids:operator\" : {" + System.lineSeparator()  + 
				"        \"@id\" : \"https://w3id.org/idsa/code/BEFORE\"" + System.lineSeparator()  + 
				"      }" + System.lineSeparator()  + 
				"    } ]" + System.lineSeparator()  + 
				"  } ]," + System.lineSeparator()  + 
				"  \"ids:provider\" : {" + System.lineSeparator()  + 
				"    \"@id\" : \"https://provider.com\"" + System.lineSeparator()  + 
				"  }," + System.lineSeparator()  + 
				"  \"ids:consumer\" : {" + System.lineSeparator()  + 
				"    \"@id\" : \"https://consumer.com\"" + System.lineSeparator()  + 
				"  }," + System.lineSeparator()  + 
				"  \"ids:prohibition\" : [ ]," + System.lineSeparator()  + 
				"  \"ids:contractStart\" : {" + System.lineSeparator()  + 
				"    \"@value\" : \"2022-06-07T11:26:36.966+02:00\"," + System.lineSeparator()  + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"" + System.lineSeparator()  + 
				"  }," + System.lineSeparator()  + 
				"  \"ids:contractDate\" : {" + System.lineSeparator()  + 
				"    \"@value\" : \"2022-06-07T11:26:36.964+02:00\"," + System.lineSeparator()  + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"" + System.lineSeparator()  + 
				"  }," + System.lineSeparator()  + 
				"  \"ids:obligation\" : [ ]" + System.lineSeparator()  + 
				"}" + System.lineSeparator();
		
		OdrlPolicy odrlPolicy = IdsOdrlUtil.getOdrlPolicy(policy);
		
		assertTrue(!Strings.isNullOrEmpty(odrlPolicy.toString()));
	}
	

}
