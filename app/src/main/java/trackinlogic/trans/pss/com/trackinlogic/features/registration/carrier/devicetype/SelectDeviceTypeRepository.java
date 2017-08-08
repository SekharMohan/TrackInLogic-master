package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeModel;

/**
 * Created by Sekhar Madhiyazhagan on 7/15/2017.
 */

public class SelectDeviceTypeRepository {


    TrackInServices trackInServices;
    private final BehaviorSubject<List<DeviceTypeModel>> publishSubject = BehaviorSubject.create();
    private final BehaviorSubject<DeviceTypeModel> postPublishSubject = BehaviorSubject.create();

    public SelectDeviceTypeRepository(TrackInServices trackInServices) {
        this.trackInServices = trackInServices;

    }

    public Observable<List<DeviceTypeModel>> getDriverDevices(int carrierId, boolean details, boolean inactive) {
        return trackInServices.getDriverDevices(carrierId, details, inactive).doOnNext(deviceTypeModels ->
                publishSubject.onNext(deviceTypeModels));
    }

    public Observable<DeviceTypeModel> postDriverDevice(int carrierId, DeviceTypeInputPayLoad device) {
        return trackInServices.postDriverDevice(carrierId, device).doOnNext(deviceTypeModel -> postPublishSubject.onNext(deviceTypeModel));
    }


}
