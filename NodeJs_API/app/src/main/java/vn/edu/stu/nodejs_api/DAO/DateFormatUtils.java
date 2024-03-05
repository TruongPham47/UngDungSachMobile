package vn.edu.stu.nodejs_api.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtils {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        DateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
        return dateTimeFormat.format(date);
    }

    public static String formatTime(Date date) {
        DateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        return timeFormat.format(date);
    }

    public static Date dateFromDateString(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return dateFormat.parse(dateString);
    }

    public static Date dateFromDateTimeString(String dateTimeString) throws ParseException {
        DateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
        return dateTimeFormat.parse(dateTimeString);
    }
}
