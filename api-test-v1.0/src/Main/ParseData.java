package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class ParseData {

	public static String ParseMydata(String TCFilepath, String TCTagname, int TCNo) throws IOException, ParseException {	

		String querieddata = null;

		Map<Integer, Map<String, String>> TCInfoAPIParse = new HashMap<Integer, Map<String, String>>();
		TCInfoAPIParse = JustReadTC.getTCInfo(TCFilepath, TCTagname);

		String jsonString = HitApi.getResponse(TCFilepath, TCTagname, TCNo);
		String jsonExp = TCInfoAPIParse.get(TCNo).get("JsonPath");

		if (TCInfoAPIParse.get(TCNo).get("ExpectedValue").equalsIgnoreCase("true")) {
			boolean querieddata1 = JsonPath.read(jsonString, jsonExp);
			querieddata = Boolean.toString(querieddata1);
		} else {
			Object querieddata2 = JsonPath.read(jsonString, jsonExp);
			Boolean flag = CheckJsonArray.isJsonArray(querieddata2);
			if (flag==false){
				Object querieddata3 = JsonPath.read(jsonString, jsonExp);
				querieddata=querieddata3.toString();
			}
			else
				{
				ArrayList<String> list = new ArrayList<String>();     
				JSONArray jsonArray = (JSONArray)querieddata2; 
				if (jsonArray != null) { 
				   int len = jsonArray.size();
				   for (int i=0;i<len;i++){ 
				    list.add(jsonArray.get(i).toString());
				   } 
				} 
				querieddata=list.get(0).toString();
				}
		}
		return querieddata;
	}
}
