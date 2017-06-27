package trackinlogic.trans.pss.com.trackinlogic.core.di;

import com.memoizrlabs.ShankModule;

import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.signup.RegistrationRepository;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;

import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class RepositoryModule implements ShankModule {


    @Override
    public void registerFactories() {
        registerFactory(RegistrationRepository.class,()-> new RegistrationRepository(provideSingleton(TrackInServices.class)));
        registerFactory(CarrierRegistrationRepository.class,()-> new CarrierRegistrationRepository(provideSingleton(TrackInServices.class)));

    }
}
