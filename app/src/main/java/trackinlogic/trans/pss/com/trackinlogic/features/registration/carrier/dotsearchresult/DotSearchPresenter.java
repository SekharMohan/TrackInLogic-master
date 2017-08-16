package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.homeTerminals.HomeTerminals;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class DotSearchPresenter extends BasePresenter<DotSearchPresenter.View> {

    private DotSearchRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public DotSearchPresenter(DotSearchRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }

    @Override
    protected void onViewAttached(View view) {
        view.init();
        if(networkStatus.isConnected()) {
            addAttachedSubscription(repository.onGettingHomeTerminals(view.getCarrierId(), true, true).observeOn(uiScheduler).subscribe(homeTerminalses -> {
                view.showHomeTerminals(homeTerminalses);},
                        throwable ->{
                                view.onFailed();}));
        }else {
            view.networkConnectivity();
        }
        addAttachedSubscription(view.onNextButtonClicked().subscribe(aVoid -> {
            if(view.validation()) {
                view.gotoDeviceTypeActivity();
            }
        }));

    }

    interface View {
        public void init();
        Observable<Void> onNextButtonClicked();
        public boolean validation();
        public int getCarrierId();
        public void showHomeTerminals(List<HomeTerminals> homeTerminals);
        public void onFailed();
        public void gotoDeviceTypeActivity();
        public void networkConnectivity();

    }
}
