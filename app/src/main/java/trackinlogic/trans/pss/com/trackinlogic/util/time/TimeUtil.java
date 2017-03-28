package trackinlogic.trans.pss.com.trackinlogic.util.time;

import android.text.TextUtils;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class TimeUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String SERVER_TIME_FORMAT_MILLI_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String SERVER_TIME_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String TAG = "TimeUtil";
    private static final String TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String TIME_FORMAT_MILLI = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static Date getDate(String time, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format, Locale.US).parse(safeTime(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    private static String safeTime(String time) {
        return time.replace("Z", "+00:00");
    }

    public static String applyTimeZoneOffset(String time, int offset) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(offset * CloseFrame.NORMAL, "UTC"));
        return dateFormat.format(getDate(time, TIME_FORMAT));
    }

    public static String displayTimeOfDay(long time, boolean showSeconds, String timezone) {
        return displayTimeOfDay(time, showSeconds ? "h:mm:ss a" : "h:mm a", timezone);
    }

    public static String displayTimeOfDay(long time, String format, String timezone) {
        Date date = new Date(1000 * time);
        int offset = TimeZoneUtil.getTimeZoneOffset(time, timezone);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(offset * CloseFrame.NORMAL, "UTC"));
        return dateFormat.format(date);
    }

    public static int getHour(long time, String timezone) {
        Date date = new Date(1000 * time);
        int offset = TimeZoneUtil.getTimeZoneOffset(time, timezone);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH", Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(offset * CloseFrame.NORMAL, "UTC"));
        return Integer.parseInt(dateFormat.format(date));
    }

    public static int getMinute(long time, String timezone) {
        Date date = new Date(1000 * time);
        int offset = TimeZoneUtil.getTimeZoneOffset(time, timezone);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm", Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(offset * CloseFrame.NORMAL, "UTC"));
        return Integer.parseInt(dateFormat.format(date));
    }

    public static String displayPrettyElapsedTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return new PrettyTime().format(getDate(time, TIME_FORMAT));
    }

    public static String getPrettyDuration(int seconds, boolean showSeconds) {
        return getPrettyDuration(seconds, showSeconds, false);
    }

    public static String getPrettyDuration(int seconds, boolean showSeconds, boolean showZeroes) {
        if (seconds <= 0) {
            seconds = 0;
        }
        int totalSeconds = seconds;
        if (!(showSeconds || totalSeconds % 60 == 0)) {
            totalSeconds += 60;
        }
        int hour = totalSeconds / 3600;
        int minute = (totalSeconds % 3600) / 60;
        int second = totalSeconds % 60;
        String format = "";

        if (hour != 0 || showZeroes) {
            format = format + hour + " hr ";
        }
        if (minute != 0 || showZeroes) {
            format = format + minute + " min ";
        }
        if (showSeconds && second != 0) {
            format = format + second + " sec";
        }
        if (totalSeconds == 0 || (!showSeconds && totalSeconds < 60)) {
            format = "0 hr 0 min";
        }
        return format.trim();
    }

    public static String getHoursDecimalFromSeconds(int seconds) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((double) (((float) seconds) / 3600.0f));
    }

    public static String createCurrentTime() {
        return new SimpleDateFormat(TIME_FORMAT, Locale.US).format(new Date());
    }

    public static String createCurrentTimeUTC() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(SERVER_TIME_FORMAT_UTC, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        return dateFormat.format(new Date());
    }

    public static long currentTimeNearestInterval(int secondInterval, boolean roundUp) {
        long time = ((System.currentTimeMillis() / 1000) / ((long) secondInterval)) * ((long) secondInterval);
        if (roundUp) {
            return time + ((long) secondInterval);
        }
        return time;
    }

    public static String createServerFormattedCurrentTime(int secondInterval, boolean roundUp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(SERVER_TIME_FORMAT_UTC, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        return dateFormat.format(Long.valueOf(new Date(currentTimeNearestInterval(secondInterval, roundUp) * 1000).getTime()));
    }

    public static String createServerFormattedTime(String day, int minutes, int offset) {
        String time = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(new SimpleTimeZone(0, "UTC"));
            calendar.setTime(dateFormat.parse(day));
            calendar.setTimeInMillis((calendar.getTimeInMillis() + ((long) ((minutes * 60) * CloseFrame.NORMAL))) - ((long) (offset * CloseFrame.NORMAL)));
            time = convertSecondsToTime(calendar.getTimeInMillis() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static long convertTimeToMilliSeconds(String time) {
        long convertTime = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(SERVER_TIME_FORMAT_MILLI_UTC, Locale.US);
            dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(new SimpleTimeZone(0, "UTC"));
            calendar.setTime(dateFormat.parse(time));
            convertTime = calendar.getTimeInMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertTime;
    }

    public static long convertTimeToSeconds(String time) {
        long convertTime = 0;
        try {
            new SimpleDateFormat(SERVER_TIME_FORMAT_UTC, Locale.US).setTimeZone(new SimpleTimeZone(0, "UTC"));
            return getDate(time, TIME_FORMAT).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return convertTime;
        }
    }

    public static String convertSecondsToTime(long seconds) {
        String result ="";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(SERVER_TIME_FORMAT_UTC, Locale.US);
            dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
            result = dateFormat.format(new Date(1000 * seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String displayTimeStamp() {
        return new SimpleDateFormat("MM/dd HH:mm:ss:SSS", Locale.US).format(new Date());
    }
}
