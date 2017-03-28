package trackinlogic.trans.pss.com.trackinlogic.model;

import android.content.Context;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class Event extends BaseModel implements Serializable {
    public static final String DRIVING = "driving";
    private static Map<String, MetaData> EVENT_META_DATA = new HashMap();
    public static final String OFF_DUTY = "off_duty";
    public static final String ON_DUTY = "on_duty";
    public static final String PC = "personal_conveyance";
    public static final String SLEEPER = "sleeper";
    private static final String TAG = "Event";
    public static final String WAITING = "waiting";
    public static final String YM = "yard_move";
    public int duration;
    public String eld_event_offline_id;
    public String end_sds_eld_event_offline_id;
    public long end_time_long;
    public float end_x;
    public String location;
    public String notes;
    public String start_sds_eld_event_offline_id;
    public float start_x;
    public String time;
    private long time_long;
    public String type;

    static class MetaData {
        int button_index;
        int color;
        int drawable;
        int drawable_round;
        int name;
        int name_abbreviation;
        int name_alt;
        int status_index;
        String type;

        public MetaData(String type, int status_index, int button_index, int drawable, int drawable_round, int name, int name_abbreviation, int name_alt, int color) {
            this.type = type;
            this.status_index = status_index;
            this.button_index = button_index;
            this.drawable = drawable;
            this.drawable_round = drawable_round;
            this.name = name;
            this.name_abbreviation = name_abbreviation;
            this.name_alt = name_alt;
            this.color = color;
        }
    }
public  Event(){

}

    public Event(String time, String type) {
        this.time = time;
        this.type = type;
    }

    public Event(String time, String type, String location, String notes) {
        this.time = time;
        this.type = type;
        this.location = location;
        this.notes = notes;
    }

    public Event(Event event) {
        super((BaseModel) event);
        this.time = event.time;
        this.type = event.type;
        this.notes = event.notes;
        this.location = event.location;
        this.eld_event_offline_id = event.eld_event_offline_id;
        this.start_sds_eld_event_offline_id = event.start_sds_eld_event_offline_id;
        this.end_sds_eld_event_offline_id = event.end_sds_eld_event_offline_id;
        this.duration = event.duration;
        this.start_x = event.start_x;
        this.end_x = event.end_x;
        this.time_long = event.time_long;
        this.end_time_long = event.end_time_long;
    }



    public void set_time(String time) {
        this.time = time;
        this.time_long = TimeUtil.convertTimeToSeconds(time);
    }

    public long time_long() {
        if (this.time_long == 0) {
            this.time_long = TimeUtil.convertTimeToSeconds(this.time);
        }
        return this.time_long;
    }

    public long end_time_long() {
        return this.end_time_long;
    }

    public boolean equals(Event event) {
        return event != null && TextUtils.equals(this.offline_id, event.offline_id);
    }

    public boolean is_break() {
        return TextUtils.equals(this.type, OFF_DUTY) || TextUtils.equals(this.type, SLEEPER) || TextUtils.equals(this.type, WAITING);
    }

    public boolean eld_enabled() {
        return !TextUtils.isEmpty(this.eld_event_offline_id);
    }

    public boolean is_special_driving_status() {
        return !TextUtils.isEmpty(this.start_sds_eld_event_offline_id);
    }

    public static String get_status(int index) {
        for (MetaData event : EVENT_META_DATA.values()) {
            if (event.status_index == index) {
                return event.type;
            }
        }
        return null;
    }

    public String get_unique_status() {
        if (is_special_driving_status()) {
            String str = this.type;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1326270154:
                    if (str.equals(ON_DUTY)) {
                        obj = 1;
                        break;
                    }
                    break;
                case -774483034:
                    if (str.equals(OFF_DUTY)) {
                        obj = null;
                        break;
                    }
                    break;
            }

        }
        return this.type;
    }

    public static int get_status_index(String type) {
        return TextUtils.isEmpty(type) ? -1 : 0;
    }

    public static int get_button_index(Event e) {
        return TextUtils.isEmpty(e.get_unique_status()) ? -1 : ((MetaData) EVENT_META_DATA.get(e.get_unique_status())).button_index;
    }

    public static String get_status_name(Context context, Event e) {
        return context.getResources().getString(((MetaData) EVENT_META_DATA.get(e.get_unique_status())).name);
    }

    public static String get_status_name_abbreviation(Context context, String type) {
        return context.getResources().getString(((MetaData) EVENT_META_DATA.get(type)).name_abbreviation);
    }

    public static boolean is_event_status_equal(Event e1, Event e2) {
        if (!e1.is_special_driving_status() && !e2.is_special_driving_status()) {
            return TextUtils.equals(e1.type, e2.type);
        }
        if (e1.is_special_driving_status() && e2.is_special_driving_status()) {
            return TextUtils.equals(e1.start_sds_eld_event_offline_id, e2.start_sds_eld_event_offline_id);
        }
        return false;
    }

    public static int get_drawable_round(Event event) {
        if (TextUtils.isEmpty(event.type)) {
            return R.drawable.arrow;
        }
        return ((MetaData) EVENT_META_DATA.get(event.get_unique_status())).drawable_round;
    }

    public static int get_color(String type) {
        return ((MetaData) EVENT_META_DATA.get(type)).color;
    }


}
