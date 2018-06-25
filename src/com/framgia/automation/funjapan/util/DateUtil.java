package com.framgia.automation.funjapan.util;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

public abstract class DateUtil {

  public static final int INVALID_DATE = 1;
  public static final int TOO_BIG_DAY = 2;
  public static final int TOO_SMALL_DAY = -2;
  public static final int TOO_BIG_MONTH = 3;
  public static final int TOO_SMALL_YEAR = -4;
  public static final int TOO_BIG_YEAR = 4;

  public static String toDMYDate(java.sql.Date date, String token) {
    if (date == null) {
      return null;
    }
    String format = "dd" + token + "MM" + token + "yyyy";
    return (new SimpleDateFormat(format).format(date));
  }

  public static String toYMDDate(Date date, String token) {
    if (date == null) {
      return null;
    }
    String format = "yyyy" + token + "MM" + token + "dd";
    return (new SimpleDateFormat(format).format(date));
  }

  public static java.sql.Date toSqlDate(java.util.Date date) {
    if (date == null) {
      return null;
    }
    java.sql.Date date2 = new java.sql.Date(date.getTime());
    return date2;
  }

  public static Timestamp toTimeStamp(java.util.Date date) {
    if (date == null) {
      return null;
    }
    Timestamp timestamp = new Timestamp(date.getTime());
    return timestamp;
  }

  public static String extractDefaultDate(Date date) {
    if (date == null) {
      return "";
    }
    return new SimpleDateFormat("yyyy-MM-dd")

        .format(date);

  }

  public static String extractDefaultDate(Object value) {

    Date date = null;

    if (value == null) {
      return null;
    }

    if (value instanceof Date) {

      date = (Date) value;

    }

    if (value instanceof java.sql.Date) {

      date = new Date( ( (java.sql.Date) value).getTime());

    }

    if (date == null) {
      return "";
    }

    return extractDefaultDate(date);

  }

  public static String extractYMDDate(Date date) {
    if(date == null) {
      return "";
    }

    Calendar cal = Calendar.getInstance();

    cal.setTime(date);

    return extractYMDDate(cal);

  }

