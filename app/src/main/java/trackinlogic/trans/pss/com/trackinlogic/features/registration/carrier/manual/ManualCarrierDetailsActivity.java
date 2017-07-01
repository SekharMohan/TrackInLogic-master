package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup.TimeAndCircleActivity;
import trackinlogic.trans.pss.com.trackinlogic.util.FileUtils;

public class ManualCarrierDetailsActivity extends BaseActivity implements View.OnClickListener{
    @BindString(R.string.log_settings)
    String navTittle;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindArray(R.array.state)
    String[] arrState;
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
        ButterKnife.bind(this);
        btnNext.setBackgroundResource(R.drawable.arrow);
        //spinnerSetup(spinnerState,arrState,getString(R.string.state_selection));
        spinnerSetup(spinnerCountry,arrCountry,getString(R.string.country_selection));
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
                            stateSpinnerSetup(spinnerState, stateList, getString(R.string.state_selection));
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
    public void stateSpinnerSetup(Spinner spinner, List values, String hint){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManualCarrierDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(values);
        adapter.add(hint);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

    }


    @Override
    public void onClick(View view) {
        startActivity(TimeAndCircleActivity.getStartIntent(this));

    }
    public void spinnerSetup(Spinner spinner, String[] values, String hint) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(values);
        adapter.add(hint);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
    }
    }
