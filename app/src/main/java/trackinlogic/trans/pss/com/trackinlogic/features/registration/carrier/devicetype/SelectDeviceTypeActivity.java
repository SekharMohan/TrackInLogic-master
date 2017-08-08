package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.devicetype;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeInputPayLoad;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.devicetype.DeviceTypeModel;

/**
 * Created by Sekhar Madhiyazhagan on 7/15/2017.
 */


public class SelectDeviceTypeActivity extends BaseActivity implements SelectDeviceTypePresenter.View{
    @InjectExtra
    int carrierId;
    @BindView(R.id.actionbar_right_icon)
    ImageView imvProceed;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindString(R.string.carrier_device_type)
    String toolbarTitle;
    SelectDeviceTypePresenter presenter;
    private List<DeviceTypeModel> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_type);
        presenter = Shank.provideNew(SelectDeviceTypePresenter.class);
        presenter.onViewAttached(this);

    }

    private void initializeSelectDeviceTypeActivity() {
        imvProceed.setBackgroundResource(R.drawable.arrow);
    }

    @Override
    protected String setToolbarTitle() {
        return toolbarTitle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public void init() {
        showLodaing();
        initializeSelectDeviceTypeActivity();
    }

    @Override
    public int getCarrierId() {
        return carrierId;
    }

    @Override
    public void onSuccess(List<DeviceTypeModel> deviceTypeModel) {
        dismissLoading();
        devices = deviceTypeModel;
addRadioButtons(deviceTypeModel);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onPostDevice(DeviceTypeModel device) {

dismissLoading();
        gotoRuleActivity();
    }

    private void gotoRuleActivity() {
        Intent intent = Henson.with(this).gotoTimeAndCircleActivity().build();
        startActivity(intent);

    }

    @Override
    public DeviceTypeInputPayLoad selectedDevice() {
        showLodaing();
        DeviceTypeInputPayLoad inputPayLoad = new DeviceTypeInputPayLoad();

        DeviceTypeModel device = devices.get(radioGroup.getCheckedRadioButtonId());
        inputPayLoad.setActive(device.isActive);
        inputPayLoad.setDescription(device.getDescription());
        inputPayLoad.setIdentification(device.getIdentification());
        inputPayLoad.setPhoneNumber(device.getPhoneNumber()
        );
        inputPayLoad.setGroupId(device.getGroupId());
        DeviceTypeInputPayLoad.DeviceType inputPayloadDeviceType = new DeviceTypeInputPayLoad.DeviceType();
        inputPayloadDeviceType.setActive(device.getDeviceType().isActive);
        inputPayloadDeviceType.setName(device.getDeviceType().getName());
        inputPayloadDeviceType.setDescription(device.getDeviceType().getDescription());
        inputPayLoad.setDeviceType(inputPayloadDeviceType);


        return inputPayLoad;
    }

    @Override
    public Observable<Void> onSaveClicked() {
        return RxView.clicks(imvProceed);
    }

    @Override
    public boolean validateDeviceType() {
        return true;
    }

    @Override
    public void connectivityFailed() {
        updateNetworkConnectivity();
    }

    public void addRadioButtons(List<DeviceTypeModel> deviceTypeModel) {

        for (DeviceTypeModel deviceType : deviceTypeModel) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setText(deviceType.getDeviceType().getName());
            radioGroup.addView(rdbtn);

        }

    }
}
