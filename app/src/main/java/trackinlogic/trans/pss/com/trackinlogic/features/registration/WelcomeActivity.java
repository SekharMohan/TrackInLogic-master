package trackinlogic.trans.pss.com.trackinlogic.features.registration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.wel_btn_login)
    Button btnLogin;
    @BindView(R.id.wel_btn_signup)
    Button btnSignup;
    @BindView(R.id.welViewFlipper)
    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(2000);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wel_btn_login:
                startActivity(LoginActivity.getStartIntent(this));
                break;
            case R.id.wel_btn_signup:
            startActivity(RegistrationActivity.getStartIntent(this));
            break;
        }
    }
}
