package trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier;

import trackinlogic.trans.pss.com.trackinlogic.model.registration.BaseQueryStringRequest;

/**
 * Created by Sekhar Madhiyazhagan on 6/26/2017.
 */

public class CarrierQueryString extends BaseQueryStringRequest{

    String dotId;


    public CarrierQueryString(String dotId, boolean details, boolean inactive) {
        super(details,inactive);
        this.dotId = dotId;

    }

    public String getDotId() {
        return dotId;
    }

    public void setDotId(String dotId) {
        this.dotId = dotId;
    }


}
