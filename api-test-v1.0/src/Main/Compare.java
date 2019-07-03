package Main;

import java.util.HashMap;
import java.util.Map;

public class Compare {
	public static void CompareData(String ActualAPIResponse, String ExpectedAPIResponse, String TCFilepath,
			String TCTagname, int TCNo) {

		System.out.println("Data Comparision: ");

		Map<Integer, Map<String, String>> TCInfo = new HashMap<Integer, Map<String, String>>();
		TCInfo = JustReadTC.getTCInfo(TCFilepath, TCTagname);

		String match = TCInfo.get(TCNo).get("Match");

		if (match.equalsIgnoreCase("exact")) {
			
			System.out.println("Match used: exact");

			if (ActualAPIResponse.equals(ExpectedAPIResponse)) {
				System.out.println("API Response after parsing with Jsonpath is: " + ActualAPIResponse);
				System.out.println("Expected API Response from TC Sheet is: " + ExpectedAPIResponse);
				System.out.println("Result of Data Comparision: ");
				System.out.println("Actual API and Expected API responses are equal");
				WriteResults.WritePass(TCFilepath, TCTagname, TCNo);
			} else {
				System.out.println("API Response after parsing with Jsonpath is: " + ActualAPIResponse);
				System.out.println("Expected API Response from TC Sheet is: " + ExpectedAPIResponse);
				System.out.println("Result of Data Comparision: ");
				System.out.println("Actual API and Expected API responses are unequal. Data is corrupted");
				WriteResults.WriteFail(TCFilepath, TCTagname, TCNo);
			}
		} else {
			if (match.equalsIgnoreCase("contains")) {
				System.out.println("Match used: contains");
				if (ActualAPIResponse.contains(ExpectedAPIResponse)) {
					System.out.println("API Response after parsing with Jsonpath is: " + ActualAPIResponse);
					System.out.println("Expected API Response from TC Sheet is: " + ExpectedAPIResponse);
					System.out.println("Result of Data Comparision: ");
					System.out.println("Actual API and Expected API responses are equal");
					WriteResults.WritePass(TCFilepath, TCTagname, TCNo);
				} else {
					System.out.println("API Response after parsing with Jsonpath is: " + ActualAPIResponse);
					System.out.println("Expected API Response from TC Sheet is: " + ExpectedAPIResponse);
					System.out.println("Result of Data Comparision: ");
					System.out.println("Actual API and Expected API responses are unequal. Data is corrupted");
					WriteResults.WriteFail(TCFilepath, TCTagname, TCNo);
				}
			}
		}
	}
}
