package trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier;

/**
 * Created by Sekhar Madhiyazhagan on 6/26/2017.
 */

public class CarrierQueryString {

    String dotId;
    boolean details;
    boolean inactive;

    public CarrierQueryString(String dotId, boolean details, boolean inactive) {
        this.dotId = dotId;
        this.details = details;
        this.inactive = inactive;
    }

    public String getDotId() {
        return dotId;
    }

    public void setDotId(String dotId) {
        this.dotId = dotId;
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
