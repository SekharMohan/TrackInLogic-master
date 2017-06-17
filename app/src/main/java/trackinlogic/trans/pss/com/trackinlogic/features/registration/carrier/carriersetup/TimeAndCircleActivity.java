package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.carriersetup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.logbook.LogBookActivity;

public class TimeAndCircleActivity extends BaseActivity {
    @BindView(R.id.actionbar_right_icon)
    ImageView iViewNext;
    @BindString(R.string.log_settings)
    String navTittle;
    @BindArray(R.array.cycleRule)
    String[] arrCycleRule;
    @BindArray(R.array.odoMeter)
    String[] arrOdoMeter;
    @BindArray(R.array.timeZone)
    String[] arrTimeZone;
@BindView(R.id.spinnerCycleRule)
    Spinner spinnerCycleRule;
    @BindView(R.id.spinnerOdometer)
    Spinner spinnerOdometer;
    @BindView(R.id.spinnerTimeZone)
    Spinner spinnerTimeZone;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, TimeAndCircleActivity.class);
    }
    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_cycle);
        ButterKnife.bind(this);
        iViewNext.setBackgroundResource(R.drawable.ic_tick);
        spinnerSetup(spinnerCycleRule,arrCycleRule,getString(R.string.cycle_rule_selection));
        spinnerSetup(spinnerOdometer,arrOdoMeter,getString(R.string.odometer_selection));
        spinnerSetup(spinnerTimeZone,arrTimeZone,getString(R.string.time_zone_selection));

        iViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TimeAndCircleActivity.this,LogBookActivity.class));
            }
        });
    }

    public void spinnerSetup(Spinner spinner,String[] values,String hint){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(values);
        adapter.add(hint);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
    }
}