package trackinlogic.trans.pss.com.trackinlogic.features.registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.BindString;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.R;

public class LoginActivity extends BaseActivity {
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
    }
}
