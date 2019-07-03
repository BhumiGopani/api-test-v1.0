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

public class WriteActutalOuptput {

	public static void WriteOutput(String TCFilepath, String TCTagname, int TCNo, String output) {

		try {

			FileInputStream file = new FileInputStream(new File(TCFilepath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(TCTagname);
			Cell cell = null;

			// Update the value of cell
			Row row = sheet.getRow(TCNo + 1);
			// Clear value first
			row.createCell(7).setCellValue("");
			row.createCell(7).setCellValue(output);

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
