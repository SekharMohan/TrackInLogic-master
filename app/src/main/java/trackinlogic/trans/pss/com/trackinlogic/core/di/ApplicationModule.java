package trackinlogic.trans.pss.com.trackinlogic.core.di;

import android.content.Context;

import com.google.gson.Gson;
import com.memoizrlabs.ShankModule;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import trackinlogic.trans.pss.com.trackinlogic.util.LocalStorage;

import static com.memoizrlabs.Shank.registerFactory;
import static com.memoizrlabs.Shank.registerNamedFactory;
public class ApplicationModule implements ShankModule {

    private final Context context;

    public static final String IO_SCHEDULER = "io";
    public static final String UI_SCHEDULER = "ui";

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Override
    public void registerFactories() {
        registerFactory(Context.class, () -> this.context);
        registerNamedFactory(Scheduler.class, ApplicationModule.IO_SCHEDULER, Schedulers::io);
        registerNamedFactory(Scheduler.class, ApplicationModule.UI_SCHEDULER, AndroidSchedulers::mainThread);
        registerFactory(Gson.class, Gson::new);
        registerFactory(LocalStorage.class, (String file) -> new LocalStorage(this.context, file));
    }
}

