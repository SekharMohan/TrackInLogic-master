package trackinlogic.trans.pss.com.trackinlogic.interfaces;

import okhttp3.ResponseBody;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public interface TrackInServices {

    Observable<ResponseBody> postRegistration(Registration registration);
    Observable<CarrierDetails> getCarrierDetails(String dotId,boolean details,boolean inactive);
}
