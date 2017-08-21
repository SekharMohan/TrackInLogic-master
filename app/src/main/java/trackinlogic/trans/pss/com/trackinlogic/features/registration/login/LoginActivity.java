package trackinlogic.trans.pss.com.trackinlogic.features.registration.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.logbook.LogBookActivity;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.token.TokenOutputPayload;
import trackinlogic.trans.pss.com.trackinlogic.util.JWTUtils;

public class LoginActivity extends BaseActivity implements LoginPresenter.View {
    @BindView(R.id.actionbar_right_icon)
    ImageView btnDone;
    @BindString(R.string.login)
    String navTittle;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayoutName;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputLayoutPsw;
    LoginPresenter presenter ;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = Shank.provideNew(LoginPresenter.class);
        presenter.onViewAttached(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public Observable<Void> onLoginBtnClicked() {
        return RxView.clicks(btnDone);
    }

    @Override
    public boolean validateFields() {

          if (edtName.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutName, edtName, getString(R.string.user_name_empty));
            return false;
        } else if (edtPassword.length() <= 0) {
            setError(textInputLayoutPsw, edtPassword, getString(R.string.psw_empty));
            return false;
        } else if (edtPassword.length() < 6) {
              setError(textInputLayoutPsw, edtPassword, getString(R.string.psw_min_limit));
              return false;

          }
        showLodaing();
        return true;
    }

    @OnTextChanged({R.id.edtName, R.id.edtPassword})
    void handleFirstNameEdittextError() {
        resetTextInputErrors(textInputLayoutName,textInputLayoutPsw);
    }

    @OnFocusChange({R.id.edtName, R.id.edtPassword})
    public void EditTextFocustChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            resetTextInputError(getTextInputField(v.getId()));
        }

    }
    @Override
    public void onSuccess(TokenOutputPayload tokenOutputPayload) {
        dismissLoading();
        try {
            JWTUtils.decoded(tokenOutputPayload.getAccess_token());
        } catch (Exception e) {
            e.printStackTrace();
        }
        gotoLogSheetActivity();
    }

    @Override
    public void onFailed(String msg) {
        dismissLoading();
        showUserMessage(msg);
    }

    private TextInputLayout getTextInputField(int id) {
        switch (id) {
            case R.id.edtName:
                return textInputLayoutName;
            case R.id.edtPassword:
                return textInputLayoutPsw;
        }
        return null;
    }
    @Override
    public void gotoLogSheetActivity() {
        startActivity(new Intent(this, LogBookActivity.class));
    }

    @Override
    public void onConnectivityFailed() {
        updateNetworkConnectivity();
    }

    @Override
    public TokenInputPayLoad getTokenInput() {
        return new TokenInputPayLoad(edtName.getText().toString(), edtPassword.getText().toString());
    }
}
