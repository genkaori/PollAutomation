package com.framgia.automation.funjapan.util;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class XLSHelper {
	public static String filelocation;

	private static HSSFWorkbook wb = null;
	private static HSSFSheet ws = null;

	public static void main(String[] args){
		readXLS();
		int r=2;
		int c=2;
		retrieveCellsMulti(r,c);
	}

	public static void readXLS() {
		FileInputStream ipstr = null;
		try {
			ipstr = new FileInputStream("data/AnhLTN_data.xls");
			wb = new HSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int retrieveNoOfRows() {
		return ws.getLastRowNum() ;
	}

	public static int retrieveNoOfCols() {
		return ws.getRow(0).getLastCellNum();
	}

	public static Object[][] retrieveCellsMulti(int startRw, int endRw) {
		readXLS();
		int colNum = retrieveNoOfCols();

		Object[][] data = new Object[endRw - startRw + 1][colNum];
		int element = 0;
		for (int i = startRw-1; i < endRw; i++) {
			HSSFRow row = ws.getRow(i);

			//System.out.println(element);
			for (int j = 0; j < colNum; j++) {
				if (row.getCell(j) == null) {

					data[element][j] = "";

				} else {
					data[element][j] = row.getCell(j).getStringCellValue();

//					System.out.println(row.getCell(j).getStringCellValue());

				}

			}
			element++;

		}
		return data;
	}

}
