package trackinlogic.trans.pss.com.trackinlogic.features.registration;

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

public class RegistrationActivity extends BaseActivity implements View.OnClickListener{
@BindString(R.string.sign_up)
String navTittle;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnDone;
    @BindView(R.id.userType)
    Spinner spiDeviceType;
    @BindArray(R.array.deviceType)
    String[] arrDeviceType;
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
        spinnerSetup(spiDeviceType,arrDeviceType,"Select device type");
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(CarrierRegistrationActivity.getStartIntent(this));

    }

    public void spinnerSetup(Spinner spinner, String[] values, String hint){
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
