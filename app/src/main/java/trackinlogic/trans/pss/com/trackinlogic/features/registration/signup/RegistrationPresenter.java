package trackinlogic.trans.pss.com.trackinlogic.features.registration.signup;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Scheduler;
import timber.log.Timber;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class RegistrationPresenter extends BasePresenter<RegistrationPresenter.View> {

    private RegistrationRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public RegistrationPresenter(RegistrationRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }

    @Override
    protected void onViewAttached(View view) {
        view.initializeRegistration();
        addAttachedSubscription(view.onRightButtonClicked().subscribe(ignore -> {
            if (view.isValidUserData()) {
                if (networkStatus.isConnected()) {
                    this.repository.onPostRegistration(view.getRegistrationData())
                            .observeOn(this.uiScheduler)
.doOnError(error ->{
    System.out.println(error);
})
                            .subscribe(responseBody -> {
                                view.registrationStatus(responseBody);

                            }
                            ,throwable -> {
                                        Timber.e(throwable, "Failed to get car location");});
                    view.gotoCarrierRegistrationActivity();
                }else {

                    view.updateInternetConnectivityStatus();
                }
            }
        }));


    }

    interface View {
        public void initializeRegistration();

        Observable<Void> onRightButtonClicked();

        public boolean isValidUserData();

        public Registration getRegistrationData();

        public void dismissLoading();

        public void updateInternetConnectivityStatus();
        public  void gotoCarrierRegistrationActivity();


        public void registrationStatus(ResponseBody responseBody);

    }
}
