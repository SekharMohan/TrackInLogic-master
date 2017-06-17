package trackinlogic.trans.pss.com.trackinlogic.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;

/**
 * Created by Sekhar Madhiyazhagan on 5/27/2017.
 */

public class WebService implements TrackInServices {

    private final Api api;
    private final Scheduler ioScheduler;

    public WebService(final String baseUrl, final Scheduler ioScheduler) {
        this.ioScheduler = ioScheduler;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient .writeTimeout(1, TimeUnit.MINUTES);
        httpClient .readTimeout(1, TimeUnit.MINUTES);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Basic dHJhY2tpbmxvZ2ljOnRyYWNraW5sb2dpYw==").header("Content-Type","application/json"); // <-- this is the important line

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();





        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
                .create(Api.class);

    }

    interface Api {

    }
}
