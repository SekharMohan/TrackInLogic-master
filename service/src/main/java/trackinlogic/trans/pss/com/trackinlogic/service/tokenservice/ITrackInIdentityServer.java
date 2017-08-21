package trackinlogic.trans.pss.com.trackinlogic.service.tokenservice;

import okhttp3.RequestBody;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenOutputPayload;

/**
 * Created by Sekhar Madhiyazhagan on 8/20/2017.
 */

public interface ITrackInIdentityServer {
   Observable<TokenOutputPayload> getToken(RequestBody grantType, RequestBody responseType,  RequestBody scope,  RequestBody clientSecret,  RequestBody clientId,  RequestBody userName,  RequestBody password);
}
