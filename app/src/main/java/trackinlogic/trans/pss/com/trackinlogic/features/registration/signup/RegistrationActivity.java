    package trackinlogic.trans.pss.com.trackinlogic.features.registration.signup;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.design.widget.TextInputLayout;
    import android.text.TextUtils;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.Spinner;

    import com.jakewharton.rxbinding.view.RxView;
    import com.memoizrlabs.Shank;

    import java.util.Arrays;

    import butterknife.BindArray;
    import butterknife.BindString;
    import butterknife.BindView;
    import butterknife.ButterKnife;
    import butterknife.OnClick;
    import butterknife.OnFocusChange;
    import butterknife.OnTextChanged;
    import okhttp3.ResponseBody;
    import rx.Observable;
    import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
    import trackinlogic.trans.pss.com.trackinlogic.R;
    import trackinlogic.trans.pss.com.trackinlogic.features.common.customview.Loader;
    import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search.CarrierRegistrationActivity;
    import trackinlogic.trans.pss.com.trackinlogic.model.registration.Registration;
    import trackinlogic.trans.pss.com.trackinlogic.util.ToastUtil;
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
        TextInputLayout phNumberLayout;
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
        @BindView(R.id.spCountrypicker)
        Spinner spCountrypicker;
        @BindView(R.id.editText)
        EditText edtDialCode;

        @BindArray(R.array.country)
        String[] arrCountry;
        private boolean userIsInteracting;

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
            ButterKnife.bind(this);
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
            spinnerSetup(spCountrypicker, Arrays.asList(arrCountry),getString(R.string.country_selection));
    initializeListener();
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
            Loader.dismissProgressBar();
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
            finish();

        }


        @Override
        public void registrationStatus(ResponseBody responseBody) {
            gotoCarrierRegistrationActivity();

        }

        private boolean validateFields() {

            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();
            if (edtFirstName.getText().toString().trim().length() <= 0) {
                setError(fNameLayout, edtFirstName, getString(R.string.first_name_empty));
                return false;
            } else if (edtLastName.getText().toString().trim().length() <= 0) {
                setError(lNameLayout, edtLastName, getString(R.string.last_name_empty));
                return false;
            } else if (spCountrypicker.getSelectedItem().toString().trim().length() <= 0 || spCountrypicker.getSelectedItem().toString().equalsIgnoreCase("select country")) {
              ToastUtil.showErrorUpdate(this, getString(R.string.select_country));
                return false;
            }else if (edtPhoneNumber.getText().toString().trim().length() <= 0) {
                setError(phNumberLayout, edtPhoneNumber, getString(R.string.phoneNo_empty));
                return false;
            } else if (edtEmail.getText().toString().trim().length() <= 0) {
                setError(emailLayout, edtEmail, getString(R.string.email_empty));
                return false;
            } else if (edtUserName.getText().toString().trim().length() <= 0) {
                setError(userNameLayout, edtUserName, getString(R.string.user_name_empty));
                return false;
            } else if (password.length() <= 0) {
                setError(pswLayout, edtPassword, getString(R.string.psw_empty));
                return false;
            } else if (confirmPassword.length() <= 0) {
                setError(confirmPswLayout, edtConfirmPassword, getString(R.string.confirm_psw_empty));
                return false;
            } else if (!ValidationUtils.isvalidPhoneNumber(edtPhoneNumber.getText().toString())) {
                setError(pswLayout, edtPassword, getString(R.string.phoneNo_invalid));
                return false;
            } else if (!ValidationUtils.isValidEmail(edtEmail.getText().toString())) {
                setError(emailLayout, edtEmail, getString(R.string.email_invalid));
                return false;
            } else if (password.length() < 6) {
                setError(pswLayout, edtPassword, getString(R.string.psw_min_limit));
                return false;

            } else if (!ValidationUtils.isValidPassword(password)) {
                setError(pswLayout, edtPassword, getString(R.string.password_invalid));
                return false;
            } else if (!password.equals(confirmPassword)) {
                setError(confirmPswLayout, edtConfirmPassword, getString(R.string.password_missmatch));
                return false;
            }
            return true;
        }

        private void setEditFieldSelection(EditText editText) {
            if (editText.getText().toString().length() != 0) {
                editText.setSelection(editText.getText().toString().length());
            }
        }

        private void setError(TextInputLayout textInputLayout, EditText editText, String errorMsg) {
            setEditFieldSelection(editText);
            editText.requestFocus();
            textInputLayout.setError(errorMsg);
            textInputLayout.setErrorEnabled(true);

        }

        @OnTextChanged({R.id.edtFirstName, R.id.edtLastName, R.id.edtPhoneNumber, R.id.edtEmail, R.id.edtUserName, R.id.edtPassword, R.id.edtConfirmPassword})
        void handleFirstNameEdittextError() {
            resetTextInputErrors(fNameLayout, lNameLayout, phNumberLayout, emailLayout, userNameLayout, pswLayout, confirmPswLayout);
        }

        @OnFocusChange({R.id.edtFirstName, R.id.edtLastName, R.id.edtPhoneNumber, R.id.edtEmail, R.id.edtUserName, R.id.edtPassword, R.id.edtConfirmPassword})
        public void EditTextFocustChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                resetTextInputError(getTextInputField(v.getId()));
            }

        }

        private TextInputLayout getTextInputField(int id) {
            switch (id) {
                case R.id.edtFirstName:
                    return fNameLayout;
                case R.id.edtLastName:
                    return lNameLayout;
                case R.id.edtPhoneNumber:
                    return phNumberLayout;
                case R.id.edtEmail:
                    return emailLayout;
                case R.id.edtUserName:
                    return userNameLayout;
                case R.id.edtPassword:
                    return pswLayout;
                case R.id.edtConfirmPassword:
                    return confirmPswLayout;
            }
            return null;
        }

        private void resetTextInputErrors(TextInputLayout... textInputLayouts) {
            for (TextInputLayout textInputLayout : textInputLayouts) {
                resetTextInputError(textInputLayout);
            }
        }

        private void resetTextInputError(TextInputLayout textInputField) {
            if (textInputField != null && !TextUtils.isEmpty(textInputField.getError())) {
                textInputField.setError(null);
                textInputField.setErrorEnabled(false);
            }

        }

        @Override
        public void onUserInteraction() {
            super.onUserInteraction();
            userIsInteracting = true;
        }
        private void initializeListener() {
            spCountrypicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(userIsInteracting){
                        String selectedCountry = spCountrypicker.getItemAtPosition(spCountrypicker.getSelectedItemPosition()).toString();
                        switch (spCountrypicker.getSelectedItemPosition()) {
                            case 0:
                                edtDialCode.setText("+1");
                                break;
                            case 1:
                                edtDialCode.setText("+1");
                                break;
                            case 2:
                                edtDialCode.setText("+52");
                                break;
                        }

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

    }
