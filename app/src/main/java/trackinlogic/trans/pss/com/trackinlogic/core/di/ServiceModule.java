package trackinlogic.trans.pss.com.trackinlogic.core.di;

import android.content.Context;

import com.memoizrlabs.Shank;
import com.memoizrlabs.ShankModule;

import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.service.WebService;
import trackinlogic.trans.pss.com.trackinlogic.service.db.CarrierAddressDBHelper;
import trackinlogic.trans.pss.com.trackinlogic.service.db.HomeTerminalDBHelper;
import trackinlogic.trans.pss.com.trackinlogic.service.tokenservice.ITrackInIdentityServer;
import trackinlogic.trans.pss.com.trackinlogic.service.tokenservice.IdentityServer;

import static com.memoizrlabs.Shank.registerFactory;

public class ServiceModule implements ShankModule {

    public static final String BASE_URL = "http://35.166.98.81/api/";
    public static final String TOKEN_URL = "http://ec2-35-167-47-203.us-west-2.compute.amazonaws.com/";
    public final Context context;
    public ServiceModule(Context context) {
        this.context = context;
    }

    @Override
    public void registerFactories() {
        registerFactory(TrackInServices.class, () -> new WebService(ServiceModule.BASE_URL,
                Shank.named("io").provideSingleton(Scheduler.class)));
        registerFactory(ITrackInIdentityServer.class, () -> new IdentityServer(ServiceModule.TOKEN_URL,
                Shank.named("io").provideSingleton(Scheduler.class)));
        registerFactory(CarrierAddressDBHelper.class, () -> new CarrierAddressDBHelper(context,
                Shank.named("io").provideSingleton(Scheduler.class)));
        registerFactory(HomeTerminalDBHelper.class, () -> new HomeTerminalDBHelper(context,
                Shank.named("io").provideSingleton(Scheduler.class)));
    }
}
