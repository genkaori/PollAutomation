package com.framgia.automation.funjapan.util;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XLSHelper_1 {

	public static String filelocation;

	private static HSSFWorkbook wb = null;
	private static HSSFSheet ws = null;

	public static void main(String[] args) {
		readXLS();
		int startRw = 2;
		int endRw = 2;
		int sheetNo = 1;
		retrieveCellsMulti(sheetNo , startRw, endRw);

		// retrieveCells(r);
	}

	public static void readXLS() {
		FileInputStream ipstr = null;
		try {
			ipstr = new FileInputStream("C:\\Users\\doan.thi.phuong.thao\\Desktop\\FunJapan_Automation\\data\\data.xls");
			wb = new HSSFWorkbook(ipstr);

			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int retrieveNoOfRows(int sheetNo) {
		return wb.getSheetAt(sheetNo).getLastRowNum();
	}

	public static int retrieveNoOfCols(int sheetNo) {
		return wb.getSheetAt(sheetNo).getRow(0).getLastCellNum();
	}

	public static Object[][] retrieveCellsMulti(int sheetNo, int startRw, int endRw) {
		ws = wb.getSheetAt(sheetNo);
		int colNum = retrieveNoOfCols(sheetNo);

		Object[][] data = new Object[endRw - startRw + 1][colNum];
		int element = 0;
		for (int i = startRw - 1; i < endRw; i++) {
			HSSFRow row = ws.getRow(i);

			System.out.println(element);
			for (int j = 0; j < colNum; j++) {
				if (row.getCell(j) == null) {

					data[element][j] = "";

				} else {
					data[element][j] = row.getCell(j).getStringCellValue();

					System.out.println(row.getCell(j).getStringCellValue());

				}

			}
			element++;

		}
		return data;
	}

}
