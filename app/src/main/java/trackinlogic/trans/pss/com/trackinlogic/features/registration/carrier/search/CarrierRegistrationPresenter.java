package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search;

import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierQueryString;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class CarrierRegistrationPresenter extends BasePresenter<CarrierRegistrationPresenter.View> {

    private CarrierRegistrationRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public CarrierRegistrationPresenter(CarrierRegistrationRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }
    @Override
    protected void onViewAttached(View view) {
        addAttachedSubscription(view.onSearchBtnclicked().subscribe(ignore -> {

            if(view.isValidateDotId()) {

                if(networkStatus.isConnected()) {
                    CarrierQueryString queryString = view.getRequestParams();
                    repository.onGettingCarrierAddress(queryString.getDotId(),queryString.isDetails(),queryString.isInactive()).subscribeOn(uiScheduler).subscribe(carrierDetails -> {view.onGettingAddressSucess(carrierDetails);},throwable ->{
                        throwable.printStackTrace();
                        view.onGettingAdressFailure();});

                } else  {
                    view.updateNetworkStatus();
                }
            }

        }));
        addAttachedSubscription(view.onManualBtnClicked().subscribe(aVoid -> view.gotoManualCarrierRegistrationActivity()));

    }

    interface View {
        public void initializeCarrierRegisterActivity();
        Observable<Void> onManualBtnClicked();
        Observable<Void> onSearchBtnclicked();
        public void onGettingAddressSucess(CarrierDetails carrierDetails);
        public void onGettingAdressFailure();
        public boolean isValidateDotId();
        public void gotoManualCarrierRegistrationActivity();
        public void updateNetworkStatus();
        public CarrierQueryString getRequestParams();
    }
}
