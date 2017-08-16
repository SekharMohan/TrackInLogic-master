package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress;

        import rx.Observable;
    import rx.Scheduler;
    import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
    import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

        /**
         * Created by Sekhar Madhiyazhagan on 6/17/2017.
         */

        public class ManualCarrierDetailsPresenter extends BasePresenter<ManualCarrierDetailsPresenter.View> {

            private ManualCarrierDetailsRepository repository;
            private Scheduler uiScheduler;
            private NetworkUtil networkStatus;

            public ManualCarrierDetailsPresenter(ManualCarrierDetailsRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
                this.repository = repository;
                this.uiScheduler = uiScheduler;
                this.networkStatus = networkStatus;

            }
            @Override
            protected void onViewAttached(ManualCarrierDetailsPresenter.View view) {
                addAttachedSubscription(view.onUserDataSaved().subscribe(ignore -> {
                    if(view.validateInfomation()) {
    repository.insertData().observeOn(uiScheduler).subscribe(isValid -> {
        if(isValid) {
            view.gotoHomeTerminalActivity();
        }
    });
                    }
                }));

            }
            public interface View {

                    Observable<Void> onUserDataSaved();
        boolean validateInfomation();
                void gotoHomeTerminalActivity();
                }

        }



