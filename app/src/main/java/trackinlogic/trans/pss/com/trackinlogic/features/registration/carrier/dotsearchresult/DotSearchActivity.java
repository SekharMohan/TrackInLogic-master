package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.f2prateek.dart.InjectExtra;
import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import rx.Observable;
import trackinlogic.trans.pss.com.trackinlogic.BaseActivity;
import trackinlogic.trans.pss.com.trackinlogic.Henson;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.adapter.HomeTerminalAdapter;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.carrier.CarrierDetails;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.homeTerminals.HomeTerminals;
import trackinlogic.trans.pss.com.trackinlogic.util.ToastUtil;

public class DotSearchActivity extends BaseActivity implements DotSearchPresenter.View {

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
    @BindView(R.id.rvHomeTerminalList)
    RecyclerView rvHomeTerminalList;

    @BindView(R.id.textView5)
    TextView textView5;
    private DotSearchPresenter presenter;
    HomeTerminalAdapter adapter;


    @Override
    protected String setToolbarTitle() {
        return navTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_search);

        presenter = Shank.provideNew(DotSearchPresenter.class);
        presenter.onViewAttached(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    private void initDotSearchActivity() {
        tvCompanyName.setText(getString(R.string.adrr_name, carrierDetails.getName()));
        tvAddressLine1.setText(getString(R.string.addr_dotId, carrierDetails.getDotId()));
        tvAddressLine2.setText(getString(R.string.addr_desc, carrierDetails.getDescription()));
    }

    @Override
    public void init() {
        showLodaing();
        initDotSearchActivity();
    }

    @Override
    public Observable<Void> onNextButtonClicked() {
        return RxView.clicks(btnNext);
    }

    @Override
    public boolean validation() {
        if (!adapter.isHomeTerminalAddressChecked()) {
            showUserMessage(
                    "Please select home terminal address");
            return false;
        }

        return true;

    }

    @Override
    public int getCarrierId() {
        return carrierDetails.getId();
    }

    @Override
    public void showHomeTerminals(List<HomeTerminals> homeTerminals) {
        dismissLoading();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHomeTerminalList.setLayoutManager(layoutManager);
        adapter = new HomeTerminalAdapter(homeTerminals);
        rvHomeTerminalList.setAdapter(adapter);
    }

    @Override
    public void onFailed() {
        dismissLoading();
        ToastUtil.showErrorUpdate(this, "Unable to fetch data");
    }

    @Override
    public void networkConnectivity() {
        updateNetworkConnectivity();

    }

    @Override
    public void gotoDeviceTypeActivity() {
        Intent intent = Henson.with(this).gotoSelectDeviceTypeActivity().carrierId(carrierDetails.getId()).build();
        startActivity(intent);


    }


}
