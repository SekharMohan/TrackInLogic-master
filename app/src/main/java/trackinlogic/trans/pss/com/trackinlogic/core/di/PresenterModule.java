package trackinlogic.trans.pss.com.trackinlogic.core.di;

import com.memoizrlabs.ShankModule;

import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCirclePresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCircleRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype.SelectDeviceTypePresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype.SelectDeviceTypeRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.DotSearchPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.DotSearchRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress.ManualCarrierDetailsPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress.ManualCarrierDetailsRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal.HomeTerminalPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal.HomeTerminalRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.login.LoginPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.login.LoginRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.signup.RegistrationPresenter;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.signup.RegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

import static com.memoizrlabs.Shank.named;
import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class PresenterModule implements ShankModule {

    @Override
    public void registerFactories() {
        registerFactory(RegistrationPresenter.class, () -> new RegistrationPresenter(
                provideSingleton(RegistrationRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));


        registerFactory(CarrierRegistrationPresenter.class, () -> new CarrierRegistrationPresenter(
                provideSingleton(CarrierRegistrationRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));

        registerFactory(TimeAndCirclePresenter.class, () -> new TimeAndCirclePresenter(
                provideSingleton(TimeAndCircleRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));

        registerFactory(SelectDeviceTypePresenter.class, () -> new SelectDeviceTypePresenter(
                provideSingleton(SelectDeviceTypeRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));
        registerFactory(ManualCarrierDetailsPresenter.class, () -> new ManualCarrierDetailsPresenter(
                provideSingleton(ManualCarrierDetailsRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));
        registerFactory(DotSearchPresenter.class, () -> new DotSearchPresenter(
                provideSingleton(DotSearchRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));
        registerFactory(HomeTerminalPresenter.class, () -> new HomeTerminalPresenter(
                provideSingleton(HomeTerminalRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));
        registerFactory(LoginPresenter.class, () -> new LoginPresenter(
                provideSingleton(LoginRepository.class),
                named(ApplicationModule.UI_SCHEDULER).provideSingleton(Scheduler.class),provideSingleton(NetworkUtil.class)));

    }
}
