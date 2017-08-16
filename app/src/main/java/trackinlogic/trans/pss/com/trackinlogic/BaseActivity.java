package trackinlogic.trans.pss.com.trackinlogic;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.f2prateek.dart.Dart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.features.common.customview.Loader;
import trackinlogic.trans.pss.com.trackinlogic.util.ToastUtil;
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
        Dart.inject(this);
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

    public void showUserMessage(String msg) {
        ToastUtil.showErrorUpdate(this,msg);
    }

    public void updateNetworkConnectivity() {
        ToastUtil.showErrorUpdate(this,"Please check internet connectivity");

    }
public void showLodaing() {
    Loader.showProgressBar(this);
}
public void dismissLoading() {
    Loader.dismissProgressBar();
}

    /**
     * Method to set spinner values
     * @param spinner
     * @param values
     * @param hint
     */
    public void spinnerSetup(Spinner spinner, List<String> values, String hint) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(values);
        adapter.add(hint);

        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());
    }

    /**
     * Method to set field errors
     * @param textInputLayout
     * @param editText
     * @param errorMsg
     */
    public void setError(TextInputLayout textInputLayout, EditText editText, String errorMsg) {
        setEditFieldSelection(editText);
        editText.requestFocus();
        textInputLayout.setError(errorMsg);
        textInputLayout.setErrorEnabled(true);

    }
    private void setEditFieldSelection(EditText editText) {
        if (editText.getText().toString().length() != 0) {
            editText.setSelection(editText.getText().toString().length());
        }
    }
    public void resetTextInputErrors(TextInputLayout... textInputLayouts) {
        for (TextInputLayout textInputLayout : textInputLayouts) {
            resetTextInputError(textInputLayout);
        }
    }

    public void resetTextInputError(TextInputLayout textInputField) {
        if (textInputField != null && !TextUtils.isEmpty(textInputField.getError())) {
            textInputField.setError(null);
            textInputField.setErrorEnabled(false);
        }

    }

}
