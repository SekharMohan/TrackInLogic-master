package trackinlogic.trans.pss.com.trackinlogic.features.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;

public class DotSearchActivity extends BaseActivity implements View.OnClickListener {
    @BindString(R.string.log_settings)
    String navTitle;
    @BindView(R.id.carrierAddress)
    RelativeLayout relCarrierAddrs;
    @BindView(R.id.btnManual)
    Button btnRegManual;
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
        btnRegManual.setOnClickListener(this);
        relCarrierAddrs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.carrierAddress:
                startActivity(TimeAndCircleActivity.getStartIntent(this));
                break;
            case R.id.regManual:
                startActivity(ManualCarrierDetailsActivity.getStartIntent(this));
                break;
        }

    }
}
