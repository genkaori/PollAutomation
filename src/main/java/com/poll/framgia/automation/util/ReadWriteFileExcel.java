package com.poll.framgia.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import com.poll.framgia.automation.bean.UserBean;


public class ReadWriteFileExcel {

	private Object getCellValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();
		default:
			break;
		}

		return null;
	}

	public List<UserBean> readDataExcel(String excelFilePath) throws IOException {
		List<UserBean> listPreData = new ArrayList<UserBean>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = getWorkbook(inputStream, excelFilePath);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();

			Iterator<Cell> cellIterator = nextRow.cellIterator();
			UserBean user = new UserBean();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					user.setEmail((String) getCellValue(nextCell));
					break;
				case 1:
					user.setPassword((String) getCellValue(nextCell));
					break;
					
				}
				
			}
			
			listPreData.add(user);
		}
		listPreData.remove(0);
		workbook.close();
		inputStream.close();

		return listPreData;
	}
	
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}
	
	public void writeExcell(String fileInputStream, String fileOutputStream, List inputList) {
		System.out.println("list write=====>" + inputList);
		try (InputStream is = new FileInputStream(fileInputStream)) {
			try (OutputStream os = new FileOutputStream(fileOutputStream)) {
				Context context = new Context();
				context.putVar("results", inputList);
				JxlsHelper.getInstance().processTemplate(is, os, context);

			} catch (Exception e) {

			}
		} catch (Exception e) {

		}
	}
	
}
