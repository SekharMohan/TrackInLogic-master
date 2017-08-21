package trackinlogic.trans.pss.com.trackinlogic.core.di;

import com.memoizrlabs.ShankModule;

import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCircleRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype.SelectDeviceTypeRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.DotSearchRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress.ManualCarrierDetailsRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal.HomeTerminalRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.login.LoginRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.signup.RegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.service.db.CarrierAddressDBHelper;
import trackinlogic.trans.pss.com.trackinlogic.service.db.HomeTerminalDBHelper;
import trackinlogic.trans.pss.com.trackinlogic.service.tokenservice.ITrackInIdentityServer;

import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class RepositoryModule implements ShankModule {


    @Override
    public void registerFactories() {
        registerFactory(RegistrationRepository.class,()-> new RegistrationRepository(provideSingleton(TrackInServices.class)));
        registerFactory(CarrierRegistrationRepository.class,()-> new CarrierRegistrationRepository(provideSingleton(TrackInServices.class)));
        registerFactory(TimeAndCircleRepository.class,()-> new TimeAndCircleRepository(provideSingleton(TrackInServices.class)));
        registerFactory(SelectDeviceTypeRepository.class,()-> new SelectDeviceTypeRepository(provideSingleton(TrackInServices.class)));
        registerFactory(ManualCarrierDetailsRepository.class,()-> new ManualCarrierDetailsRepository(provideSingleton(CarrierAddressDBHelper.class)));
        registerFactory(DotSearchRepository.class,()-> new DotSearchRepository(provideSingleton(TrackInServices.class)));
        registerFactory(HomeTerminalRepository.class,()-> new HomeTerminalRepository(provideSingleton(HomeTerminalDBHelper.class)));
        registerFactory(LoginRepository.class,()-> new LoginRepository(provideSingleton(ITrackInIdentityServer.class)));


    }
}
