package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 7/1/2017.
 */

public class BaseQueryStringRequest {
    boolean details;
    boolean inactive;

    public BaseQueryStringRequest(boolean details, boolean inactive) {
        this.details = details;
        this.inactive = inactive;
    }

    public boolean isDetails() {
        return details;
    }

    public void setDetails(boolean details) {
        this.details = details;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
}
