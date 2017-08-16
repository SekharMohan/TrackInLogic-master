package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal;

import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminalPresenter  extends BasePresenter<HomeTerminalPresenter.View> {

    private HomeTerminalRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public HomeTerminalPresenter(HomeTerminalRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }
    @Override
    protected void onViewAttached(HomeTerminalPresenter.View view) {
        addAttachedSubscription(view.onUserDataSaved().subscribe(ignore -> {
            if(view.validateInfomation()) {
                repository.insertData().observeOn(uiScheduler).subscribe(isValid -> {
                    if(isValid) {
                        view.gotoServiceSettings();
                    }
                });
            }
        }));

    }
    public interface View {

        Observable<Void> onUserDataSaved();
        boolean validateInfomation();
        void gotoServiceSettings();
    }

}