package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneResonse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TripCycle;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class TimeAndCircleRepository {

    TrackInServices trackInServices;

    private final BehaviorSubject<List<TimeZoneResonse>> publishTimeZoneListSubject = BehaviorSubject.create();
    private final BehaviorSubject<TimeZoneResonse> publishTimeZoneSubject = BehaviorSubject.create();
    private final BehaviorSubject<List<OdoMeterResponse>> publishOdometerListSubject = BehaviorSubject.create();
    private final BehaviorSubject<OdoMeterResponse> publishOdometerSubject = BehaviorSubject.create();
    private final BehaviorSubject<List<CycleRuleResponse>> publishCycleListSubject = BehaviorSubject.create();
    private final BehaviorSubject<CycleRuleResponse> publisCycleSubject = BehaviorSubject.create();
    private final BehaviorSubject<List<TripCycle>> publishServiceType = BehaviorSubject.create();

    public TimeAndCircleRepository(TrackInServices trackInServices) {
        this.trackInServices = trackInServices;


    }

    Observable<List<TimeZoneResonse>> getTimeZones(boolean details, boolean inactive) {
        return trackInServices.getTimeZones(details, inactive).doOnNext(timeZoneList -> {
            publishTimeZoneListSubject.onNext(timeZoneList);
        });
    }

    Observable<TimeZoneResonse> getTimeZone(int timeZoneId, boolean details, boolean inactive) {
        return trackInServices.getTimeZone(timeZoneId, details, inactive).doOnNext(timeZoneResonse -> publishTimeZoneSubject.onNext(timeZoneResonse));
    }

    Observable<List<TimeZoneResonse>> postTimeZones(List<TimeZoneData> timeZoneData) {
        return trackInServices.postTimeZones(timeZoneData).doOnNext(timeZoneList -> {
            publishTimeZoneListSubject.onNext(timeZoneList);
        });
    }

    Observable<TimeZoneResonse> postTimeZone(TimeZoneData timeZoneData) {
        return trackInServices.postTimeZone(timeZoneData).doOnNext(timeZoneResonse -> publishTimeZoneSubject.onNext(timeZoneResonse));
    }

    Observable<List<OdoMeterResponse>> getOdometers(boolean details, boolean inactive) {
        return trackInServices.getOdometers(details, inactive).doOnNext(odoMeterResponses -> publishOdometerListSubject.onNext(odoMeterResponses));
    }

    Observable<OdoMeterResponse> getOdometer(int odometerId, boolean details, boolean inactive) {
        return trackInServices.getOdometer(odometerId, details, inactive).doOnNext(odoMeterResponse -> publishOdometerSubject.onNext(odoMeterResponse));
    }

    Observable<List<OdoMeterResponse>> postOdometers(List<OdoMeterData> odoMeterDataList) {
        return trackInServices.postOdometers(odoMeterDataList).doOnNext(odoMeterResponses -> publishOdometerListSubject.onNext(odoMeterResponses));

    }

    Observable<OdoMeterResponse> postOdometer(OdoMeterData odoMeterData) {
        return trackInServices.postOdometer(odoMeterData).doOnNext(odoMeterResponse -> publishOdometerSubject.onNext(odoMeterResponse));

    }

    Observable<List<CycleRuleResponse>> getCargotypes(boolean details, boolean inactive) {
        return trackInServices.getCargotypes(details, inactive).doOnNext(cycleRuleResponses -> publishCycleListSubject.onNext(cycleRuleResponses));
    }

    Observable<CycleRuleResponse> getCargotype(int cargoTypeId, boolean details, boolean inactive) {
        return trackInServices.getCargotype(cargoTypeId, details, inactive).doOnNext(cycleRuleResponse -> publisCycleSubject.onNext(cycleRuleResponse));
    }

    Observable<List<CycleRuleResponse>> postCargotypes(List<CycleRuleData> cycleRuleDataList) {
        return trackInServices.postCargotypes(cycleRuleDataList).doOnNext(cycleRuleResponses -> publishCycleListSubject.onNext(cycleRuleResponses));
    }

    Observable<CycleRuleResponse> postCargotype(CycleRuleData cycleRuleData) {
        return postCargotype(cycleRuleData).doOnNext(cycleRuleResponse -> publisCycleSubject.onNext(cycleRuleResponse));
    }

    Observable<List<TripCycle>> getServiceType() {
        return trackInServices.getServiceCycler().doOnNext(serviceTypeResponse -> publishServiceType.onNext(serviceTypeResponse));

    }
}