  public static int getYear(Date date) {
    if (date == null) {
      return 0;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int year = cal.get(Calendar.YEAR);
    return year;
  }

  public static String getStringYear(Date date) {
    String sYear = "";
    if (date == null) {
      return sYear;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int year = cal.get(Calendar.YEAR);
    sYear = year + "";
    return sYear;
  }

  public static int getMonth(Date date) {
    if (date == null) {
      return 0;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int month = cal.get(Calendar.MONTH) + 1;
    return month;
  }

  public static int getDay(Date date) {
      if (date == null) {
        return 0;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      int day = cal.get(Calendar.DAY_OF_MONTH);
      return day;
  }
  public static String getStringMonth(Date date) {
    String sMonth = "";
    if (date == null) {
      return sMonth;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int month = cal.get(Calendar.MONTH) + 1;
    if (month < 10) {
      sMonth = "0" + month;
    } else {
      sMonth = month + "";
    }
    return sMonth;
  }

  public static String getStringDay(Date date) {
    String sDay = "";
    if (date == null) {
      return sDay;
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    if (day < 10) {
      sDay = "0" + day;
    } else {
      sDay = day + "";
    }
    return sDay;
  }

  public static String extractYMDDate(Object value) {
    Date date = null;
    if (value == null) {
      return null;
    }

    if (value instanceof Date) {

      date = (Date) value;

    }

    if (value instanceof java.sql.Date) {

      date = new Date( ( (java.sql.Date) value).getTime());

    }

    if (date == null) {
      return "";
    }

    return extractYMDDate(date);

  }

  public static int getCurrentYear() {
    return Calendar.getInstance().get(Calendar.YEAR);
  }

  public static int getCurrentMonth() {
    return Calendar.getInstance().get(Calendar.MONTH);
  }

  public static int getCurrentDayofMonth() {
    return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
  }

  public static String getCurrentDate(String dateFormat) {
    return new SimpleDateFormat(dateFormat)
        .format(Calendar.getInstance().getTime());
  }

  public static String getCurrentDate() {
    return getCurrentDate("yyyy-MM-dd");
  }

  public static java.sql.Date getCurrentSqlDate() {
    return toSqlDate(Calendar.getInstance().getTime());
  }

  public static Timestamp getCurrentTimestamp() {
    return toTimeStamp(Calendar.getInstance().getTime());
  }

  public static String getCurrentYMDDate() {
    return new SimpleDateFormat("yyyy-MM-dd")
        .format(Calendar.getInstance().getTime());
  }

  public static String extractDefaultDate(Calendar cal) {
    return new SimpleDateFormat("yyyy-MM-dd").
        format(cal.getTime());
  }

  public static String extractYMDDate(java.sql.Date date) {
    if (date == null) {
      return "";
    }
    return new SimpleDateFormat("yyyy-MM-dd").
        format(date);
  }

  public static String getShortYear(Calendar calendar) {
    return new SimpleDateFormat("yy").format(calendar.getTime());
  }

  public static String getCurrentShortYear() {
    return new SimpleDateFormat("yy").format(Calendar.getInstance().getTime());
  }

  public static String getShortYear(Date date) {
    return new SimpleDateFormat("yy").format(date);
  }

  /**
   * extract date from Calendar Object
   * @param cal Calendar
   * @return date in the form of yyyy-mm-dd
   */
  public static String extractYMDDate(Calendar cal) {
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH);

    String date = cal.get(Calendar.YEAR) + "-";

    if (month < 10) {
      date += "0" + month + "-";
    } else {
      date += month + "-";

    }
    if (day < 10) {
      date += "0" + day;
    } else {
      date += day;
    }
    return date;
  }

  /**
   * convert date string to Calendar Object
   * @param dateMDY : data   value in format MM/dd/yyyy
   * @return Calendar
   */
  public static Calendar convertMDYDate(String dateMDY) {

    Vector vctTemp = CommonUtil.splitToVector(dateMDY, "/");

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vctTemp.elementAt(1) + ""));
    cal.set(Calendar.MONTH, Integer.parseInt(vctTemp.elementAt(0) + "") - 1);
    cal.set(Calendar.YEAR, Integer.parseInt(vctTemp.elementAt(2) + ""));
    return cal;
  }

  /**
   * convert date string to Calendar Object
   * @param dateMDY : data   value in format dd/MM/yyyy
   * @return Calendar
   */
  public static Calendar convertDMYDate(String dateDMY) {

    Vector vctTemp = CommonUtil.splitToVector(dateDMY, "/");

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vctTemp.elementAt(0) + ""));
    cal.set(Calendar.MONTH, Integer.parseInt(vctTemp.elementAt(1) + "") - 1);
    cal.set(Calendar.YEAR, Integer.parseInt(vctTemp.elementAt(2) + ""));

    return cal;
  }

  /**
   * convert date string to Calendar Object
   * @param dateMDY : data   value in format yyyy-mm-dd
   * @return Calendar
   */
  public static Calendar convertYMDDate(String dateYMD) {
    Calendar cal = Calendar.getInstance();

    if (!CommonUtil.isEmpty(dateYMD)) {
      Vector vctTemp = CommonUtil.splitToVector(dateYMD, "-");
      cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vctTemp.elementAt(2) + ""));
      cal.set(Calendar.MONTH, Integer.parseInt(vctTemp.elementAt(1) + "") - 1);
      cal.set(Calendar.YEAR, Integer.parseInt(vctTemp.elementAt(0) + ""));
    } else {
      return null;
    }

