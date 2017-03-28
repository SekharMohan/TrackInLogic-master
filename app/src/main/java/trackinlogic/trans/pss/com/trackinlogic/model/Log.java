package trackinlogic.trans.pss.com.trackinlogic.model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class Log extends BaseModel implements Serializable {
    private static final String TAG = "Log";
    public String carrier_city;
    public String carrier_name;
    public String carrier_state;
    public String carrier_street;
    public String carrier_zip;
    @Deprecated
    public String co_driver_company_id;
    @Deprecated
    public String co_driver_first_name;
    public LinkedHashSet<String> co_driver_full_names = new LinkedHashSet();
    public List<Integer> co_driver_ids = new ArrayList();
    @Deprecated
    public String co_driver_last_name;
    public String cycle;
    public String date;
    public String destination;
    public String dot_id;
    public String driver_company_id;
    public String driver_first_name;
    public String driver_last_name;
    public String driver_signature_filename;
    public String driver_signature_url;
    public String driver_signed_at;
    public String eld_mode;
    public boolean exception_24_hour_restart;
    public boolean exception_8_hour_break;
    public boolean exception_ca_farm_school_bus;
    public boolean exception_short_haul;
    public boolean exception_wait_time;

    public String local_driver_signature_url;
    public boolean metric_units;
    @Deprecated
    public Integer miles_driven;
    public String notes;
    @Deprecated
    public Integer odometer_end;
    @Deprecated
    public Integer odometer_start;

    public String origin;
    public String ref_no;
    public boolean short_haul;
    public String terminal_city;
    public String terminal_state;
    public String terminal_street;
    public String terminal_zip;
    public String time_zone;
    public String time_zone_abbreviation;
    public int time_zone_offset;
    public Integer total_miles;
    @Deprecated
    public String tractor_num;
    public LinkedHashSet<String> tractor_nums = new LinkedHashSet();
    @Deprecated
    public String trailer_1_num;
    @Deprecated
    public String trailer_2_num;
    public LinkedHashSet<String> trailer_nums = new LinkedHashSet();
    public String utc_end_time ="10;50 AM";
    private long utc_end_time_long;
    public String utc_start_time = "8:47 AM";
    private long utc_start_time_long;
    public List<Integer> vehicle_ids = new ArrayList();
public Log(){

}
    public Log(Log l) {
        super((BaseModel) l);
        copy_attributes(l);
    }

    public void copy_attributes(Log l) {
        this.date = l.date;
        this.time_zone = l.time_zone;
        this.time_zone_offset = l.time_zone_offset;
        this.time_zone_abbreviation = l.time_zone_abbreviation;
        this.cycle = l.cycle;
        this.exception_24_hour_restart = l.exception_24_hour_restart;
        this.exception_8_hour_break = l.exception_8_hour_break;
        this.exception_wait_time = l.exception_wait_time;
        this.exception_short_haul = l.exception_short_haul;
        this.short_haul = l.short_haul;
        this.exception_ca_farm_school_bus = l.exception_ca_farm_school_bus;
        this.metric_units = l.metric_units;
        this.utc_start_time = l.utc_start_time;
        this.utc_end_time = l.utc_end_time;
        this.driver_signed_at = l.driver_signed_at;
        this.driver_signature_url = l.driver_signature_url;
        this.driver_signature_filename = l.driver_signature_filename;
        this.driver_first_name = l.driver_first_name;
        this.driver_last_name = l.driver_last_name;
        this.driver_company_id = l.driver_company_id;
        this.origin = l.origin;
        this.destination = l.destination;
        this.carrier_name = l.carrier_name;
        this.carrier_street = l.carrier_street;
        this.carrier_city = l.carrier_city;
        this.carrier_state = l.carrier_state;
        this.carrier_zip = l.carrier_zip;
        this.total_miles = l.total_miles;
        this.eld_mode = l.eld_mode;
        this.ref_no = l.ref_no;
        this.notes = l.notes;
        this.dot_id = l.dot_id;
        this.terminal_street = l.terminal_street;
        this.terminal_city = l.terminal_city;
        this.terminal_state = l.terminal_state;
        this.terminal_zip = l.terminal_zip;
        this.vehicle_ids = l.vehicle_ids;
        this.co_driver_ids = l.co_driver_ids;

        this.local_driver_signature_url = l.local_driver_signature_url;
        this.utc_start_time_long = l.utc_start_time_long;
        this.utc_end_time_long = l.utc_end_time_long;
        this.tractor_nums = l.tractor_nums;
        this.trailer_nums = l.trailer_nums;
        this.co_driver_full_names = l.co_driver_full_names;
        this.co_driver_first_name = l.co_driver_first_name;
        this.co_driver_last_name = l.co_driver_last_name;
        this.co_driver_company_id = l.co_driver_company_id;
        this.odometer_start = l.odometer_start;
        this.odometer_end = l.odometer_end;
        this.miles_driven = l.miles_driven;
        this.tractor_num = l.tractor_num;
        this.trailer_1_num = l.trailer_1_num;
        this.trailer_2_num = l.trailer_2_num;
    }

    public boolean needs_log_form_save(Log l) {
        if (!TextUtils.equals(this.driver_first_name, l.driver_first_name) || !TextUtils.equals(this.driver_last_name, l.driver_last_name) || !TextUtils.equals(this.driver_company_id, l.driver_company_id) || !TextUtils.equals(this.origin, l.origin) || !TextUtils.equals(this.destination, l.destination) || !TextUtils.equals(this.carrier_name, l.carrier_name) || !TextUtils.equals(this.carrier_city, l.carrier_city) || !TextUtils.equals(this.carrier_state, l.carrier_state) || !TextUtils.equals(this.carrier_zip, l.carrier_zip)) {
            return true;
        }
        if (this.total_miles == null && l.total_miles != null) {
            return true;
        }
        if (this.total_miles != null && l.total_miles == null) {
            return true;
        }
        if ((this.total_miles == null || l.total_miles == null || this.total_miles.equals(l.total_miles)) && TextUtils.equals(this.ref_no, l.ref_no) && TextUtils.equals(this.notes, l.notes) && TextUtils.equals(this.terminal_street, l.terminal_street) && TextUtils.equals(this.terminal_city, l.terminal_city) && TextUtils.equals(this.terminal_state, l.terminal_state) && TextUtils.equals(this.terminal_zip, l.terminal_zip) && this.vehicle_ids.equals(l.vehicle_ids) && this.co_driver_ids.equals(l.co_driver_ids)) {
            return false;
        }
        return true;
    }

    public synchronized void add_vehicle_id(Integer vehicle_id) {
        if (!(this.vehicle_ids.contains(vehicle_id) || vehicle_id == null)) {
            this.vehicle_ids.add(vehicle_id);
        }

    }


    public synchronized void add_co_driver_id(Integer co_driver_id) {
        if (!(this.co_driver_ids.contains(co_driver_id) || co_driver_id == null)) {
            this.co_driver_ids.add(co_driver_id);
        }

    }







    public boolean reset_today(long time) {
        return time >= utc_start_time_long() && time < utc_end_time_long();
    }

    public boolean is_today() {
        long now = System.currentTimeMillis() / 1000;
        return now >= utc_start_time_long() && now <= utc_end_time_long();
    }


    public boolean is_daylight_savings_23() {
//        return TimeZoneUtil.isDaylightSavings23(this.date, this.time_zone);
        return true;
    }

    public boolean is_daylight_savings_25() {
//        return TimeZoneUtil.isDaylightSavings25(this.date, this.time_zone);
        return true;
    }

    public int hours_in_day_log_chart() {
        return  24;
    }

    public int hours_in_day() {
        if (is_daylight_savings_25()) {
            return 25;
        }
        return is_daylight_savings_23() ? 23 : 24;
    }





    public void set_utc_start_time(String time) {
        this.utc_start_time = time;
        this.utc_start_time_long = TimeUtil.convertTimeToSeconds(this.utc_start_time);
    }

    public void set_utc_end_time(String time) {
        this.utc_end_time = time;
        this.utc_end_time_long = TimeUtil.convertTimeToSeconds(this.utc_end_time);
    }

    public long utc_start_time_long() {
        if (this.utc_start_time_long == 0) {
            this.utc_start_time_long = TimeUtil.convertTimeToSeconds(this.utc_start_time);
        }
        return this.utc_start_time_long;
    }

    public long utc_end_time_long() {
        if (this.utc_end_time_long == 0) {
            this.utc_end_time_long = TimeUtil.convertTimeToSeconds(this.utc_end_time);
        }
        return this.utc_end_time_long;
    }

    public boolean eld_enabled() {
        return !TextUtils.equals(this.eld_mode, "none");
    }

    public int get_event_seconds_interval(User user) {
        if (eld_enabled()) {
            return 1;
        }
        return user.minute_logs ? 60 : 900;
    }


}
