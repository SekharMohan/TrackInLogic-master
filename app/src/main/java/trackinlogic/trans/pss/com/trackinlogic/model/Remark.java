package trackinlogic.trans.pss.com.trackinlogic.model;


import java.io.Serializable;

import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class Remark extends BaseModel implements Serializable {
    public String location;
    public String notes;
    public String time;
    private long time_long;


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
}
