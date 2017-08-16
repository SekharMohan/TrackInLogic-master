package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.hometerminal;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.f2prateek.dart.HensonNavigable;
import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCircleActivity;
import trackinlogic.trans.pss.com.trackinlogic.util.FileUtils;
@HensonNavigable
public class HomeTerminalActivity extends BaseActivity implements HomeTerminalPresenter.View {

    @BindString(R.string.home_terminal)
    String navTittle;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindArray(R.array.country)
    String[] arrCountry;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;
    @BindView(R.id.edtStreet)
    EditText edtStreet;
    @BindView(R.id.edtCity)
    EditText edtCity;
    @BindView(R.id.textInputLayout4)
    TextInputLayout textInputLayoutSt;
    @BindView(R.id.textInputLayout5)
    TextInputLayout textInputLayoutCity;
    @BindView(R.id.zip)
    EditText edtZip;
    @BindView(R.id.textInputLayoutZip)
    TextInputLayout textInputLayoutZip;
    @BindView(R.id.edtSt1)
    EditText edtSt1;
    @BindView(R.id.textInputLayoutStLine1)
    TextInputLayout textInputLayoutStLine1;
    private boolean userIsInteracting;
    private List<String> stateList;
    private HomeTerminalPresenter presenter;

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_terminal);

        btnNext.setBackgroundResource(R.drawable.arrow);
        spinnerSetup(spinnerCountry, Arrays.asList(arrCountry), getString(R.string.country_selection));

        presenter = Shank.provideNew(HomeTerminalPresenter.class);
        presenter.onViewAttached(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    private void init() {
        stateList = new ArrayList<String>();
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userIsInteracting) {
                    String selectedCountry = spinnerCountry.getItemAtPosition(spinnerCountry.getSelectedItemPosition()).toString();
                    if (!selectedCountry.isEmpty()) {
                        String assetJsonFile = FileUtils.loadJSONFromAsset(HomeTerminalActivity.this, selectedCountry);
                        try {

                            JSONArray jsonarray = new JSONArray(assetJsonFile);
                            stateList.clear();
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String stateName = jsonobject.getString("name");
                                String stateCode = jsonobject.getString("code");
                                stateList.add(stateName);
                            }
                            spinnerSetup(spinnerState, stateList, getString(R.string.state_selection));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userIsInteracting = true;
    }


    @Override
    public Observable<Void> onUserDataSaved() {
        return RxView.clicks(btnNext);
    }

    @OnTextChanged({R.id.edtSt1,R.id.edtStreet, R.id.edtCity, R.id.zip})
    void handleFirstNameEdittextError() {
        resetTextInputErrors(textInputLayoutStLine1,textInputLayoutSt, textInputLayoutCity, textInputLayoutZip);
    }

    @OnFocusChange({R.id.edtSt1,R.id.edtStreet, R.id.edtCity, R.id.zip})
    public void EditTextFocustChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            resetTextInputError(getTextInputField(v.getId()));
        }

    }

    private TextInputLayout getTextInputField(int id) {
        switch (id) {
            case R.id.edtSt1:
                return textInputLayoutStLine1;
            case R.id.edtStreet:
                return textInputLayoutSt;
            case R.id.edtCity:
                return textInputLayoutCity;
            case R.id.zip:
                return textInputLayoutZip;
        }
        return null;
    }

    @Override
    public boolean validateInfomation() {
        if (edtSt1.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutStLine1, edtSt1, "Enter the street line1");
            return false;
        } else if (edtStreet.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutSt, edtStreet, "Enter the street line2");
            return false;
        } else if (edtCity.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutCity, edtCity, "Enter the city");
            return false;
        } else if (spinnerCountry.getSelectedItem().toString().trim().length() <= 0 || spinnerCountry.getSelectedItem().toString().equalsIgnoreCase("select country")) {
            showUserMessage("select country");
            return false;
        } else if (spinnerState.getSelectedItem().toString().trim().length() <= 0 || spinnerState.getSelectedItem().toString().equalsIgnoreCase("select country")) {
            showUserMessage("select state");
            return false;
        } else if (edtZip.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutZip, edtZip, "Enter the zip/postal code");
            return false;
        }

        return true;
    }

    @Override
    public void gotoServiceSettings() {
        startActivity(TimeAndCircleActivity.getStartIntent(this));
    }
}