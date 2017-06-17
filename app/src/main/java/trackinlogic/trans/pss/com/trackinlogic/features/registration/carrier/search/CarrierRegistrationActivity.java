package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.DotSearchActivity;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.ManualCarrierDetailsActivity;


public class CarrierRegistrationActivity extends BaseActivity  implements View.OnClickListener{
    @BindString(R.string.log_settings)
    String navTittle;
    @BindView(R.id.regCarrierSearch)
    Button btnCarrierSearch;
    @BindView(R.id.regManual)
    Button btnManualEntry;

    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }
    public static Intent getStartIntent(Context context) {
        return new Intent(context, CarrierRegistrationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_carrier);
        ButterKnife.bind(this);
        btnNext.setVisibility(View.GONE);
        btnCarrierSearch.setOnClickListener(this);
        btnManualEntry.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.regCarrierSearch:
                startActivity(
                        DotSearchActivity.getStartIntent(this));
                break;
            case R.id.regManual:
                startActivity(ManualCarrierDetailsActivity.getStartIntent(this));
                break;
        }

    }
}
