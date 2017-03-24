package trackinlogic.trans.pss.com.trackinlogic;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.app_bar_title)
    protected TextView appBarTitle;

    protected abstract String setToolbarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected boolean useToolbarAsHome() {
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = this.getLayoutInflater().inflate(layoutResID, null);
        this.configureToolbar(view);
        setContentView(view);
    }


    private void configureToolbar(View view) {
        ButterKnife.bind(this, view);
        if (this.toolbar != null) {
            this.setSupportActionBar(this.toolbar);
            this.getSupportActionBar().setDisplayShowTitleEnabled(false);
            appBarTitle.setText(this.setToolbarTitle());
            if (!this.useToolbarAsHome()) {
                this.getSupportActionBar().setHomeButtonEnabled(true);
                this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                this.toolbar.setNavigationOnClickListener(arrow -> this.onBackPressed());
            }
        }
    }


}
