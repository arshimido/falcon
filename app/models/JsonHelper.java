package models;

import java.util.List;

import play.libs.Json;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
/**
 * @author mohamed
 *
 * JsonHelper class, converting from/to Json to/from String
 */
public class JsonHelper {

	/**
	 * Construct json format list from list of strings
	 * 
	 * @param list : list of Strings
	 * @return String : constructed json object
	 */
	public static String construct(List<String> list) {
		ObjectNode node = Json.newObject();
		ArrayNode arr = node.putArray("all");
    	for (String temp : list) {
    		arr.add(temp);
    	}
    	return node.toString();
	}
	
	public static String toJsonString(String parameter) {
		ObjectNode node = (ObjectNode) Json.parse(parameter);
		return node.toString();
	}
}
