package test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class Test9 {
	static Logger logger = Logger.getLogger(Test9.class);
	

	public static void main(String[] args) {
		try {
			FileInputStream ipstr = new FileInputStream("/Users/chu.anh.tuan/Projects/java/Umami_Automation/xls/data.xls");
			HSSFWorkbook wb = new HSSFWorkbook(ipstr);
			HSSFSheet ws = wb.getSheetAt(0);
			int rowNum = ws.getLastRowNum() + 1;

			logger.info("rowNum: " + rowNum);
			for (int i = 0; i < rowNum; i++) {
				HSSFRow row = ws.getRow(i);
				int colNum = row.getLastCellNum();
				logger.info("colNum: " + colNum);
				for (int j = 0; j < colNum; j++) {
					logger.info(row.getCell(j).getStringCellValue());
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		List results = new ArrayList();
		Map testResult = new HashMap();
		testResult.put("testsuite", "testsuite 01");
		testResult.put("testcase", "testcase 01");
		testResult.put("teststep", "teststep 01");
		testResult.put("testresult", true);
		results.add(testResult);
		testResult = new HashMap();
		testResult.put("testsuite", "testsuite 02");
		testResult.put("testcase", "testcase 02");
		testResult.put("teststep", "teststep 02");
		testResult.put("testresult", false);
		results.add(testResult);
		try (InputStream is = new FileInputStream("/Users/chu.anh.tuan/Projects/java/Umami_Automation/xls/result-template.xls")) {
			try (OutputStream os = new FileOutputStream("/Users/chu.anh.tuan/Projects/java/Umami_Automation/xls/result-out.xls")) {
				Context context = new Context();
				context.putVar("results", results);
				JxlsHelper.getInstance().processTemplate(is, os, context);
			} catch (Exception e) {
				logger.error(e);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
