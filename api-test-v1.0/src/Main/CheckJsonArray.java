package Main;

import com.jayway.jsonpath.JsonPathException;

import net.minidev.json.JSONArray;

public class CheckJsonArray {
	static boolean isJsonArray(Object queriedDescription) {
		if (queriedDescription instanceof JSONArray) {
			// It's an array
			return true;
		} else {
			// It's an object
			return false;
		}

	}
}
