package trackinlogic.trans.pss.com.trackinlogic.service.tokenservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenOutputPayload;

/**
 * Created by Sekhar Madhiyazhagan on 8/20/2017.
 */

public class IdentityServer implements ITrackInIdentityServer {

    private final TokenServer api;
    private final Scheduler ioScheduler;

    public IdentityServer(final String baseUrl, final Scheduler ioScheduler) {
        this.ioScheduler = ioScheduler;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
               .build();



        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(TokenServer.class);

    }

    @Override
    public Observable<TokenOutputPayload> getToken(RequestBody grantType, RequestBody responseType, RequestBody scope, RequestBody clientSecret, RequestBody clientId, RequestBody userName, RequestBody password) {
        return api.getToken(grantType,responseType,scope,clientSecret,clientId,userName,password).subscribeOn(ioScheduler);
    }

    interface  TokenServer {
        @Multipart
        @POST("connect/token")
        Observable<TokenOutputPayload> getToken(@Part("grant_type") RequestBody grantType, @Part("response_type") RequestBody responseType,@Part("scope") RequestBody scope,@Part("client_secret") RequestBody clientSecret,@Part("client_id") RequestBody clientId,@Part("userName") RequestBody userName,@Part("password") RequestBody password);

    }
}
