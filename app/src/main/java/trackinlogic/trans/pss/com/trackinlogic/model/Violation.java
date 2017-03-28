package trackinlogic.trans.pss.com.trackinlogic.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class Violation extends BaseModel implements Comparable<Violation>, Comparator<Violation> {
    public static final String AK_CYCLE_70 = "ak_cycle_70";
    public static final String AK_CYCLE_70_P = "ak_cycle_70_p";
    public static final String AK_CYCLE_80 = "ak_cycle_80";
    public static final String AK_CYCLE_80_P = "ak_cycle_80_p";
    public static final String AK_DRIVING_15 = "ak_driving_15";
    public static final String AK_DUTY_20 = "ak_duty_20";
    public static final String BREAK_30 = "break_30";
    public static final String CANADA_BREAK_24 = "canada_break_24";
    public static final String CANADA_BREAK_24_70 = "canada_break_24_70";
    public static final String CANADA_BREAK_24_80 = "canada_break_24_80";
    public static final String CANADA_CYCLE_120 = "canada_cycle_120";
    public static final String CANADA_CYCLE_70 = "canada_cycle_70";
    public static final String CANADA_CYCLE_80 = "canada_cycle_80";
    public static final String CANADA_DAILY_BREAK_10 = "canada_daily_break_10";
    public static final String CANADA_DAILY_BREAK_8 = "canada_daily_break_8";
    public static final String CANADA_DAILY_DRIVING_13 = "canada_daily_driving_13";
    public static final String CANADA_DAILY_DUTY_14 = "canada_daily_duty_14";
    public static final String CANADA_DRIVING_13 = "canada_driving_13";
    public static final String CANADA_DRIVING_15 = "canada_driving_15";
    public static final String CANADA_DUTY_14 = "canada_duty_14";
    public static final String CANADA_DUTY_16 = "canada_duty_16";
    public static final String CANADA_DUTY_18 = "canada_duty_18";
    public static final String CANADA_DUTY_20 = "canada_duty_20";
    public static final String CANADA_OIL_BREAK_3_24 = "canada_oil_break_3_24";
    public static final String CANADA_OIL_DAILY_BREAK_10 = "canada_oil_daily_break_10";
    public static final String CANADA_OIL_DAILY_DRIVING_13 = "canada_oil_daily_driving_13";
    public static final String CANADA_OIL_DAILY_DUTY_14 = "canada_oil_daily_duty_14";
    public static final String CANADA_OIL_DRIVING_13 = "canada_oil_driving_13";
    public static final String CANADA_OIL_DUTY_14 = "canada_oil_duty_14";
    public static final String CANADA_OIL_DUTY_16 = "canada_oil_duty_16";
    public static final String CA_CYCLE_80 = "ca_cycle_80";
    public static final String CA_CYCLE_80_P = "ca_cycle_80_p";
    public static final String CA_DRIVING_10 = "ca_driving_10";
    public static final String CA_DRIVING_12 = "ca_driving_12";
    public static final String CA_DUTY_15 = "ca_duty_15";
    public static final String CA_DUTY_16 = "ca_duty_16";
    public static final String CA_DUTY_16_P = "ca_duty_16_p";
    public static final String CYCLE_60 = "cycle_60";
    public static final String CYCLE_60_P = "cycle_60_p";
    public static final String CYCLE_70 = "cycle_70";
    public static final String CYCLE_70_P = "cycle_70_p";
    public static final String DRIVING_10 = "driving_10";
    public static final String DRIVING_11 = "driving_11";
    public static final String DUTY_14 = "duty_14";
    public static final String DUTY_15 = "duty_15";
    public static final String DUTY_16 = "duty_16";
    public static final String TX_CYCLE_70 = "tx_cycle_70";
    public static final String TX_DRIVING_12 = "tx_driving_12";
    public static final String TX_DUTY_15 = "tx_duty_15";
    public static Map<String, ViolationMetaData> VIOLATION_TYPES = new HashMap();
    public String end_time;
    private Long end_time_long;
    public String start_time;
    private long start_time_long;
    public String type;

    /*static {
        VIOLATION_TYPES.put(DUTY_14, new ViolationMetaData(DUTY_14, R.string.violation_duty_14));
        VIOLATION_TYPES.put(DUTY_16, new ViolationMetaData(DUTY_16, R.string.violation_duty_16));
        VIOLATION_TYPES.put(DRIVING_11, new ViolationMetaData(DRIVING_11, R.string.violation_driving_11));
        VIOLATION_TYPES.put(BREAK_30, new ViolationMetaData(BREAK_30, R.string.violation_break_30));
        VIOLATION_TYPES.put(CYCLE_60, new ViolationMetaData(CYCLE_60, R.string.violation_cycle_60));
        VIOLATION_TYPES.put(CYCLE_70, new ViolationMetaData(CYCLE_70, R.string.violation_cycle_70));
        VIOLATION_TYPES.put(DUTY_15, new ViolationMetaData(DUTY_15, R.string.violation_duty_15));
        VIOLATION_TYPES.put(DRIVING_10, new ViolationMetaData(DRIVING_10, R.string.violation_driving_10));
        VIOLATION_TYPES.put(CYCLE_60_P, new ViolationMetaData(CYCLE_60_P, R.string.violation_cycle_60_p));
        VIOLATION_TYPES.put(CYCLE_70_P, new ViolationMetaData(CYCLE_70_P, R.string.violation_cycle_70_p));
        VIOLATION_TYPES.put(CANADA_DUTY_14, new ViolationMetaData(CANADA_DUTY_14, R.string.violation_canada_duty_14));
        VIOLATION_TYPES.put(CANADA_DUTY_16, new ViolationMetaData(CANADA_DUTY_16, R.string.violation_canada_duty_16));
        VIOLATION_TYPES.put(CANADA_DUTY_18, new ViolationMetaData(CANADA_DUTY_18, R.string.violation_canada_duty_18));
        VIOLATION_TYPES.put(CANADA_DUTY_20, new ViolationMetaData(CANADA_DUTY_20, R.string.violation_canada_duty_20));
        VIOLATION_TYPES.put(CANADA_DRIVING_13, new ViolationMetaData(CANADA_DRIVING_13, R.string.violation_canada_driving_13));
        VIOLATION_TYPES.put(CANADA_DRIVING_15, new ViolationMetaData(CANADA_DRIVING_15, R.string.violation_canada_driving_15));
        VIOLATION_TYPES.put(CANADA_DAILY_DUTY_14, new ViolationMetaData(CANADA_DAILY_DUTY_14, R.string.violation_canada_daily_duty_14));
        VIOLATION_TYPES.put(CANADA_DAILY_DRIVING_13, new ViolationMetaData(CANADA_DAILY_DRIVING_13, R.string.violation_canada_daily_driving_13));
        VIOLATION_TYPES.put(CANADA_DAILY_BREAK_8, new ViolationMetaData(CANADA_DAILY_BREAK_8, R.string.violation_canada_daily_break_8));
        VIOLATION_TYPES.put(CANADA_DAILY_BREAK_10, new ViolationMetaData(CANADA_DAILY_BREAK_10, R.string.violation_canada_daily_break_10));
        VIOLATION_TYPES.put(CANADA_CYCLE_70, new ViolationMetaData(CANADA_CYCLE_70, R.string.violation_canada_cycle_70));
        VIOLATION_TYPES.put(CANADA_CYCLE_80, new ViolationMetaData(CANADA_CYCLE_80, R.string.violation_canada_cycle_80));
        VIOLATION_TYPES.put(CANADA_CYCLE_120, new ViolationMetaData(CANADA_CYCLE_120, R.string.violation_canada_cycle_120));
        VIOLATION_TYPES.put(CANADA_BREAK_24, new ViolationMetaData(CANADA_BREAK_24, R.string.violation_canada_break_24));
        VIOLATION_TYPES.put(CANADA_BREAK_24_70, new ViolationMetaData(CANADA_BREAK_24_70, R.string.violation_canada_break_24_70));
        VIOLATION_TYPES.put(CANADA_BREAK_24_80, new ViolationMetaData(CANADA_BREAK_24_80, R.string.violation_canada_break_24_80));
        VIOLATION_TYPES.put(CANADA_OIL_DRIVING_13, new ViolationMetaData(CANADA_OIL_DRIVING_13, R.string.violation_canada_oil_driving_13));
        VIOLATION_TYPES.put(CANADA_OIL_DUTY_14, new ViolationMetaData(CANADA_OIL_DUTY_14, R.string.violation_canada_oil_duty_14));
        VIOLATION_TYPES.put(CANADA_OIL_DUTY_16, new ViolationMetaData(CANADA_OIL_DUTY_16, R.string.violation_canada_oil_duty_16));
        VIOLATION_TYPES.put(CANADA_OIL_DAILY_DRIVING_13, new ViolationMetaData(CANADA_OIL_DAILY_DRIVING_13, R.string.violation_canada_oil_daily_driving_13));
        VIOLATION_TYPES.put(CANADA_OIL_DAILY_DUTY_14, new ViolationMetaData(CANADA_OIL_DAILY_DUTY_14, R.string.violation_canada_oil_daily_duty_14));
        VIOLATION_TYPES.put(CANADA_OIL_DAILY_BREAK_10, new ViolationMetaData(CANADA_OIL_DAILY_BREAK_10, R.string.violation_canada_oil_daily_break_10));
        VIOLATION_TYPES.put(CANADA_OIL_BREAK_3_24, new ViolationMetaData(CANADA_OIL_BREAK_3_24, R.string.violation_canada_oil_break_3_24));
        VIOLATION_TYPES.put(CA_DRIVING_12, new ViolationMetaData(CA_DRIVING_12, R.string.violation_ca_driving_12));
        VIOLATION_TYPES.put(CA_DUTY_16, new ViolationMetaData(CA_DUTY_16, R.string.violation_ca_duty_16));
        VIOLATION_TYPES.put(CA_DUTY_16_P, new ViolationMetaData(CA_DUTY_16_P, R.string.violation_ca_duty_16_p));
        VIOLATION_TYPES.put(CA_CYCLE_80, new ViolationMetaData(CA_CYCLE_80, R.string.violation_ca_cycle_80));
        VIOLATION_TYPES.put(CA_DRIVING_10, new ViolationMetaData(CA_DRIVING_10, R.string.violation_ca_driving_10));
        VIOLATION_TYPES.put(CA_DUTY_15, new ViolationMetaData(CA_DUTY_15, R.string.violation_ca_duty_15));
        VIOLATION_TYPES.put(CA_CYCLE_80_P, new ViolationMetaData(CA_CYCLE_80_P, R.string.violation_ca_cycle_80_p));
        VIOLATION_TYPES.put(TX_DRIVING_12, new ViolationMetaData(TX_DRIVING_12, R.string.violation_tx_driving_12));
        VIOLATION_TYPES.put(TX_DUTY_15, new ViolationMetaData(TX_DUTY_15, R.string.violation_tx_duty_15));
        VIOLATION_TYPES.put(TX_CYCLE_70, new ViolationMetaData(TX_CYCLE_70, R.string.violation_tx_cycle_70));
        VIOLATION_TYPES.put(AK_CYCLE_70, new ViolationMetaData(AK_CYCLE_70, R.string.violation_ak_cycle_70));
        VIOLATION_TYPES.put(AK_CYCLE_80, new ViolationMetaData(AK_CYCLE_80, R.string.violation_ak_cycle_80));
        VIOLATION_TYPES.put(AK_DUTY_20, new ViolationMetaData(AK_DUTY_20, R.string.violation_ak_duty_20));
        VIOLATION_TYPES.put(AK_DRIVING_15, new ViolationMetaData(AK_DRIVING_15, R.string.violation_ak_driving_15));
        VIOLATION_TYPES.put(AK_CYCLE_70_P, new ViolationMetaData(AK_CYCLE_70_P, R.string.violation_ak_cycle_70_p));
        VIOLATION_TYPES.put(AK_CYCLE_80_P, new ViolationMetaData(AK_CYCLE_80_P, R.string.violation_ak_cycle_80_p));
    }*/

    public Violation(String type, String start_time, String end_time) {
        this.type = type;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Violation(Violation violation) {
        super((BaseModel) violation);
        this.start_time = violation.start_time;
        this.end_time = violation.end_time;
        this.type = violation.type;
        this.start_time_long = violation.start_time_long;
        this.end_time_long = violation.end_time_long;
    }

    public long start_time_long() {
        if (this.start_time_long == 0) {
            this.start_time_long = TimeUtil.convertTimeToSeconds(this.start_time);
        }
        return this.start_time_long;
    }

    public void set_start_time(long time) {
        this.start_time = TimeUtil.convertSecondsToTime(time);
        this.start_time_long = time;
    }

    public Long end_time_long() {
        if (this.end_time_long == null || this.end_time_long.longValue() == 0) {
            this.end_time_long = TextUtils.isEmpty(this.end_time) ? null : Long.valueOf(TimeUtil.convertTimeToSeconds(this.end_time));
        }
        return this.end_time_long;
    }

    public void set_end_time(long time) {
        this.end_time = TimeUtil.convertSecondsToTime(time);
        this.end_time_long = Long.valueOf(time);
    }

    public String hos_equivalency_string() {
        return String.format("%s:%s:%s", new Object[]{this.type, this.start_time, this.end_time});
    }



    public int compareTo(@NonNull Violation another) {
        int diff = this.start_time.compareTo(another.start_time);
        if (diff == 0) {
            return (this.end_time == null || another.end_time == null) ? 0 : this.end_time.compareTo(another.end_time);
        } else {
            return diff;
        }
    }

    public int compare(Violation lhs, Violation rhs) {
        return lhs.compareTo(rhs);
    }
}
