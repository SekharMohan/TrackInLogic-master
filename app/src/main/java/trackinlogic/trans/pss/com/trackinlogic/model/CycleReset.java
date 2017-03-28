package trackinlogic.trans.pss.com.trackinlogic.model;


import java.io.Serializable;
import java.util.HashMap;

import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class CycleReset extends BaseModel implements Serializable {
    public static HashMap<String, CycleResetMetaData> CYCLE_RESET_TYPES = new HashMap();
    public static final String TYPE_24_HOUR = "24_hour";
    public static final String TYPE_34_HOUR = "34_hour";
    public static final String TYPE_36_HOUR = "36_hour";
    public static final String TYPE_72_HOUR = "72_hour";
    public static final String TYPE_AK_24_HOUR = "ak_24_hour";
    public static final String TYPE_AK_34_HOUR = "ak_34_hour";
    public static final String TYPE_CA_24_HOUR = "ca_24_hour";
    public static final String TYPE_CA_34_HOUR = "ca_34_hour";
    public static final String TYPE_TX_24_HOUR = "tx_24_hour";
    public static final String TYPE_TX_34_HOUR = "tx_34_hour";
    public String end_time;
    private long end_time_long;
    public String start_time;
    private long start_time_long;
    public String status;
    public String type;


    public CycleReset(CycleReset cycleReset) {
        super((BaseModel) cycleReset);
        this.start_time = cycleReset.start_time;
        this.end_time = cycleReset.end_time;
        this.status = cycleReset.status;
        this.type = cycleReset.type;
        this.start_time_long = cycleReset.start_time_long;
        this.end_time_long = cycleReset.end_time_long;
    }

    public CycleReset(String start_time, String end_time, String type, String status) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.type = type;
        this.status = status;
    }

    public long start_time_long() {
        if (this.start_time_long == 0) {
            this.start_time_long = TimeUtil.convertTimeToSeconds(this.start_time);
        }
        return this.start_time_long;
    }

    public long end_time_long() {
        if (this.end_time_long == 0) {
            this.end_time_long = TimeUtil.convertTimeToSeconds(this.end_time);
        }
        return this.end_time_long;
    }

    public String hos_equivalency_string() {
        return String.format("%s:%s:%s:%s", new Object[]{this.status, this.type, this.start_time, this.end_time});
    }
}
