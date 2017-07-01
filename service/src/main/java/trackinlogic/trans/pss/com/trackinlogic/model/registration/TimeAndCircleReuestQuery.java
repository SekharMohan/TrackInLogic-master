package trackinlogic.trans.pss.com.trackinlogic.model.registration;

/**
 * Created by Sekhar Madhiyazhagan on 7/1/2017.
 */

public class TimeAndCircleReuestQuery extends  BaseQueryStringRequest {

    int detailsId;

    public TimeAndCircleReuestQuery(boolean details, boolean inactive, int detailsId) {
        super(details, inactive);
        this.detailsId = detailsId;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }
}
