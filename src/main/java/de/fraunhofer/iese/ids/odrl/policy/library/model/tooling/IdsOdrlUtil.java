/**
 * 
 */
package de.fraunhofer.iese.ids.odrl.policy.library.model.tooling;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.utils.JsonUtils;

import de.fraunhofer.iese.ids.odrl.policy.library.model.OdrlPolicy;

/**
 * @author Robin Brandstaedter <Robin.Brandstaedter@iese.fraunhofer.de>
 *
 */
public class IdsOdrlUtil {
	
	
	@SuppressWarnings("rawtypes")
	public static Map getMapOfJsonString(String odrlPolicyString) {
		Map odrlPolicyMap = null;
		try {
			Object o = JsonUtils.fromString(odrlPolicyString);
			if (o instanceof Map) {
				odrlPolicyMap = (Map) o;
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return odrlPolicyMap;
	}
	
	public static OdrlPolicy getOdrlPolicy(@SuppressWarnings("rawtypes") Map map , String version) {
		if("IM4".equalsIgnoreCase(version)) {
			return Im4PatternUtil.getPolicy(map, true);
		}
		return PatternUtil.getPolicy(map, true);
	}
	
	@SuppressWarnings("rawtypes")
	public static OdrlPolicy getOdrlPolicy(String odrlPolicyString, String version) {
		Map map = getMapOfJsonString(odrlPolicyString);
		return getOdrlPolicy(map, version);
	}

}
