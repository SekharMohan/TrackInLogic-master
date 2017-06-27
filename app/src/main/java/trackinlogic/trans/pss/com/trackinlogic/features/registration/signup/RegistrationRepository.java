package trackinlogic.trans.pss.com.trackinlogic.features.registration.signup;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class RegistrationRepository {
    TrackInServices trackInServices;
    private final BehaviorSubject<ResponseBody> publishSubject = BehaviorSubject.create();

    public  RegistrationRepository(TrackInServices trackInServices){
        this.trackInServices = trackInServices;

    }

    public Observable<ResponseBody> onPostRegistration(Registration registration) {
        return  trackInServices.postRegistration(registration)
                .doOnNext(registrationResponse ->{publishSubject.onNext(registrationResponse);});
    }


}
