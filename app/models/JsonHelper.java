package models;

import java.util.List;

public class JsonHelper {

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
