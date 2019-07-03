package Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.parser.ParseException;

public class TCExecuteLogic {
	public static void execute(String TCFilepath, String TCTagname) throws IOException, ParseException {
		Map<Integer, Map<String, String>> TCInfo = new HashMap<Integer, Map<String, String>>();
		TCInfo = JustReadTC.getTCInfo(TCFilepath, TCTagname);
		int TotalTC = TCInfo.size();
		for (int i = 0; i < TotalTC; i++) {
			int UITC = i + 1;
			System.out.println("--------------Results for TestCaseID: " + UITC + "----------------");
			String APIParsedData = ParseData.ParseMydata(TCFilepath, TCTagname, i);
			String ExpectedAPIResponse = TCInfo.get(i).get("ExpectedValue");
			WriteActutalOuptput.WriteOutput(TCFilepath, TCTagname, i, APIParsedData);
			Compare.CompareData(APIParsedData, ExpectedAPIResponse, TCFilepath, TCTagname, i);
		}
	}
}
