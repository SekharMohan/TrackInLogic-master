package trackinlogic.trans.pss.com.trackinlogic.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterResponse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneData;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneResonse;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.TripCycle;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeModel;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public interface TrackInServices {

    Observable<ResponseBody> postRegistration(Registration registration);
    Observable<CarrierDetails> getCarrierDetails(String dotId,boolean details,boolean inactive);


    Observable<List<TimeZoneResonse>> getTimeZones( boolean details,  boolean inactive);

    Observable<TimeZoneResonse> getTimeZone( int timeZoneId,  boolean details,  boolean inactive);

    Observable<List<TimeZoneResonse>> postTimeZones(List<TimeZoneData> timeZoneData);

    Observable<TimeZoneResonse> postTimeZone( TimeZoneData timeZoneData);

    Observable<List<OdoMeterResponse>> getOdometers( boolean details,  boolean inactive);
    Observable<OdoMeterResponse> getOdometer( int odometerId,  boolean details,  boolean inactive);
    Observable<List<OdoMeterResponse>> postOdometers( List<OdoMeterData> odoMeterDataList);
    Observable<OdoMeterResponse> postOdometer( OdoMeterData odoMeterData);

    Observable<List<CycleRuleResponse>> getCargotypes( boolean details,  boolean inactive);
    Observable<CycleRuleResponse> getCargotype( int cargoTypeId,  boolean details,  boolean inactive);
    Observable<List<CycleRuleResponse>> postCargotypes( List<CycleRuleData> cycleRuleDataList);
    Observable<CycleRuleResponse> postCargotype(CycleRuleData cycleRuleData);

    Observable<List<DeviceTypeModel>> getDriverDevices( int carrierId,  boolean details,  boolean inactive);

    Observable<DeviceTypeModel> postDriverDevice( int carrierId,DeviceTypeInputPayLoad device);


    Observable<List<TripCycle>> getServiceCycler();

}