    return cal;
  }

  public static String convertYMD2Default(String dateYMD) {
    if (CommonUtil.isEmpty(dateYMD)) {
      return "";
    }
    return new SimpleDateFormat("yyyy-MM-dd")
        .format(java.sql.Date.valueOf(dateYMD));
  }

  public static String convertDate2Default(Date date) {
    if (date == null) {
      return "";
    }
    return new SimpleDateFormat("yyyy-MM-dd").
        format(date);
  }

  public static String convertTimestamp2Default(Timestamp timestamp) {
    if (timestamp == null) {
      return "";
    }
    return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(timestamp);
  }

  public static String convertDate2Default(Timestamp date) {
    if (date == null) {
      return "";
    }
    return new SimpleDateFormat("yyyy-MM-dd").
        format(new Date(date.getTime()));
  }

  public static boolean checkIntNumber(String value) {
    String pattern = "0123456789";
    for (int i = 0; i < value.length(); i++) {
      if (!pattern.contains(value.substring(i, i + 1))) {
        return false;
      }
    }
    return true;
  }

  public static Calendar convertDefaultDate(String dateDefault, String token) {
    Vector vctDateValue = CommonUtil.splitToVector(dateDefault, token);
    if (CommonUtil.isEmpty(vctDateValue) || vctDateValue.size() != 3) {
      return null;
    }

    Vector vctDateFormat = CommonUtil.splitToVector("yyyy-MM-dd", token);

    int date = 0, month = 0, year = 0;
    for (int i = 0; i < vctDateFormat.size(); i++) {
      if (!checkIntNumber(vctDateValue.elementAt(i).toString())) {
        return null;
      }

      String datePart = (String) vctDateFormat.elementAt(i);
      if (datePart.contains("d") || datePart.contains("D")) {
        date = Integer.parseInt(vctDateValue.elementAt(i).toString());
      }

      if (datePart.contains("m") || datePart.contains("M")) {
        month = Integer.parseInt(vctDateValue.elementAt(i).toString());

      }
      if (datePart.contains("y") || datePart.contains("Y")) {
        year = Integer.parseInt(vctDateValue.elementAt(i).toString());
      }
    }

    if (month > 12 || month < 1) {
      return null;
    }

    if (year < 1) {
      return null;
    }

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
        month == 10 || month == 12) {
      if (date < 1 || date > 31) {
        return null;
      }
    }

    if (month == 4 || month == 6 || month == 9 || month == 11) {
      if (date < 1 || date > 30) {
        return null;
      }
    }

    if (month == 2) {
      if (year % 4 == 0) {
        if (date < 1 || date > 29) {
          return null;
        }
      } else {
        if (date < 1 || date > 28) {
          return null;
        }
      }
    }

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, date);
    cal.set(Calendar.MONTH, month - 1);
    cal.set(Calendar.YEAR, year);

    return cal;
  }

  public static String convertDefault2YMD(String dateDefault, String token) {
    if (convertDefaultDate(dateDefault, token) == null) {
      return "";
    }
    return extractYMDDate(convertDefaultDate(dateDefault, token));
  }

  public static String convertDefault2YMD(String dateDefault) {
    if (convertDefaultDate(dateDefault, "/") == null) {
      return "";
    }
    return extractYMDDate(convertDefaultDate(dateDefault, "/"));
  }

  /**
   * convert date string to java.sql.Date
   * @param dateMDY : data   value in system format
   * @return java.sql.Date
   */
  public static java.sql.Date stringToDate(String dateDMY) {
    if (CommonUtil.isEmpty(dateDMY)) {
      return null;
    }
    int posDay = dateDMY.indexOf("/");
    if (posDay < 0) {
      return null;
    }

    String strDay = dateDMY.substring(0, posDay);
    if (!CommonUtil.isDigitString(strDay)) {
      return null;
    }

    int day = Integer.parseInt(strDay);
    if (day < 1 || day > 31) {
      return null;
    }

    int posMonth = dateDMY.indexOf("/", posDay + 1);
    if (posMonth < 0) {
      return null;
    }

    String strMonth = dateDMY.substring(posDay + 1, posMonth);
    if (!CommonUtil.isDigitString(strMonth)) {
      return null;
    }
    int month = Integer.parseInt(strMonth);
    if (month < 1 || month > 12) {
      return null;
    }

    String strYear = dateDMY.substring(posMonth + 1);
    if (!CommonUtil.isDigitString(strYear)) {
      return null;
    }
    int year = Integer.parseInt(strYear);

    if (month == 2) {
      if (year % 4 == 0) {
        if (day > 29) {
          return null;
        }
      } else {
        if (day > 28) {
          return null;
        }
      }
    }

    if (month == 4 || month == 6 || month == 9 || month == 11) {
      if (day > 30) {
        return null;
      }
    }
    return java.sql.Date.valueOf(year + "-" + month + "-" + day);
  }

  /**
   * convert date from short format to long format
   * @param dateMDY : data   value in format MM/dd/yyyy
   * @return date in long format
   */
  public static String toLongDate(String dateMDY) {
    String result = "";
    String dayOfWeek[] = {
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
        "Saturday"};

    String monthOfYear[] = {
        "January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"};

    Calendar cal = convertMDYDate(dateMDY);
    result += dayOfWeek[cal.get(Calendar.DAY_OF_WEEK) - 1] + ", " +
        monthOfYear[cal.get(Calendar.MONTH)] + " ";

    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    result += dayOfMonth;
    switch (dayOfMonth % 100) {
      case 11:
      case 12:
      case 13:
        result += "th";
        break;

      default:
        switch (dayOfMonth % 10) {
          case 1:
            result += "st";
            break;
          case 2:
            result += "nd";
            break;
          case 3:
            result += "rd";
            break;
          default:
            result += "th";
            break;
        }
        break;
    }
    result += " " + cal.get(Calendar.YEAR);
    return result;
  }

  /**
   *
   * @param date : input date value
   * @return String
   */
  public static String formatDate(String date) {
    if (CommonUtil.isEmpty(date)) {
      return date;
    }

    String result = "";
    Vector vctValue = CommonUtil.splitToVector(date, "/");
    for (int i = 0; i < vctValue.size(); i++) {
      String value = (String) vctValue.elementAt(i);
      if (value.length() == 1) {
        value = "0" + value;
      }
      if (i < vctValue.size() - 1) {
        result += value + "/";
      } else {
        result += value;
      }
    }

    return result;
  }

  /**
   *
   * @param date : checked date (with format dd/MM/yyyy)
   * @param fromDate : beginning date of period (with format dd/MM/yyyy)
   * @param toDate : end date of period (with format dd/MM/yyyy)
   * @return boolean
   */
  public static boolean dayInPeriod(String date, String fromDate, String toDate) {
    if (stringToDate(date).before(stringToDate(fromDate)) ||
        stringToDate(date).after(stringToDate(toDate))) {
      return false;
    } else {
      return true;
    }
  }

  public static String getFirstDateOfQuarter(int quarter, int year) {
    int month = 3 * (quarter - 1) + 1;
    // int month = 3 * (quarter - 1) + quarter;
    return getFirstDateOfMonth(month, year);
  }

  public static String getFirstDateOfMonth(int month, int year) {
    String strMonth;
    if (month < 10) {
      strMonth = "0" + month;
    } else {
      strMonth = month + "";

    }
    return year + "-" + strMonth + "-" + "1";
  }

  public static String getLastDateOfQuarter(int quarter, int year) {
    int month = 3 * (quarter - 1) + 3;
    //int month = 3 * (quarter) + 1;
    return getLastDateOfMonth(month, year);
  }

  public static String getFirstDateOfInstalment(int periodTime, int year) {
    int month = 3 * (periodTime / 3 - 1) + 1;
    return getFirstDateOfMonth(month, year);
  }

  public static String getLastDateOfInstalment(int periodTime, int year) {
    int month = 3 * (periodTime / 3 - 1) + 3;
    return getLastDateOfMonth(month, year);
  }

  public static String getFirstDateOf6MonthYearEnd(int periodTime, int year) {
    int month = periodTime + 1;
    return getFirstDateOfMonth(month, year);
  }

  public static String getLastDateOf6MonthYearEnd(int periodTime, int year) {
    int month = periodTime * 2;
    return getLastDateOfMonth(month, year);
  }

  public static String getLastDateOfMonth(int month, int year) {
    int date = -1;

    if (month == 1 || month == 3 || month == 5 || month == 7 ||
        month == 8 || month == 10 || month == 12) {
      date = 31;
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      date = 30;
    } else if (month == 2) {
      if (year % 4 == 0) {
        date = 29;
      } else {
        date = 28;
      }
    }

    String strMonth;
    if (month < 10) {
      strMonth = "0" + month;
    } else {
      strMonth = month + "";

    }
    return year + "-" + strMonth + "-" + date;
  }

  public static int getCountDayofMonth(int month, int year) {
    int day = -1;

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12) {
      day = 31;
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      day = 30;
    } else if (month == 2) {
      if (year % 4 == 0) {
        day = 29;
      } else {
        day = 28;
      }
    }
    return day;
  }

  public static boolean isDMYDate(String dateDMY) {
    Date date = stringToDate(dateDMY);
    return (date != null);
  }

  public static String getFirstDayOfCurrentYear() {
    return "01/01/" + DateUtil.getCurrentYear();
  }

  public static String getLastDayOfCurrentYear() {
    return "31/12/" + DateUtil.getCurrentYear();
  }

  public static String validateDate(String strDMYDate) {
    //condition:
    // + strDMYDate is not empty
    // + strDMYDate includes digits and '/' only

    String result = "";
    int day = 0, month = 0, year = 0;

    String temp = strDMYDate;
    String str = "";
    int pos = temp.indexOf("/");
    if (pos < 0) {
      day = Integer.parseInt(temp);
      month = DateUtil.getCurrentMonth() + 1;
      year = DateUtil.getCurrentYear();
    } else {
      str = temp.substring(0, pos);
      if (CommonUtil.isEmpty(str)) {
        return null;
      }
      day = Integer.parseInt(str);

      temp = temp.substring(pos + 1);
      if (CommonUtil.isEmpty(temp)) {
        month = DateUtil.getCurrentMonth() + 1;
        year = DateUtil.getCurrentYear();
      } else {
        pos = temp.indexOf("/");
        if (pos < 0) {
          month = Integer.parseInt(temp);
          year = DateUtil.getCurrentYear();
        } else {
          str = temp.substring(0, pos);
          if (CommonUtil.isEmpty(str)) {
            month = DateUtil.getCurrentMonth() + 1;
            year = DateUtil.getCurrentYear();
          } else {
            month = Integer.parseInt(str);

            str = temp.substring(pos + 1);
            if (CommonUtil.isEmpty(str)) {
              year = DateUtil.getCurrentYear();
            } else {
              year = Integer.parseInt(str);
            }

          }
        }
      }
    }
    //validate
    if (day < 1 || day > 31) {
      return null;
    }
    if (month < 1 || month > 12) {
      return null;
    }
    if (year < DateUtil.getCurrentYear() - 100 ||
        year > DateUtil.getCurrentYear() + 100) {
      return null;
    }

    if (month == 2) {
      if (year % 4 == 0) {
        if (day > 29) {
          return null;
        }
      } else {
        if (day > 28) {
          return null;
        }
      }
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      if (day > 30) {
        return null;
      }
    } else {
      if (day > 31) {
        return null;
      }
    }

    //build result;
    if (day >= 10) {
      result = day + "/";
    } else {
      result = "0" + day + "/";
    }

    if (month >= 10) {
      result += month + "/" + year;
    } else {
      result += "0" + month + "/" + year;
    }
    return result;
  }

  public static long getNumberDay(Date fromDate, Date toDate) {
    if (fromDate.equals(toDate)) {
      return 1;
    }

    Calendar toCalendar = Calendar.getInstance();
    toCalendar.setTime(toDate);

    Calendar fromCalendar = Calendar.getInstance();
    fromCalendar.setTime(fromDate);

    return (toCalendar.getTimeInMillis() - fromCalendar.getTimeInMillis()) /
        (24 * 60 * 60 * 1000);
  }

  public static java.util.Date getPreviousDate(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return new java.util.Date(date.getTime() - 24 * 60 * 60 * 1000);
  }

  public static java.util.Date getNextDate(java.util.Date date) {
    if (date == null) {
      return null;
    }

    return new java.util.Date(date.getTime() + 24 * 60 * 60 * 1000);
  }
}
