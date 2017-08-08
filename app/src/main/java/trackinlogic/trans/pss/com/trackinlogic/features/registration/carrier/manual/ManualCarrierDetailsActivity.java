package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCircleActivity;
import trackinlogic.trans.pss.com.trackinlogic.util.FileUtils;

public class ManualCarrierDetailsActivity extends BaseActivity implements View.OnClickListener{
    @BindString(R.string.carrier_settings)
    String navTittle;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindArray(R.array.country)
    String[] arrCountry;
    private boolean userIsInteracting;
    private List<String> stateList;
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
        spinnerSetup(spinnerCountry, Arrays.asList(arrCountry),getString(R.string.country_selection));
        btnNext.setOnClickListener(this);
        init();
    }

    private void init() {
        stateList = new ArrayList<String>();
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( userIsInteracting) {

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
    public void onClick(View view) {
        startActivity(TimeAndCircleActivity.getStartIntent(this));
    }

    }
