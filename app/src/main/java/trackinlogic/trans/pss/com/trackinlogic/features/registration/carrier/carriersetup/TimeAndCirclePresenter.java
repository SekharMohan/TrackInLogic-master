package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup;

import com.f2prateek.dart.HensonNavigable;

import java.util.List;

import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.BaseQueryStringRequest;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeAndCircleReuestQuery;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneResonse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TripCycle;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */


public class TimeAndCirclePresenter extends BasePresenter<TimeAndCirclePresenter.View> {
    private TimeAndCircleRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public TimeAndCirclePresenter(TimeAndCircleRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }

    @Override
    protected void onViewAttached(View view) {
        view.init();
        TimeAndCircleReuestQuery query = view.getTimeZone();
        addAttachedSubscription(repository.getOdometers(query.isDetails(),query.isInactive()).observeOn(uiScheduler).subscribe(odoMeterResponseList ->
                view.setOdometers(odoMeterResponseList),Throwable::printStackTrace));
        addAttachedSubscription(repository.getTimeZones( query.isDetails(), query.isInactive()).observeOn(uiScheduler).subscribe(timeZoneResonseList ->
                view.setTimeZones(timeZoneResonseList), Throwable::printStackTrace));
        addAttachedSubscription(repository.getServiceType().observeOn(uiScheduler).subscribe(serviceType ->view.setServiceType(serviceType), Throwable::printStackTrace));

    }

    interface View {

        void init();


        public void setTimeZones(List<TimeZoneResonse> timeZoneResonseList);

        public void setTimeZone(TimeZoneResonse timeZoneResonse);

        /*   public void postTimeZonesResponse(List<TimeZoneResonse> timeZoneResonseList);
           public void postTimeZoneResponse(TimeZoneResonse timeZoneResonse);*/
        public TimeAndCircleReuestQuery getTimeZone();

        public BaseQueryStringRequest getTimeZones();

        public TimeAndCircleReuestQuery getOdometer();

        public BaseQueryStringRequest getOdometers();

        public TimeAndCircleReuestQuery getCargoType();

        public BaseQueryStringRequest getCargoTypes();

        public TimeZoneData postTimeZoneBody();

        public List<TimeZoneData> postTimeZonesBody();

        public OdoMeterData postOdometerBody();

        public List<OdoMeterData> postOdometersBody();

        public CycleRuleData postCargoTypeBody();

        public List<CycleRuleData> postCargoTypesBody();

        public void setOdometer(OdoMeterResponse odoMeterResponse);

        public void setOdometers(List<OdoMeterResponse> odoMeterResponseList);

        public void setCargoType(CycleRuleResponse cycleRuleResponse);

        public void setCargoTypes(List<CycleRuleResponse> cycleRuleResponseList);
        public void setServiceType(List<TripCycle> serviceType);

    }
}
