package trackinlogic.trans.pss.com.trackinlogic.util.time;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class TimeZoneUtil {
    private static final String TAG = "TimeZoneUtil";
    private static Set<String> dates23Hour = new HashSet();
    private static Set<String> dates25Hour = new HashSet();
    public static Map<String, String> timezoneMap = new HashMap();
    private static Set<String> timezones = new HashSet();

    static {
        timezoneMap.put("Eastern Time (US & Canada)", "America/New_York");
        timezoneMap.put("Indiana (East)", "America/Indiana/Indianapolis");
        timezoneMap.put("Central Time (US & Canada)", "America/Chicago");
        timezoneMap.put("Saskatchewan", "America/Regina");
        timezoneMap.put("Mountain Time (US & Canada)", "America/Denver");
        timezoneMap.put("Arizona", "America/Phoenix");
        timezoneMap.put("Pacific Time (US & Canada)", "America/Los_Angeles");
        timezoneMap.put("Alaska", "America/Anchorage");
        timezoneMap.put("Atlantic Time (Canada)", "Canada/Atlantic");
        timezoneMap.put("UTC", "UTC");
        timezones.add("Eastern Time (US & Canada)");
        timezones.add("Indiana (East)");
        timezones.add("Central Time (US & Canada)");
        timezones.add("Mountain Time (US & Canada)");
        timezones.add("Pacific Time (US & Canada)");
        timezones.add("Alaska");
        timezones.add("Atlantic Time (Canada)");
        dates23Hour.add("2014-03-09");
        dates23Hour.add("2015-03-08");
        dates23Hour.add("2016-03-13");
        dates23Hour.add("2017-03-12");
        dates23Hour.add("2018-03-11");
        dates23Hour.add("2019-03-10");
        dates23Hour.add("2020-03-08");
        dates23Hour.add("2021-03-14");
        dates23Hour.add("2022-03-13");
        dates23Hour.add("2023-03-12");
        dates23Hour.add("2024-03-10");
        dates25Hour.add("2014-11-02");
        dates25Hour.add("2015-11-01");
        dates25Hour.add("2016-11-06");
        dates25Hour.add("2017-11-05");
        dates25Hour.add("2018-11-04");
        dates25Hour.add("2019-11-03");
        dates25Hour.add("2020-11-01");
        dates25Hour.add("2021-11-07");
        dates25Hour.add("2022-11-06");
        dates25Hour.add("2023-11-05");
        dates25Hour.add("2024-11-03");
    }

    public static int getTimeZoneOffset(long time, String timezoneID) {
        String timeZone = (String) timezoneMap.get(timezoneID);
        if (timeZone == null) {
            timeZone = timezoneID;
        }
        TimeZone timezone = TimeZone.getTimeZone(timeZone);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timezone);
        calendar.setTimeInMillis(1000 * time);
        return (calendar.get(Calendar.ZONE_OFFSET) + calendar.get(Calendar.DST_OFFSET)) / CloseFrame.NORMAL;
    }

    public static boolean isDaylightSavings23(String date, String timezone) {
        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(timezone) || !timezones.contains(timezone) || !dates23Hour.contains(date)) {
            return false;
        }
        return true;
    }

    public static boolean isDaylightSavings25(String date, String timezone) {
        if (TextUtils.isEmpty(date) || TextUtils.isEmpty(timezone) || !timezones.contains(timezone) || !dates25Hour.contains(date)) {
            return false;
        }
        return true;
    }

    public static String getTimeZoneName(String timezoneID) {
        return (String) timezoneMap.get(timezoneID);
    }

    public static String getTimeZoneAbbreviation(String timezoneID) {
        return TimeZone.getTimeZone((String) timezoneMap.get(timezoneID)).getDisplayName(false, 0, Locale.US);
    }
}
