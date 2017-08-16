package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.carrieraddress;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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
import trackinlogic.trans.pss.com.trackinlogic.Henson;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.util.FileUtils;

public class ManualCarrierDetailsActivity extends BaseActivity implements ManualCarrierDetailsPresenter.View {
    @BindString(R.string.carrier_address)
    String navTittle;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindArray(R.array.country)
    String[] arrCountry;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;

    @BindView(R.id.edtCarrierName)
    AppCompatEditText edtCarrierName;
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
    private boolean userIsInteracting;
    private List<String> stateList;
    private ManualCarrierDetailsPresenter presenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, ManualCarrierDetailsActivity.class);
    }

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_details);
        btnNext.setBackgroundResource(R.drawable.arrow);
        spinnerSetup(spinnerCountry, Arrays.asList(arrCountry), getString(R.string.country_selection));

        presenter = Shank.provideNew(ManualCarrierDetailsPresenter.class);
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
                        String assetJsonFile = FileUtils.loadJSONFromAsset(ManualCarrierDetailsActivity.this, selectedCountry);
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
    @OnTextChanged({R.id.edtStreet, R.id.edtCity, R.id.zip})
    void handleFirstNameEdittextError() {
        resetTextInputErrors(textInputLayoutSt,textInputLayoutCity,textInputLayoutZip);
    }

    @OnFocusChange({R.id.edtStreet, R.id.edtCity, R.id.zip})
    public void EditTextFocustChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            resetTextInputError(getTextInputField(v.getId()));
        }

    }
    private TextInputLayout getTextInputField(int id) {
        switch (id) {
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
        if (edtCarrierName.getText().toString().trim().length() <= 0) {
            edtCarrierName.setError("Enter the name");
            return false;
        } else if (edtStreet.getText().toString().trim().length() <= 0) {
            setError(textInputLayoutSt, edtStreet, "Enter the street");
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
        }else if(edtZip.getText().toString().trim().length() <= 0){
            setError(textInputLayoutZip,edtZip,"Enter the zip/postal code");
            return  false;
        }

        return true;
    }

    @Override
    public void gotoHomeTerminalActivity() {
        Intent intent = Henson.with(this).gotoHomeTerminalActivity().build();
        startActivity(intent);
    }
}
