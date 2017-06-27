package trackinlogic.trans.pss.com.trackinlogic;

import android.app.Application;

import com.memoizrlabs.ShankModuleInitializer;

import trackinlogic.trans.pss.com.trackinlogic.core.di.ApplicationModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.PresenterModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.RepositoryModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.ServiceModule;

/**
 * Created by sekhar on 28/02/17.
 */

public class TrackInLogic extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ShankModuleInitializer.initializeModules(new ApplicationModule(this.getApplicationContext()),
                new ServiceModule(),
                new RepositoryModule(),
                new PresenterModule());
    }
}
