package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HitApi {

	public static String getResponse(String TCFilepath, String TCTagname, int TCNo) throws IOException {

		String a = null;

		Map<Integer, Map<String, String>> TCInfoAPI = new HashMap<Integer, Map<String, String>>();
		TCInfoAPI = JustReadTC.getTCInfo(TCFilepath, TCTagname);

		String urldata = TCInfoAPI.get(TCNo).get("URI");

		URL url = new URL(urldata);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		} else {
			System.out.println("API Resonse Code: 200");
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		System.out.println("API response body is: ");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			a = output;
		}
		return a;
	}

}
