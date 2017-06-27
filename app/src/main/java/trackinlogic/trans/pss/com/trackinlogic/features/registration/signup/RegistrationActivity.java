package trackinlogic.trans.pss.com.trackinlogic.features.registration.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;

import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationActivity;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
import trackinlogic.trans.pss.com.trackinlogic.util.ValidationUtils;

public class RegistrationActivity extends BaseActivity implements RegistrationPresenter.View {
    @BindString(R.string.sign_up)
    String navTittle;

    @BindView(R.id.fname)
    TextInputLayout fNameLayout;
    @BindView(R.id.lname)
    TextInputLayout lNameLayout;
    @BindView(R.id.email)
    TextInputLayout emailLayout;
    @BindView(R.id.phNumber)
    TextInputLayout  phNumberLayout;
    @BindView(R.id.psw)
    TextInputLayout pswLayout;
    @BindView(R.id.confirmPsw)
    TextInputLayout confirmPswLayout;
    @BindView(R.id.userName)
    TextInputLayout userNameLayout;
    @BindView(R.id.edtFirstName)
    EditText edtFirstName;
    @BindView(R.id.edtLastName)
    EditText edtLastName;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPassword)
    EditText edtConfirmPassword;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnDone;

    private RegistrationPresenter presenter;

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        presenter = Shank.provideNew(RegistrationPresenter.class);
        presenter.onViewAttached(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @OnClick(R.id.actionbar_right_icon)
    public void onSelectButtonClicked() {
        startActivity(CarrierRegistrationActivity.getStartIntent(this));

    }


    @Override
    public void initializeRegistration() {
//        Loader.showProgressBar(this);


    }

    @Override
    public Observable<Void> onRightButtonClicked() {
        return RxView.clicks(btnDone);
    }

    @Override
    public boolean isValidUserData() {
        return validateFields();
    }

    @Override
    public Registration getRegistrationData() {
        showLodaing();
        Registration registration = new Registration();
        registration.setFirstName(edtFirstName.getText().toString());
        registration.setLastName(edtLastName.getText().toString());
        registration.setPhoneNumber(edtPhoneNumber.getText().toString());
        registration.setEmail(edtEmail.getText().toString());
        registration.setUserName(edtUserName.getText().toString());
        registration.setPassword(edtPassword.getText().toString());


        return registration;
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void updateInternetConnectivityStatus() {
        dismissLoading();
        updateNetworkConnectivity();
    }

    @Override
    public void gotoCarrierRegistrationActivity() {
        dismissLoading();
        startActivity(CarrierRegistrationActivity.getStartIntent(this));

    }


    @Override
    public void registrationStatus(ResponseBody responseBody) {
        gotoCarrierRegistrationActivity();

    }

    private boolean validateFields() {

        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();
        if (edtFirstName.getText().toString().trim().length() <= 0) {
            setError(fNameLayout,edtFirstName, getString(R.string.first_name_empty));
            return false;
        } else if (edtLastName.getText().toString().trim().length() <= 0) {
            setError(lNameLayout,edtLastName, getString(R.string.last_name_empty));
            return false;
        } else if (edtPhoneNumber.getText().toString().trim().length() <= 0) {
            setError(phNumberLayout,edtPhoneNumber, getString(R.string.phoneNo_empty));
            return false;
        } else if (edtEmail.getText().toString().trim().length() <= 0) {
            setError(emailLayout,edtEmail, getString(R.string.email_empty));
            return false;
        } else if (edtUserName.getText().toString().trim().length() <= 0) {
            setError(userNameLayout,edtUserName, getString(R.string.user_name_empty));
            return false;
        } else if (password.length() <= 0) {
            setError(pswLayout,edtPassword, getString(R.string.psw_empty));
            return false;
        } else if (confirmPassword.length() <= 0) {
            setError(confirmPswLayout,edtConfirmPassword, getString(R.string.confirm_psw_empty));
            return false;
        } else if (!ValidationUtils.isvalidPhoneNumber(edtPhoneNumber.getText().toString())) {
            setError(pswLayout,edtPassword, getString(R.string.password_invalid));
            return false;
        } else if (!ValidationUtils.isValidEmail(edtEmail.getText().toString())) {
            setError(emailLayout,edtEmail, getString(R.string.email_invalid));
            return false;
        } else if (password.length() < 8) {
            setError(pswLayout,edtPassword, getString(R.string.psw_min_limit));
            return false;

        } else if (!ValidationUtils.isValidPassword(password)) {
            setError(pswLayout,edtPassword, getString(R.string.password_invalid));
            return false;
        } else if (!password.equals(confirmPassword)) {
            setError(confirmPswLayout,edtConfirmPassword, getString(R.string.password_missmatch));
            return false;
        }
        return true;
    }

    private void setEditFieldSelection(EditText editText) {
        if (editText.getText().toString().length() != 0) {
            editText.setSelection(editText.getText().toString().length());
        }
    }

    private void setError(TextInputLayout textInputLayout,EditText editText, String errorMsg) {
        setEditFieldSelection(editText);
        editText.requestFocus();
        textInputLayout.setError(errorMsg);

    }


}