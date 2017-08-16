package trackinlogic.trans.pss.com.trackinlogic;

import android.app.Application;
import android.content.res.Resources;

import com.memoizrlabs.ShankModuleInitializer;

import trackinlogic.trans.pss.com.trackinlogic.core.di.ApplicationModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.PresenterModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.RepositoryModule;
import trackinlogic.trans.pss.com.trackinlogic.core.di.ServiceModule;

/**
 * Created by sekhar on 28/02/17.
 */

public class TrackInLogic extends Application {
    private static TrackInLogic instance = null;
    public TrackInLogic() {
        super();
        instance = this;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ShankModuleInitializer.initializeModules(new ApplicationModule(this.getApplicationContext()),
                new ServiceModule(this.getApplicationContext()),
                new RepositoryModule(),
                new PresenterModule());
    }

    public static TrackInLogic getInstance() {
        return instance;
    }
    /**
     * @return the ressources
     */
    public static Resources res() {
        return getInstance().getResources();
    }
}
