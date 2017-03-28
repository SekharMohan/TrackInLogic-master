package trackinlogic.trans.pss.com.trackinlogic.util.time;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import trackinlogic.trans.pss.com.trackinlogic.R;

public class DateUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TAG = "DateUtil";

    public static Date getDate(String date, String format) {
        Date d = null;
        try {
            d = new SimpleDateFormat(format, Locale.US).parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String getFormattedDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT, Locale.US).format(getDate(date, "MM.dd.yyyy"));
    }

    public static String displayMonth(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat("MMMM", Locale.US).format(getDate(date, DATE_FORMAT));
    }

    public static String displayMonthShort(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat("MMM", Locale.US).format(getDate(date, DATE_FORMAT));
    }

    public static String displayMonthAndDayShort(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return displayMonthShort(date) + " " + displayDayOfMonth(date);
    }

    public static String displayDayOfMonth(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat("dd", Locale.US).format(getDate(date, DATE_FORMAT));
    }

    public static String displayPrettyDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(getDate(date, DATE_FORMAT));
    }

    public static String displayDayOfWeek(String date, boolean shortFormat) {
        if (TextUtils.isEmpty(date)) {
            return "";
        }
        return new SimpleDateFormat(shortFormat ? "EEE" : "EEEE", Locale.US).format(getDate(date, DATE_FORMAT));
    }

    public static List<String> getLastDates(int totalDays, long timeZoneOffset, String generateAfterThisTime) {
        List<String> dates = new ArrayList();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        long now = System.currentTimeMillis() / 1000;
        long today = ((now + timeZoneOffset) - ((now + timeZoneOffset) % 86400)) * 1000;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTime(new Date(today));
        String todayDate = dateFormat.format(calendar.getTime());
        if (generateAfterThisTime == null || todayDate.compareTo(generateAfterThisTime) > 0) {
            dates.add(todayDate);
        }
        for (int i = 0; i < totalDays - 1; i++) {
            calendar.add(Calendar.DAY_OF_YEAR,-1 );
            String date = dateFormat.format(calendar.getTime());
            if (generateAfterThisTime == null || date.compareTo(generateAfterThisTime) > 0) {
                dates.add(0, dateFormat.format(calendar.getTime()));
            }
        }
        return dates;
    }

    public static long convertDateToSeconds(String date, String timeZone) {
        long seconds = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
            dateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
            TimeZone timezone = TimeZone.getTimeZone(TimeZoneUtil.getTimeZoneName(timeZone));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timezone);
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.HOUR_OF_DAY,Calendar.MINUTE);
            return calendar.getTimeInMillis() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return seconds;
        }
    }

    public static String getDate(long time) {
        return getDate(time, null);
    }

    public static String getDate(long time, String timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        if (timeZone != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return dateFormat.format(Long.valueOf(1000 * time));
    }

    public static String getDate() {
        return getDate(System.currentTimeMillis() / 1000);
    }

    public static String messageTimestamp(Context context, Date sentDate, Date currentDate) {
        String timeText;
        Calendar sentCal = Calendar.getInstance();
        sentCal.setTimeInMillis(sentDate.getTime());
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTimeInMillis(currentDate.getTime());
        if (sentCal.get(Calendar.DATE) == currentCal.get(Calendar.DATE)) {
            timeText = context.getString(R.string.messages_today) + ", " + DateFormat.format("h:mm a", sentCal);
        } else if (currentCal.get(Calendar.DATE) - sentCal.get(Calendar.DATE) == 1) {
            timeText = context.getString(R.string.messages_yesterday) + ", " + DateFormat.format("h:mm a", sentCal);
        } else if (currentCal.get(Calendar.YEAR) == sentCal.get(Calendar.YEAR)) {
            timeText = DateFormat.format("MMM d, h:mm a", sentCal).toString();
        } else {
            timeText = DateFormat.format("MM/dd/yyyy, h:mm a", sentCal).toString();
        }
        return timeText.replace("am", "AM").replace("pm", "PM");
    }

    public static String conversationTimestamp(Context context, Date sentDate, Date currentDate) {
        String timeText;
        Calendar sentCal = Calendar.getInstance();
        sentCal.setTimeInMillis(sentDate.getTime());
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTimeInMillis(currentDate.getTime());
        if (sentCal.get(Calendar.DATE) == currentCal.get(Calendar.DATE)) {
            timeText = DateFormat.format("h:mm a", sentCal).toString();
        } else if (currentCal.get(Calendar.DATE) - sentCal.get(Calendar.DATE) == 1) {
            timeText = context.getString(R.string.messages_yesterday);
        } else if (currentCal.get(Calendar.YEAR) == sentCal.get(Calendar.YEAR)) {
            timeText = DateFormat.format("MMM d", sentCal).toString();
        } else {
            timeText = DateFormat.format("MM/dd/yyyy", sentCal).toString();
        }
        return timeText.replace("am", "AM").replace("pm", "PM");
    }
}
