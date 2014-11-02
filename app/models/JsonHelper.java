package models;

import java.util.List;
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
    	String res = "{ \"all\": [";
    	
    	for (int i = 0; i < list.size(); i++) {
    		res += "\"" + list.get(i) + "\"";
    		if (i < list.size() - 1)
    			res += ",";
    	}
    	
    	return res + "]}";
	}
}
