package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.f2prateek.dart.InjectExtra;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.Henson;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;

public class DotSearchActivity extends BaseActivity implements View.OnClickListener {

    @InjectExtra
    CarrierDetails carrierDetails;
    @BindString(R.string.carrier_settings)
    String navTitle;
    @BindView(R.id.carrierAddress)
    RelativeLayout relCarrierAddrs;


    @BindView(R.id.companyName)
    TextView tvCompanyName;
    @BindView(R.id.address1)
    TextView tvAddressLine1;
    @BindView(R.id.address2)
    TextView tvAddressLine2;
    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DotSearchActivity.class);
    }

    @Override
    protected String setToolbarTitle() {
        return navTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_search);
        ButterKnife.bind(this);
        btnNext.setVisibility(View.GONE);

        relCarrierAddrs.setOnClickListener(this);
        init();
    }

    private void init() {
        tvCompanyName.setText(getString(R.string.adrr_name,carrierDetails.getName()));
        tvAddressLine1.setText(getString(R.string.addr_dotId,carrierDetails.getDotId()));
        tvAddressLine2.setText(getString(R.string.addr_desc,carrierDetails.getDescription()));
    }
private void gotoDeviceTypeActivity() {

    Intent intent = Henson.with(this).gotoSelectDeviceTypeActivity().carrierId(carrierDetails.getId()).build();
    startActivity(intent);


}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.carrierAddress:
                gotoDeviceTypeActivity();
                break;

        }

    }
}
