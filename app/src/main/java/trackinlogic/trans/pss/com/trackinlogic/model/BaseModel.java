package trackinlogic.trans.pss.com.trackinlogic.model;

import com.mixpanel.android.BuildConfig;

import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;

public class BaseModel implements Serializable {
    private static final String TAG = "BaseModel";
    public int id;
    public String local_updated_at;
    public String locked_at;
    public String offline_id;
    public String parent_id;
    public String sync_status;
    public String updated_at;

    public BaseModel() {
        this.offline_id = UUID.randomUUID().toString().toLowerCase(Locale.US);
        this.parent_id = BuildConfig.VERSION_NAME;
        this.updated_at = BuildConfig.VERSION_NAME;
    }

    public BaseModel(String parentID) {
        this.offline_id = UUID.randomUUID().toString().toLowerCase(Locale.US);
        this.parent_id = parentID;
        this.updated_at = BuildConfig.VERSION_NAME;
    }

    public BaseModel(BaseModel baseModel) {
        this.id = baseModel.id;
        this.offline_id = baseModel.offline_id;
        this.parent_id = baseModel.parent_id;
        this.sync_status = baseModel.sync_status;
        this.updated_at = baseModel.updated_at;
        this.local_updated_at = baseModel.updated_at;
        this.locked_at = baseModel.locked_at;
    }


}
