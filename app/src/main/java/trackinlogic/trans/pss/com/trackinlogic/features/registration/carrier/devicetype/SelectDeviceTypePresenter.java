package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeModel;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 7/15/2017.
 */

public class SelectDeviceTypePresenter extends BasePresenter<SelectDeviceTypePresenter.View> {

    private SelectDeviceTypeRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public SelectDeviceTypePresenter(SelectDeviceTypeRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }

    @Override
    protected void onViewAttached(View view) {
        view.init();
        if (networkStatus.isConnected()) {
            addAttachedSubscription(repository.getDriverDevices(view.getCarrierId(), true, true).observeOn(uiScheduler).subscribe(cycleRuleResponseList -> {
                view.onSuccess(cycleRuleResponseList);
            }, Throwable::printStackTrace));
        } else {
            view.connectivityFailed();
        }

        addAttachedSubscription(view.onSaveClicked().subscribe(ignore -> {

            if (view.validateDeviceType()) {

                if (networkStatus.isConnected()) {
                    if (view.selectedDevice() != null) {
                    repository.postDriverDevice(view.getCarrierId(), view.selectedDevice()).observeOn(uiScheduler).subscribeOn(uiScheduler).subscribe(carrierDetails -> {
                        view.onPostDevice(carrierDetails);

                    }, throwable -> {
                        throwable.printStackTrace();
                        view.onFailure();
                    });
                }else {
                        view.onFailure();
                    }

                } else {
                    view.connectivityFailed();
                }
            }

        }));
    }

    interface View {
        public void init();

        public int getCarrierId();

        public void onSuccess(List<DeviceTypeModel> deviceTypeModel);

        public void onFailure();

        public void onPostDevice(DeviceTypeModel device);

        public DeviceTypeInputPayLoad selectedDevice();

        Observable<Void> onSaveClicked();

        public boolean validateDeviceType();

        public void connectivityFailed();

    }
}
