package com.scopus.finolane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;

@Component
public class ExcelTest {

	public void exportToExcel(String fileName, String sheetName, List<String> columnList, List<List<String>> dataList)
			throws java.io.IOException {

		try {
			SXSSFWorkbook wb = new SXSSFWorkbook(1000);
			Sheet sheet1 = wb.createSheet("sheetName");
			int excelRow = 0;
			// Create Title row
			Row titleRow = sheet1.createRow(excelRow++);
			for (int i = 0; i < columnList.size(); i++) {
				// Create each column under the row and write the header data
				Cell cell = titleRow.createCell(i);
				cell.setCellValue(columnList.get(i));
			}
			// Set content line
			if (dataList != null && dataList.size() > 0) {
				// Serial numbers start with 1
				int count = 1;
				// Outer layer for circulation
				for (int i = 0; i < dataList.size(); i++) {
					Row dataRow = sheet1.createRow(excelRow++);
					for (int j = -1; j < dataList.get(0).size(); j++) {
						Cell cell = dataRow.createCell(j + 1);
						if (j == -1) {
							cell.setCellValue(count++);
						} else {
							cell.setCellValue(dataList.get(i).get(j));
						}
					}
				}
			}

			// String fileName = "Text" + System.currentTimeMillis() + ".xlsx";
			// environment.getRequiredProperty("directory.filepath")
			fileName = fileName + ".xlsx";
			String saveTo = "H:/";
			File file = new File(saveTo);
			if (!file.exists())
				file.mkdirs();
			StringBuffer sb = new StringBuffer();
			sb.append(saveTo);
			sb.append(fileName);
			OutputStream outputStream = new FileOutputStream(new File(sb.toString()));
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
			wb.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
