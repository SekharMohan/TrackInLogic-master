package trackinlogic.trans.pss.com.trackinlogic.model;

import android.content.Context;

import com.mixpanel.android.BuildConfig;

import java.io.Serializable;

import trackinlogic.trans.pss.com.trackinlogic.R;

public class CycleResetMetaData extends BaseModel implements Serializable {
    public Integer description_modifier_res_id;
    public int drawable_id;
    public int drawable_log_chart_id;
    public int hours;
    public String type;

    public CycleResetMetaData(String type, int hours, Integer description_modifier_res_id, int drawable_id, int drawable_log_chart_id) {
        this.type = type;
        this.hours = hours;
        this.description_modifier_res_id = description_modifier_res_id;
        this.drawable_id = drawable_id;
        this.drawable_log_chart_id = drawable_log_chart_id;
    }

    public String description(Context context) {
        return BuildConfig.VERSION_NAME + this.hours + context.getString(R.string.hour_restart) + (this.description_modifier_res_id == null ? BuildConfig.VERSION_NAME : context.getString(this.description_modifier_res_id.intValue()));
    }
}
