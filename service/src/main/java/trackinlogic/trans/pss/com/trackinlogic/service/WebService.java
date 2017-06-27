    package trackinlogic.trans.pss.com.trackinlogic.service;

    import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
    import retrofit2.http.GET;
    import retrofit2.http.POST;
    import retrofit2.http.Path;
    import retrofit2.http.Query;
    import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.BuildConfig;
import trackinlogic.trans.pss.com.trackinlogic.interfaces.TrackInServices;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;


    /**
     * Created by Sekhar Madhiyazhagan on 5/27/2017.
     */

    public class WebService implements TrackInServices {

        private final Api api;
        private final Scheduler ioScheduler;

        public WebService(final String baseUrl, final Scheduler ioScheduler) {
            this.ioScheduler = ioScheduler;

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor( provideHttpLoggingInterceptor() )
           .connectTimeout(1, TimeUnit.MINUTES)
             .writeTimeout(1, TimeUnit.MINUTES)
          .readTimeout(1, TimeUnit.MINUTES)
          .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Basic dHJhY2tpbmxvZ2ljOnRyYWNraW5sb2dpYw==").header("Content-Type","application/json"); // <-- this is the important line

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).build();



            api = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()
                    .create(Api.class);

        }

        @Override
        public Observable<ResponseBody> postRegistration(Registration registration) {

            return api.postRegistration(registration).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<CarrierDetails> getCarrierDetails(String dotId, boolean details, boolean inactive) {
            return api.getCarrierDetails(dotId,details,inactive);
        }

        private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
            HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.d("",message);
                        }
                    });
            httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ?HttpLoggingInterceptor.Level.BODY :
                    HttpLoggingInterceptor.Level.NONE);
            return httpLoggingInterceptor;
        }

        interface Api {

            @POST("user")Observable<ResponseBody> postRegistration(@Body Registration registration);
            @GET("carrier/dots/{dotId}")
            Observable<CarrierDetails> getCarrierDetails(@Path("dotId") String dotId,@Query("details") boolean details,@Query("inactive") boolean inactive);
        }
    }
