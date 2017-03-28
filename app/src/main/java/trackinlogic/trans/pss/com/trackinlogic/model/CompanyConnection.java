package trackinlogic.trans.pss.com.trackinlogic.model;

import android.text.TextUtils;

import java.io.Serializable;

public class CompanyConnection extends BaseModel implements Serializable {
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DEACTIVATED = "deactivated";
    public static final String STATUS_PENDING = "pending";
    public Company company;
    public int company_id;
    public String role;
    public String status;
    public int user_id;

    public String status_string() {
        if (TextUtils.equals(this.status, STATUS_PENDING)) {
            return "Pending Approval";
        }
        if (TextUtils.equals(this.status, STATUS_ACTIVE)) {
            return "Connected";
        }
        return null;
    }
}
