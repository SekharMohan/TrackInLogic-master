    package trackinlogic.trans.pss.com.trackinlogic.service;

    import android.util.Log;

    import java.io.IOException;
    import java.util.List;
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
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleData;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.CycleRuleResponse;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterData;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.OdoMeterResponse;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneData;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.TimeZoneResonse;
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
            return api.getCarrierDetails(dotId,details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<TimeZoneResonse>> getTimeZones(boolean details, boolean inactive) {
            return api.getTimeZones(details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<TimeZoneResonse> getTimeZone(int timeZoneId, boolean details, boolean inactive) {
            return api.getTimeZone(timeZoneId,details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<TimeZoneResonse>> postTimeZones(List<TimeZoneData> timeZoneData) {
            return postTimeZones(timeZoneData).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<TimeZoneResonse> postTimeZone(TimeZoneData timeZoneData) {
            return postTimeZone(timeZoneData).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<OdoMeterResponse>> getOdometers(boolean details, boolean inactive) {
            return api.getOdometers(details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<OdoMeterResponse> getOdometer(int odometerId, boolean details, boolean inactive) {
            return api.getOdometer(odometerId,details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<OdoMeterResponse>> postOdometers(List<OdoMeterData> odoMeterDataList) {
            return api.postOdometers(odoMeterDataList).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<OdoMeterResponse> postOdometer(OdoMeterData odoMeterData) {
            return api.postOdometer(odoMeterData).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<CycleRuleResponse>> getCargotypes(boolean details, boolean inactive) {
            return api.getCargotypes(details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<CycleRuleResponse> getCargotype(int cargoTypeId, boolean details, boolean inactive) {
            return api.getCargotype(cargoTypeId,details,inactive).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<List<CycleRuleResponse>> postCargotypes(List<CycleRuleData> cycleRuleDataList) {
            return api.postCargotypes(cycleRuleDataList).subscribeOn(ioScheduler);
        }

        @Override
        public Observable<CycleRuleResponse> postCargotype(CycleRuleData cycleRuleData) {
            return null;
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

            @GET("timezones")
            Observable<List<TimeZoneResonse>> getTimeZones(@Query("details") boolean details, @Query("inactive") boolean inactive);
            @GET("timezone/{timeZoneID}")
            Observable<TimeZoneResonse> getTimeZone(@Path("timeZoneID") int timeZoneId, @Query("details") boolean details, @Query("inactive") boolean inactive);
            @POST("timezones")
            Observable<List<TimeZoneResonse>> postTimeZones(@Body List<TimeZoneData> timeZoneData);
            @POST("timezone")
            Observable<TimeZoneResonse> postTimeZone(@Body TimeZoneData timeZoneData);

            @GET("odometers")
            Observable<List<OdoMeterResponse>> getOdometers(@Query("details") boolean details, @Query("inactive") boolean inactive);
            @GET("odometer/{odometerID}")
            Observable<OdoMeterResponse> getOdometer(@Path("odometerID") int odometerId, @Query("details") boolean details, @Query("inactive") boolean inactive);
            @POST("odometers")
            Observable<List<OdoMeterResponse>> postOdometers(@Body List<OdoMeterData> odoMeterDataList);
            @POST("odometer")
            Observable<OdoMeterResponse> postOdometer(@Body OdoMeterData odoMeterData);

            @GET("cargotypes")
            Observable<List<CycleRuleResponse>> getCargotypes(@Query("details") boolean details, @Query("inactive") boolean inactive);
            @GET("cargotype/{cargotypeId}")
            Observable<CycleRuleResponse> getCargotype(@Path("cargotypeId") int cargoTypeId, @Query("details") boolean details, @Query("inactive") boolean inactive);
            @POST("cargotypes")
            Observable<List<CycleRuleResponse>> postCargotypes(@Body List<CycleRuleData> cycleRuleDataList);
            @POST("cargotype")
            Observable<CycleRuleResponse> postCargotype(@Body CycleRuleData cycleRuleData);

        }
    }
