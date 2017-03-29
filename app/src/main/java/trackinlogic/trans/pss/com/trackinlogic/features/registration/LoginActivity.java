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
import trackinlogic.trans.pss.com.trackinlogic.activities.LogBookActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.actionbar_right_icon)
    ImageView btnDone;
    @BindString(R.string.login)
    String navTittle;
    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected String setToolbarTitle() {
        return navTittle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnDone.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,LogBookActivity.class));

    }
}
