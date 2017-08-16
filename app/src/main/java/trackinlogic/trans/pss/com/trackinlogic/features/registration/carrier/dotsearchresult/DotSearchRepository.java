package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.homeTerminals.HomeTerminals;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class DotSearchRepository {

    TrackInServices trackInServices;
    private final BehaviorSubject<List<HomeTerminals>> publishSubject = BehaviorSubject.create();

    public  DotSearchRepository(TrackInServices trackInServices){
        this.trackInServices = trackInServices;

    }

    public Observable<List<HomeTerminals>> onGettingHomeTerminals(int carrierId, boolean details, boolean inactive) {
        return  trackInServices.getHomeTerminals(carrierId,details,inactive)
                .doOnNext(homeTerminalses -> publishSubject.onNext(homeTerminalses));
    }
}
