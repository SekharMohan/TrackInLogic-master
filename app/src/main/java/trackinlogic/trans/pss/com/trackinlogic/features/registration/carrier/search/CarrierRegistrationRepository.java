package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;

/**
 * Created by Sekhar Madhiyazhagan on 6/26/2017.
 */

public class CarrierRegistrationRepository {

    TrackInServices trackInServices;
    private final BehaviorSubject<CarrierDetails> publishSubject = BehaviorSubject.create();

    public  CarrierRegistrationRepository(TrackInServices trackInServices){
        this.trackInServices = trackInServices;

    }

    public Observable<CarrierDetails> onGettingCarrierAddress(String dotId,boolean details,boolean inactive) {
        return  trackInServices.getCarrierDetails(dotId,details,inactive)
                .doOnNext(carrierDetails ->{publishSubject.onNext(carrierDetails);});
    }
}
