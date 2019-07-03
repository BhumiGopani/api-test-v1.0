package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteResults {

	public static void WritePass(String TCFilepath, String TCTagname, int TCNo) {

		try {

			FileInputStream file = new FileInputStream(new File(TCFilepath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(TCTagname);
			Cell cell = null;

			// Update the value of cell
			Row row = sheet.getRow(TCNo + 1);
			row.createCell(8).setCellValue("Pass");

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(TCFilepath));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void WriteFail(String TCFilepath, String TCTagname, int TCNo) {

		try {

			FileInputStream file = new FileInputStream(new File(TCFilepath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(TCTagname);
			Cell cell = null;

			// Update the value of cell
			Row row = sheet.getRow(TCNo + 1);
			row.createCell(8).setCellValue("Fail");

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(TCFilepath));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
