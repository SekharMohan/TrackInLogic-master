package trackinlogic.trans.pss.com.trackinlogic.features.registration.login;

import okhttp3.RequestBody;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenOutputPayload;
import trackinlogic.trans.pss.com.trackinlogic.service.tokenservice.ITrackInIdentityServer;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class LoginRepository {
    ITrackInIdentityServer trackInServices;
    private final BehaviorSubject<TokenOutputPayload> publishSubject = BehaviorSubject.create();

    public  LoginRepository(ITrackInIdentityServer trackInServices){
        this.trackInServices = trackInServices;

    }

    public Observable<TokenOutputPayload> onTokenizer(RequestBody grantType, RequestBody responseType, RequestBody scope, RequestBody clientSecret, RequestBody clientId, RequestBody userName, RequestBody password){
        return  trackInServices.getToken(grantType,responseType,scope,clientSecret,clientId,userName,password)
                .doOnNext(tokenResponse ->{publishSubject.onNext(tokenResponse);});
    }

}
