package trackinlogic.trans.pss.com.trackinlogic.model;

import java.io.Serializable;
import java.util.List;

public class Company extends BaseModel implements Serializable {
    public String city;
    public String company_id;
    public String cycle;
    public List<String> dot_ids;
    public boolean exception_24_hour_restart;
    public boolean exception_8_hour_break;
    public boolean exception_ca_farm_school_bus;
    public boolean exception_short_haul;
    public boolean exception_wait_time;
    public Boolean metric_units;
    public Boolean minute_logs;
    public String name;
    public boolean prevent_driver_to_driver_messages;
    public boolean require_location_on_log_events;
    public String state;
    public String street;
    public String time_zone;
    public boolean vehicles_enabled;
    public String zip;
}
