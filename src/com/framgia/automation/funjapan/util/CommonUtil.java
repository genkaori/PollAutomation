package com.framgia.automation.funjapan.util;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class CommonUtil {

	public static final String DIGIT_CHAR = "0123456789";
	public static final String LOW_CASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
	public static final String UPCASE_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String number[] = { "khÃ´ng", "má»™t", "hai", "ba", "bá»‘n", "nÄƒm", "sÃ¡u", "báº£y", "tÃ¡m", "chÃ­n" };

	/**
	 * convert array to vector
	 * 
	 * @param array
	 *            : input array of value
	 * @return java.util.Vector
	 */
	public static Vector arrayToVector(Object[] array) {
		if (array.length == 0) {
			return null;
		}
		Vector result = new Vector();

		for (int i = 0; i < array.length; i++) {
			result.addElement(array[i]);
		}
		return result;
	}

	public static String mergeFromVector(Vector v, String strToken) {
		String strResult = "";

		if (v != null && v.size() > 0) {
			for (int i = 0; i < v.size(); i++) {
				if (strResult.equals("")) {
					strResult = String.valueOf(v.elementAt(i)).trim();
				} else {
					strResult += strToken + String.valueOf(v.elementAt(i)).trim();
				}
			}
		}

		return strResult;
	}

	public static String[] splitToArray(String strSource, String separator) {
		if (CommonUtil.isEmpty(strSource)) {
			return null;
		}

		if (strSource == null || strSource.trim().equals("")) {
			return null;
		}

		Vector vctTemp = new Vector();
		StringTokenizer st = new StringTokenizer(strSource, separator);
		while (st.hasMoreTokens()) {
			vctTemp.add(st.nextToken());
		}

		String[] arrResult = null;

		if (vctTemp.size() > 0) {
			arrResult = new String[vctTemp.size()];
			for (int i = 0; i < vctTemp.size(); i++) {
				arrResult[i] = (String) vctTemp.elementAt(i);
			}
		}
		return arrResult;
	}

	public static Vector splitToVector(String strSource, String separator) {
		if (CommonUtil.isEmpty(strSource)) {
			return null;
		}

		Vector vctResult = new Vector();
		StringTokenizer st = new StringTokenizer(strSource, separator);
		while (st.hasMoreTokens()) {
			vctResult.add(st.nextToken());
		}
		return vctResult;
	}

	public static boolean isEmpty(String strInput) {
		if (strInput == null || strInput.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Hashtable htInput) {
		if (htInput == null || htInput.size() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isEmpty(Vector vctInput) {
		if (vctInput == null || vctInput.size() == 0) {
			return true;
		}

		return false;
	}

	public static boolean findString(String strInput, String strFind) {

		if (strInput.trim().length() == 0) {
			return false;
		}
		if (strInput.contains(strFind)) {
			return true;
		}

		return false;

	}

	public static String replace(String strInput, String strSearch, String strReplace) {
		int i = 0, j = 0;
		StringBuffer strBuffer = new StringBuffer();
		String strTemplate = new String();

		if (strInput.trim().length() == 0) {
			return ("");
		}

		// find and replace all substring
		while ((i <= strInput.length() - 1) && (strInput.indexOf(strSearch, i) != -1)) {
			i = strInput.indexOf(strSearch, i);
			strTemplate = strInput.substring(j, i);
			strBuffer.append(strTemplate).append(strReplace);
			i += strSearch.length();
			j = i;
		}
		if (i <= strInput.length() - 1) {
			strBuffer.append(strInput.substring(j, strInput.length()));

		}
		return strBuffer.toString();
	}

	public static String escapeReport(String xml) {
		xml = replace(xml, "&", "&amp;");

		return xml;

	}

	public static String escapeValue(String value) {
		if (isEmpty(value)) {
			return value;
		}
		String[] replacedSigns = { "&", "<", ">" };
		String[] replacingSigns = { "&amp;", "&lt;", "&gt;" };

		for (int i = 0; i < replacedSigns.length; i++) {
			value = replace(value, replacedSigns[i], replacingSigns[i]);
		}
		return value;
	}

	public static String escapeLtGt(String value) {
		if (isEmpty(value)) {
			return value;
		}
		String[] replacedSigns = { "<", ">" };
		String[] replacingSigns = { "&lt;", "&gt;" };

		for (int i = 0; i < replacedSigns.length; i++) {
			value = replace(value, replacedSigns[i], replacingSigns[i]);
		}
		return value;
	}

	public static String setFixedLength(String text, int fixedLength) {
		int redundant = (fixedLength - text.length()) * 2;
		if (redundant > 0) {
			for (int i = 0; i < redundant; i++) {
				text = text + " ";
			}
		} else {
			text = text.substring(0, fixedLength);
		}
		return text;
	}

	/**
	 * check if string value is integer
	 * 
	 * @param s
	 *            <code>String</code>
	 * @return <code>true</code>, if string value is integer
	 */
	public static boolean isIntegerValue(String s) {
		try {
			Integer.parseInt(s.trim());
			return true;
		} catch (NumberFormatException _nx) {
			return false;
		}
	}

	public static boolean isLongValue(String s) {
		try {
			Long.parseLong(s.trim());
			return true;
		} catch (NumberFormatException _nx) {
			return false;
		}
	}

	public static int parseInt(String s) throws NumberFormatException {
		return Integer.parseInt(s.trim().replace(",", ""));
	}

	public static long parseLong(String s) throws NumberFormatException {
		return Long.parseLong(s.trim().replace(",", ""));
	}

	public static boolean isDecimalNumberValue(String s, int p, int n) {
		try {
			Float.parseFloat(s.trim());
			if ((p > 0) && (s.length() > p + 1)) {
				if (s.substring(s.indexOf(".") + 1, s.length()).length() > n) {
					return false;
				}
			}
			return true;
		} catch (NumberFormatException _nx) {
			return false;
		}
	}

	public static boolean isDoubleValue(String value) {
		try {
			Double.parseDouble(value.trim());
			return true;
		} catch (NumberFormatException _nx) {
			return false;
		}
	}

	public static double parseDouble(String s) throws NumberFormatException {
		return Double.parseDouble(s.trim().replace(",", ""));
	}

	public static boolean isRealNumber(String value) {
		String temp = "-0123456789.";
		value = value.trim();
		if (value.trim().length() == 0) {
			return false;
		}
		int countSign = 0;
		int countDecimal = 0;
		for (int i = 0; i < value.length(); i++) {
			String item = value.substring(i, i + 1);
			if (temp.indexOf(item) < 0) {
				return false;
			}

			if (item.equalsIgnoreCase("-")) {
				if (countSign == 1) {
					return false;
				}
			}
			countSign += 1;

			if (item.equalsIgnoreCase(".")) {
				if (countDecimal == 1) {
					return false;
				}
			}
			countDecimal += 1;
		}
		return true;
	}

	/**
	 * written by Sonph 4 testing purpose
	 * 
	 * @param strLogFilePath
	 *            log file path
	 * @param values
	 *            output string
	 * @param isReset
	 *            reset log file or not
	 */
	public static void out2Log(String strLogFilePath, String values, boolean isReset) {

		try {
			FileOutputStream outputFile = null;
			if (isReset) {
				outputFile = new FileOutputStream(strLogFilePath);
			} else {
				outputFile = new FileOutputStream(strLogFilePath, true);
			}
			OutputStreamWriter writer = new OutputStreamWriter(outputFile, "8859_1");
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
			printWriter.println(values);

			printWriter.close();
		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
	}

	// to encode special charaters used by XML
	public static String encodeXML(String input) {
		// replace reserved characters < > &
		String output = "";
		if (input != null) {
			output = CommonUtil.replace(input, "&", "&amp;");
			output = CommonUtil.replace(output, ">", "&gt;");
			output = CommonUtil.replace(output, "<", "&lt;");
		}
		return output;

	}

	// to decode special charaters used by XML
	public static String decodeXML(String input) {
		// replace reserved characters < > &
		String output = "";
		if (input != null) {
			output = CommonUtil.replace(input, "&gt;", ">");
			output = CommonUtil.replace(output, "&lt;", "<");
			output = CommonUtil.replace(output, "&amp;", "&");
		}
		return output;

	}

	public static String getIpAddress() {
		String ipAddress = "0.0.0.0";
		try {
			InetAddress localaddr = InetAddress.getLocalHost();
			ipAddress = localaddr.getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println(" Can't detect localhost : " + e);
		}
		return ipAddress;
	}

	public static String getIpName() {
		String ipName = "";
		try {
			InetAddress localaddr = InetAddress.getLocalHost();
			ipName = localaddr.getHostName();
		} catch (UnknownHostException e) {
			System.err.println(" Can't detect localhost : " + e);
		}
		return ipName;
	}

	public static String formatCurrency(double value) {
		return new DecimalFormat("#.###.##0").format(value);
	}

	public static String formatCurrency(float value) {
		return new DecimalFormat("#.###.##0").format(value);
	}

	public static String formatNumber(double value) {
		return new DecimalFormat("#.###.##0.00").format(value);
	}

	public static String formatNumber(long value) {
		return new DecimalFormat("#.###.##0").format(value);
	}

	public static String formatNumber(int value) {
		return new DecimalFormat("#.###.##0").format(value);
	}

	public static String formatNumber(float value) {
		return new DecimalFormat("#.###.##0").format(value);
	}

	public static boolean isDigitOrPointString(String str) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		for (int i = 0; i < arrChar.length; i++) {
			if (!Character.isDigit(arrChar[i]) && arrChar[i] != '.') {
				return false;
			}
		}
		return true;
	}

	public static boolean isDigitString(String str) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		for (int i = 0; i < arrChar.length; i++) {
			if (!Character.isDigit(arrChar[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean isDigitString(String str, boolean allowDot) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		if (str.indexOf(".") != str.lastIndexOf(".")) {
			return false;
		}
		for (int i = arrChar.length - 1; i >= 0; i--) {
			if (allowDot && (arrChar[i] == '.')) {
				continue;
			}
			if (!Character.isDigit(arrChar[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean isLetterString(String str) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		for (int i = 0; i < arrChar.length; i++) {
			if (!Character.isLetter(arrChar[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean isLetterOrDigitString(String str) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		for (int i = 0; i < arrChar.length; i++) {
			if (!Character.isLetterOrDigit(arrChar[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean isICDString(String str) {
		if (CommonUtil.isEmpty(str)) {
			return false;
		}
		char[] arrChar = str.toCharArray();
		for (int i = 0; i < arrChar.length; i++) {
			if (!Character.isLetterOrDigit(arrChar[i]) && arrChar[i] != '.') {
				return false;
			}
		}
		return true;
	}

	// create by ThuCT

	public static void move(String oriPath, String desPath, boolean isDelete)

			throws IOException {

		if (isEmpty(oriPath) || isEmpty(desPath)) {

			return;
		}

		java.io.File oriFile = new java.io.File(oriPath);

		if (oriFile.exists()) {

			// Create channel on the source

			FileChannel srcChannel = new FileInputStream(oriFile).getChannel();

			// Create channel on the destination

			FileChannel dstChannel = new FileOutputStream(desPath).getChannel();

			// Copy file contents from source to destination

			dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

			// Close the channels

			srcChannel.close();

			dstChannel.close();

			if (isDelete) {

				oriFile.delete();
			}

		}

	}

	public static String replaceutf8(String strInput, String strSearch, String strReplace) {
		int i = 0, j = 0;
		StringBuffer strBuffer = new StringBuffer();
		String strTemplate = new String();

		if (strInput.trim().length() == 0) {
			return ("");
		}

		// find and replace all substring
		while ((i <= strInput.length() - 1) && (strInput.indexOf(strSearch, i) != -1)) {
			i = strInput.indexOf(strSearch, i);
			strTemplate = strInput.substring(j, i);
			strBuffer.append(strTemplate).append(strReplace);
			i += strSearch.length();
			j = i;
		}
		if (i <= strInput.length() - 1) {
			strBuffer.append(strInput.substring(j, strInput.length()));

		}
		return strBuffer.toString();
	}

	// tienld report
	public static String toUTF8(String isoString) {
		String utf8String = null;
		if (null != isoString && !isoString.equals("")) {
			try {
				byte[] stringBytesISO = isoString.getBytes("UTF-8");
				utf8String = new String(stringBytesISO, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("UnsupportedEncodingException is: " + e.getMessage());
				utf8String = isoString;
			}
		} else {
			utf8String = isoString;
		}
		return utf8String;
	}

	public static String readBaseNumber(int source) {
		if (source == 0) {
			return "";
		}
		String result = "";
		int hundred = source / 100;
		source = source % 100;
		int ten = source / 10;
		source = source % 10;
		int unit = source;
		if (hundred >= 0 && hundred <= 9) {
			result += number[hundred] + " trÄƒm ";
		}
		if (ten > 1 && ten <= 9) {
			result += number[ten] + " ";
		}
		if (ten == 1) {
			result += "mÆ°á»�i ";
		}
		if (ten == 0) {
			if (hundred > 0 && unit > 0) {
				result += "láº» ";
			}
		}
		if (unit > 1) {
			result += number[unit];
		}
		if (unit == 1 && ten > 1) {
			result += "má»‘t";
		}
		if (unit == 1 && ten <= 1) {
			result += "má»™t";
		}
		if (unit == 0 && ten > 1) {
			result += "mÆ°Æ¡i";
		}
		return result.trim();
	}

	public static String number2String(long source) {
		if (source == 0) {
			return "khÃ´ng";
		}
		String result = "";
		if (Long.toString(source).length() > 12) {
			return "";
		}
		int ty = (int) source / 1000000000;
		source = source % 1000000000;
		int trieu = (int) source / 1000000;
		source = source % 1000000;
		int nghin = (int) source / 1000;
		source = source % 1000;
		int dv = (int) source;
		if (readBaseNumber(ty).length() > 0) {
			result += readBaseNumber(ty) + " tá»· ";
		}
		if (readBaseNumber(trieu).length() > 0) {
			result += readBaseNumber(trieu) + " triá»‡u ";
		}
		if (readBaseNumber(nghin).length() > 0) {
			result += readBaseNumber(nghin) + " nghÃ¬n ";
		}
		if (readBaseNumber(dv).length() > 0) {
			result += readBaseNumber(dv);
		}

		if (result.trim().length() > 5 && result.trim().substring(0, 5).equalsIgnoreCase("khÃ´ng")) {
			int length = result.trim().length();
			result = result.substring(11, length);
		}
		return result.trim();
	}

	// TuanCA added
	public static double parseDouble(double value, int p, int s) {
		try {
			if ((p < 0) || (s < 0)) {
				throw new Exception("p & s must > 0");
			}
			String formatString = "";
			if (p == 0) {
				formatString += "##################";
			} else {
				for (int i = 0; i < p - s; i++) {
					formatString += "#";
				}
			}
			if (s > 0) {
				formatString += ".";
				for (int i = 0; i < s; i++) {
					formatString += "#";
				}
			}
			NumberFormat fomatter = new DecimalFormat(formatString);
			return Double.parseDouble(fomatter.format(value));
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static boolean isEng(String str) {
		char[] arrChar = str.trim().toCharArray();

		for (int i = 0; i < arrChar.length; i++) {
			if (!((arrChar[i] >= 'A' && arrChar[i] <= 'Z') || (arrChar[i] >= 'a' && arrChar[i] <= 'z')
					|| (arrChar[i] >= '0' && arrChar[i] <= '9') || arrChar[i] == '_')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isIntegerValue(double value) {
		try {
			String s = value + "";
			if (s.indexOf(".") > -1) {
				if (Integer.parseInt(s.substring(s.indexOf(".") + 1, s.length())) > 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String convertExpression(String pattern) {
		return pattern;
	}

}
