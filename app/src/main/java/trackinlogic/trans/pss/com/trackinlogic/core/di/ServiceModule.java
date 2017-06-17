package trackinlogic.trans.pss.com.trackinlogic.core.di;

import com.memoizrlabs.Shank;
import com.memoizrlabs.ShankModule;

import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.service.WebService;

import static com.memoizrlabs.Shank.registerFactory;

public class ServiceModule implements ShankModule {

    public static final String BASE_URL = "http://35.166.98.81/api/";

    @Override
    public void registerFactories() {
        registerFactory(TrackInServices.class, () -> new WebService(ServiceModule.BASE_URL,
                Shank.named("io").provideSingleton(Scheduler.class)));
    }
}
