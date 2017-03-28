package trackinlogic.trans.pss.com.trackinlogic.model;

import android.content.Context;
import android.text.TextUtils;

import com.mixpanel.android.BuildConfig;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;

import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeZoneUtil;

public class User extends BaseModel implements Serializable {
    private static HashMap<String, Integer> VIOLATION_ALERT_STRINGS = new HashMap();
    private static HashMap<String, Integer> VIOLATION_ALERT_TIMES = new HashMap();
    public static final String VIOLATION_ALERT_TIME_15_MINUTES = "15_minutes";
    public static final String VIOLATION_ALERT_TIME_1_HOUR = "1_hour";
    public static final String VIOLATION_ALERT_TIME_30_MINUTES = "30_minutes";
    public static final String VIOLATION_ALERT_TIME_45_MINUTES = "45_minutes";
    public static final String VIOLATION_ALERT_TIME_NEVER = "never";
    public String authentication_token;
    public Carrier carrier;
    public String carrier_city;
    public String carrier_name;
    public String carrier_state;
    public String carrier_street;
    public String carrier_zip;
    public CompanyConnection company_connection;
    public String created_at;
    public int credit_balance;
    public Integer current_vehicle_id;
    public String cycle;
    public String cycle2;
    public String device_token;
    public String dot_id;
    public String driver_company_id;
    public String drivers_license_number;
    public String drivers_license_state;
    public String eld_mode;
    public String email;
    public boolean exception_24_hour_restart;
    public boolean exception_24_hour_restart2;
    public boolean exception_8_hour_break;
    public boolean exception_8_hour_break2;
    public boolean exception_ca_farm_school_bus;
    public boolean exception_ca_farm_school_bus2;
    public boolean exception_short_haul;
    public boolean exception_short_haul2;
    public boolean exception_wait_time;
    public boolean exception_wait_time2;
    public boolean export_combined;
    public boolean export_odometers;
    public boolean export_recap;
    public String first_name;
    public String idfa;
    public String last_name;
    public boolean location_services_enabled;
    public boolean metric_units;
    public boolean minute_logs;
    public LinkedHashSet<String> mobile_onboarding_steps_completed = new LinkedHashSet();
    public int mobile_sign_in_count;
    public String password;
    public boolean personal_conveyance_enabled;
    public String phone;
    public String role;
    public String status;
    public String terminal_city;
    public String terminal_state;
    public String terminal_street;
    public String terminal_zip;
    public String time_zone;
    public String time_zone_abbreviation;
    public int time_zone_offset;
    public String tractor_num;
    public String trailer_1_num;
    public String username;
    public String utm_campaign;
    public String utm_medium;
    public String utm_source;
    public String violation_alerts;
    public boolean yard_moves_enabled;


    public User(String first_name) {
        this.first_name = first_name;
    }

    public int time_zone_offset() {
        long now = System.currentTimeMillis() / 1000;
        long initial_offset = (long) TimeZoneUtil.getTimeZoneOffset(now, this.time_zone);
        long adjusted = now + initial_offset;
        return TimeZoneUtil.getTimeZoneOffset(((adjusted - (adjusted % 86400)) + 43200) - initial_offset, this.time_zone);
    }

    public Integer company_id() {
        if (this.company_connection == null || this.company_connection.company == null) {
            return null;
        }
        return Integer.valueOf(this.company_connection.company.id);
    }

    public boolean require_location_on_log_events() {
        return (this.company_connection == null || this.company_connection.company == null || !this.company_connection.company.require_location_on_log_events) ? false : true;
    }

    public boolean vehicles_enabled() {
        return (this.company_connection == null || this.company_connection.company == null || !this.company_connection.company.vehicles_enabled) ? false : true;
    }

    public boolean eld_enabled() {
        return !TextUtils.equals(this.eld_mode, "none");
    }

    public boolean secondary_cycle() {
        return !TextUtils.isEmpty(this.cycle2);
    }

    public boolean update_settings_from_log(Log log) {
        boolean update = false;
        if (!TextUtils.equals(this.first_name, log.driver_first_name)) {
            this.first_name = log.driver_first_name;
            update = true;
        }
        if (!TextUtils.equals(this.last_name, log.driver_last_name)) {
            this.last_name = log.driver_last_name;
            update = true;
        }
        if (!TextUtils.equals(this.driver_company_id, log.driver_company_id)) {
            this.driver_company_id = log.driver_company_id;
            update = true;
        }
        if (!TextUtils.equals(this.carrier_name, log.carrier_name)) {
            this.carrier_name = log.carrier_name;
            update = true;
        }
        if (!TextUtils.equals(this.carrier_street, log.carrier_street)) {
            this.carrier_street = log.carrier_street;
            update = true;
        }
        if (!TextUtils.equals(this.carrier_city, log.carrier_city)) {
            this.carrier_city = log.carrier_city;
            update = true;
        }
        if (!TextUtils.equals(this.carrier_state, log.carrier_state)) {
            this.carrier_state = log.carrier_state;
            update = true;
        }
        if (!TextUtils.equals(this.carrier_zip, log.carrier_zip)) {
            this.carrier_zip = log.carrier_zip;
            update = true;
        }
        if (!TextUtils.equals(this.terminal_street, log.terminal_street)) {
            this.terminal_street = log.terminal_street;
            update = true;
        }
        if (!TextUtils.equals(this.terminal_city, log.terminal_city)) {
            this.terminal_city = log.terminal_city;
            update = true;
        }
        if (!TextUtils.equals(this.terminal_state, log.terminal_state)) {
            this.terminal_state = log.terminal_state;
            update = true;
        }
        if (TextUtils.equals(this.terminal_zip, log.terminal_zip)) {
            return update;
        }
        this.terminal_zip = log.terminal_zip;
        return true;
    }

    public int get_violation_alert_time() {
        return VIOLATION_ALERT_TIMES.get(this.violation_alerts) == null ? 0 : ((Integer) VIOLATION_ALERT_TIMES.get(this.violation_alerts)).intValue();
    }

    public String get_violation_alert_string(Context context) {
        Integer id = (Integer) VIOLATION_ALERT_STRINGS.get(this.violation_alerts);
        return id == null ? BuildConfig.VERSION_NAME : context.getResources().getString(id.intValue());
    }

    public static String get_violation_alert_id(Context context, String violation_alert_display_name) {
        for (String id : VIOLATION_ALERT_STRINGS.keySet()) {
            if (TextUtils.equals(violation_alert_display_name, context.getResources().getString(((Integer) VIOLATION_ALERT_STRINGS.get(id)).intValue()))) {
                return id;
            }
        }
        return null;
    }

    public String full_name() {
        String str;
        String name = TextUtils.isEmpty(this.first_name) ? BuildConfig.VERSION_NAME : this.first_name;
        StringBuilder append = new StringBuilder().append(name);
        if (TextUtils.isEmpty(this.last_name)) {
            str = BuildConfig.VERSION_NAME;
        } else {
            str = (TextUtils.isEmpty(name) ? BuildConfig.VERSION_NAME : " ") + this.last_name;
        }
        return append.append(str).toString();
    }


}
