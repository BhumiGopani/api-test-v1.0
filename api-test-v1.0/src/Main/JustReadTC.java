package Main;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JustReadTC {

	static Map<Integer, Map<String, String>> getTCInfo(String TCFName, String sheetName1) {

		Map<String, String> TCInfo = new HashMap<String, String>();
		Map<String, String> TCInfoRetain = new HashMap<String, String>();
		Map<Integer, Map<String, String>> B = new HashMap<Integer, Map<String, String>>();
		try {

			FileInputStream mFis = new FileInputStream(TCFName);
			XSSFWorkbook workbook1 = new XSSFWorkbook(mFis);
			int TCFRN = getTCFirstRowNumber(workbook1, sheetName1);
			int TCLRN = getTCLastRowNumber(workbook1, sheetName1);
			// Because we have 5 columns to scan so having this loop
			for (int i = TCFRN; i < TCLRN; i++) {
				// Change col value
				for (int colnum = 0; colnum <= 6; colnum++) {
					Cell KeyCell = getTCExcelCell(workbook1, sheetName1, TCFRN, colnum);
					String KeyCellText = KeyCell.getStringCellValue();
					Cell ValueCell = getTCExcelCell(workbook1, sheetName1, i + 1, colnum);
					String ValueCellText = ValueCell.getStringCellValue();
					TCInfo.put(KeyCellText, ValueCellText);
					// Change col value
					if (colnum == 6) {
						B.put(i, TCInfo);
						TCInfo = TCInfoRetain;
					}
				}
				TCInfoRetain = new HashMap<String, String>();
			}
			workbook1.close();
			mFis.close();

		} catch (Exception e) {
			System.out.println("Error in getting TC Info from TC File " + TCFName + "having sheet " + sheetName1);
		}

		return B;

	}

	public static int getTCFirstRowNumber(XSSFWorkbook workbook, String sheetName) {
		return getFRN(workbook, sheetName);
	}

	private static int getFRN(XSSFWorkbook workbook, String sheetName) {
		int fRowNum = -99;
		if (workbook != null) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet != null)
				fRowNum = sheet.getFirstRowNum();
		}
		return fRowNum;

	}

	public static int getTCLastRowNumber(XSSFWorkbook workbook, String sheetName) {
		return getLRN(workbook, sheetName);
	}

	private static int getLRN(XSSFWorkbook workbook, String sheetName) {
		int lRowNum = -99;
		if (workbook != null) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet != null)
				lRowNum = sheet.getLastRowNum();
		}
		return lRowNum;
	}

	public static Cell getTCExcelCell(XSSFWorkbook workbook, String sheetName, int rowIndex, int cellIndex) {
		Cell cell = null;
		if (workbook != null) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet != null) {
				XSSFRow row = sheet.getRow(rowIndex);
				if (row != null)
					cell = row.getCell(cellIndex);
			}
		}
		return cell;

	}

}
