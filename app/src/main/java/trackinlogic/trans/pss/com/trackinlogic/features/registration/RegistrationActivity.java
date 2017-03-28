package trackinlogic.trans.pss.com.trackinlogic.features.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(CarrierRegistrationActivity.getStartIntent(this));

    }
}
