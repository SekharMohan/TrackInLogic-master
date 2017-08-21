package trackinlogic.trans.pss.com.trackinlogic.features.registration.login;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.HttpException;
import rx.Observable;
import rx.Scheduler;
import trackinlogic.trans.pss.com.trackinlogic.core.interfaces.BasePresenter;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenOutputPayload;
import trackinlogic.trans.pss.com.trackinlogic.util.NetworkUtil;

/**
 * Created by Sekhar Madhiyazhagan on 6/17/2017.
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.View>{

    private LoginRepository repository;
    private Scheduler uiScheduler;
    private NetworkUtil networkStatus;

    public LoginPresenter(LoginRepository repository, Scheduler uiScheduler, NetworkUtil networkStatus) {
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.networkStatus = networkStatus;

    }

    @Override
    protected void onViewAttached(View view) {
        addAttachedSubscription(view.onLoginBtnClicked().subscribe(aVoid -> {
            if(networkStatus.isConnected()) {

                if(view.validateFields()) {
                    TokenInputPayLoad inputPayLoad = view.getTokenInput();
                    repository.onTokenizer(getRequestBodyFielsd(inputPayLoad.grantType),getRequestBodyFielsd(inputPayLoad.responseType),getRequestBodyFielsd(inputPayLoad.scope),getRequestBodyFielsd(inputPayLoad.clientSecret),getRequestBodyFielsd(inputPayLoad.clientId),getRequestBodyFielsd(inputPayLoad.userName),getRequestBodyFielsd(inputPayLoad.password)).observeOn(uiScheduler).subscribe(tokenOutputPayload -> {
                        view.onSuccess(tokenOutputPayload);
                    },throwable -> {
try {
    if (throwable instanceof HttpException) {
        String errorBody = ((HttpException) throwable).response().errorBody().toString();
        JSONObject jsonObject = new JSONObject(errorBody);
        view.onFailed(jsonObject.getString("error"));
    }
}catch (Exception exception) {
    view.onFailed("invalid_client");
    exception.printStackTrace();
}

                    });
                }
            } else {
                view.onConnectivityFailed();
            }
        }));

    }

    RequestBody getRequestBodyFielsd(String value) {
      return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    interface View {

        Observable<Void> onLoginBtnClicked();
        boolean validateFields();
        void onSuccess(TokenOutputPayload tokenOutputPayload);
        void onFailed(String msg);
        void gotoLogSheetActivity();
        void onConnectivityFailed();
        TokenInputPayLoad getTokenInput();

    }
}
