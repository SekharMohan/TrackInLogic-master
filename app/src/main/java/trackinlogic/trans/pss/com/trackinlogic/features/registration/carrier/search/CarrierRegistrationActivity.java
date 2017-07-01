package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;

import butterknife.BindString;
import butterknife.BindView;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.Henson;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.manual.ManualCarrierDetailsActivity;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierQueryString;


public class CarrierRegistrationActivity extends BaseActivity  implements CarrierRegistrationPresenter.View {
    @BindString(R.string.log_settings)
    String navTittle;
    @BindView(R.id.regCarrierSearch)
    Button btnCarrierSearch;
    @BindView(R.id.regManual)
    Button btnManualEntry;
    @BindView(R.id.edtDotNumber)
    EditText edtDotNumber;
    @BindView(R.id.solo)
    RadioButton rbSolo;
    @BindView(R.id.team)
    RadioButton rbTeam;

    @BindView(R.id.actionbar_right_icon)
    ImageView btnNext;
    CarrierRegistrationPresenter presenter;

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
        presenter = Shank.provideNew(CarrierRegistrationPresenter.class) ;
        presenter.onViewAttached(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public void initializeCarrierRegisterActivity() {
        btnNext.setVisibility(View.GONE);
    }

    @Override
    public Observable<Void> onManualBtnClicked() {
        return RxView.clicks(btnManualEntry);
    }

    @Override
    public Observable<Void> onSearchBtnclicked() {
        return RxView.clicks(btnCarrierSearch);
    }

    @Override
    public void onGettingAddressSucess(CarrierDetails carrierDetails) {
        dismissLoading();
       gotoDotSearchActivity(carrierDetails);

    }

    private void gotoDotSearchActivity(CarrierDetails carrierDetails) {
        Intent intent = Henson.with(this)
                .gotoDotSearchActivity().carrierDetails(carrierDetails).build();
        startActivity(intent);
    }

    @Override
    public void onGettingAdressFailure() {
        dismissLoading();
     showUserMessage(getString(R.string.dotid_find_faild));


    }

    @Override
    public boolean isValidateDotId() {
        if(rbSolo.isChecked() || rbTeam.isChecked()) {
            return edtDotNumber.getText().toString().trim().length() >= 0 ? true : false;
        } else  {
            showUserMessage(getString(R.string.device_type));
        }
        return false;
    }

    @Override
    public void gotoManualCarrierRegistrationActivity() {
        startActivity(ManualCarrierDetailsActivity.getStartIntent(this));
    }

    @Override
    public void updateNetworkStatus() {
        updateNetworkConnectivity();

    }

    @Override
    public CarrierQueryString getRequestParams() {
showLodaing();
        return new CarrierQueryString(edtDotNumber.getText().toString(),true,true);
    }

 /*   @Override
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

    }*/
}
