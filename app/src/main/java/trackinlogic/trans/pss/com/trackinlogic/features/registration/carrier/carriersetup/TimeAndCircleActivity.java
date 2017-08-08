package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.f2prateek.dart.HensonNavigable;
import com.memoizrlabs.Shank;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.logbook.LogBookActivity;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.BaseQueryStringRequest;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeAndCircleReuestQuery;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneResonse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TripCycle;
@HensonNavigable
public class TimeAndCircleActivity extends BaseActivity implements TimeAndCirclePresenter.View {
    @BindView(R.id.actionbar_right_icon)
    ImageView iViewNext;
    @BindString(R.string.log_settings)
    String navTittle;
    @BindView(R.id.spinnerCycleRule)
    Spinner spinnerCycleRule;
    @BindView(R.id.spinnerOdometer)
    Spinner spinnerOdometer;
    @BindView(R.id.spinnerTimeZone)
    Spinner spinnerTimeZone;
    TimeAndCirclePresenter presenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, TimeAndCircleActivity.class);
    }

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_cycle);

        presenter = Shank.provideNew(TimeAndCirclePresenter.class);
        presenter.onViewAttached(this);

        iViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeAndCircleActivity.this, LogBookActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }


    @Override
    public void init() {
        iViewNext.setBackgroundResource(R.drawable.ic_tick);
        showLodaing();

    }

    @Override
    public void setTimeZones(List<TimeZoneResonse> timeZoneResonseList) {
        List<String> timeZoneList = new ArrayList<>();
        for (TimeZoneResonse timeZoneResonse:timeZoneResonseList) {
            timeZoneList.add(timeZoneResonse.getName());
        }
        spinnerSetup(spinnerTimeZone, timeZoneList, getString(R.string.time_zone_selection));
        dismissLoading();
    }

    @Override
    public void setTimeZone(TimeZoneResonse timeZoneResonse) {

    }

    @Override
    public TimeAndCircleReuestQuery getTimeZone() {
        return new TimeAndCircleReuestQuery(true, true, 1);
    }

    @Override
    public BaseQueryStringRequest getTimeZones() {
        return null;
    }

    @Override
    public TimeAndCircleReuestQuery getOdometer() {
        return null;
    }

    @Override
    public BaseQueryStringRequest getOdometers() {
        return null;
    }

    @Override
    public TimeAndCircleReuestQuery getCargoType() {
        return null;
    }

    @Override
    public BaseQueryStringRequest getCargoTypes() {
        return null;
    }

    @Override
    public TimeZoneData postTimeZoneBody() {
        return null;
    }

    @Override
    public List<TimeZoneData> postTimeZonesBody() {
        return null;
    }

    @Override
    public OdoMeterData postOdometerBody() {
        return null;
    }

    @Override
    public List<OdoMeterData> postOdometersBody() {
        return null;
    }

    @Override
    public CycleRuleData postCargoTypeBody() {
        return null;
    }

    @Override
    public List<CycleRuleData> postCargoTypesBody() {
        return null;
    }

    @Override
    public void setOdometer(OdoMeterResponse odoMeterResponse) {



    }

    @Override
    public void setOdometers(List<OdoMeterResponse> odoMeterResponseList) {
        List<String> odometerList = new ArrayList<>();
        for(OdoMeterResponse odoMeterResponse:odoMeterResponseList) {
            odometerList.add(odoMeterResponse.getUnitName());
        }

        spinnerSetup(spinnerOdometer, odometerList, getString(R.string.odometer_selection));
    }

    @Override
    public void setCargoType(CycleRuleResponse cycleRuleResponse) {



    }

    @Override
    public void setCargoTypes(List<CycleRuleResponse> cycleRuleResponseList) {
        List<String> cargoTypeList = new ArrayList<String>();
        for(CycleRuleResponse cycleRuleResponse : cycleRuleResponseList) {
            cargoTypeList.add(cycleRuleResponse.getName());
        }
        spinnerSetup(spinnerCycleRule, cargoTypeList, getString(R.string.cycle_rule_selection));

    }

    @Override
    public void setServiceType(List<TripCycle> serviceType) {
        List<String> cargoTypeList = new ArrayList<String>();
        for(TripCycle cycleRuleResponse : serviceType) {
            cargoTypeList.add(cycleRuleResponse.getName());
        }
        spinnerSetup(spinnerCycleRule, cargoTypeList, getString(R.string.cycle_rule_selection));

    }
}