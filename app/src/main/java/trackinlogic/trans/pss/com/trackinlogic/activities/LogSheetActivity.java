package trackinlogic.trans.pss.com.trackinlogic.activities;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.NavigationMenu;
import trackinlogic.trans.pss.com.trackinlogic.R;

public class LogSheetActivity extends NavigationMenu implements View.OnClickListener {

    /*@BindView(R.id.offDutyStartTime)
    public EditText edtOffDutyStartTime;
    @BindView(R.id.offDutyEndTime)
    public EditText edtOffDutyEndTime;*/

    private EditText edtOffDutyStartTime, edtOffDutyEndTime, edtSleepBirthStartTime, edtSleepBirthEndTime, edtDrivingDutyStartTime,
            edtDrivingDutyEndTime, edtOnDutyStartTime, edtOnDutyEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_log_sheet, contentFrameLayout);
        ButterKnife.bind(this);
        initInitializer();

        setTitle(R.string.log_sheet);
    }

    private void initInitializer() {
        edtOffDutyStartTime = (EditText)findViewById(R.id.offDutyStartTime);
        edtOffDutyEndTime = (EditText)findViewById(R.id.offDutyEndTime);
        edtSleepBirthStartTime = (EditText)findViewById(R.id.sleepDutyStartTime);
        edtSleepBirthEndTime = (EditText)findViewById(R.id.sleepDutyEndTime);
        edtDrivingDutyStartTime = (EditText)findViewById(R.id.drivingDutyStartTime);
        edtDrivingDutyEndTime = (EditText)findViewById(R.id.drivingDutyEndTime);
        edtOnDutyStartTime = (EditText)findViewById(R.id.onDutyStartTime);
        edtOnDutyEndTime = (EditText)findViewById(R.id.onDutyEndTime);

        edtOffDutyStartTime.setOnClickListener(this);
        edtOffDutyEndTime.setOnClickListener(this);
        edtSleepBirthStartTime.setOnClickListener(this);
        edtSleepBirthEndTime.setOnClickListener(this);
        edtDrivingDutyStartTime.setOnClickListener(this);
        edtDrivingDutyEndTime.setOnClickListener(this);
        edtOnDutyStartTime.setOnClickListener(this);
        edtOnDutyEndTime.setOnClickListener(this);
        hideKeyInput();
    }

    private void hideKeyInput() {
        edtOffDutyStartTime.setInputType(InputType.TYPE_NULL);
        edtOffDutyEndTime.setInputType(InputType.TYPE_NULL);
        edtSleepBirthStartTime.setInputType(InputType.TYPE_NULL);
        edtSleepBirthEndTime.setInputType(InputType.TYPE_NULL);
        edtDrivingDutyStartTime.setInputType(InputType.TYPE_NULL);
        edtDrivingDutyEndTime.setInputType(InputType.TYPE_NULL);
        edtOnDutyStartTime.setInputType(InputType.TYPE_NULL);
        edtOnDutyEndTime.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.offDutyStartTime:
                invokeTimePicker(edtOffDutyStartTime);
                break;

            case R.id.offDutyEndTime:
                invokeTimePicker(edtOffDutyEndTime);
                break;

            case R.id.sleepDutyStartTime:
                invokeTimePicker(edtSleepBirthStartTime);
                break;

            case R.id.sleepDutyEndTime:
                invokeTimePicker(edtSleepBirthEndTime);
                break;

            case R.id.drivingDutyStartTime:
                invokeTimePicker(edtDrivingDutyStartTime);
                break;

            case R.id.drivingDutyEndTime:
                invokeTimePicker(edtDrivingDutyEndTime);
                break;

            case R.id.onDutyStartTime:
                invokeTimePicker(edtOnDutyStartTime);
                break;

            case R.id.onDutyEndTime:
                invokeTimePicker(edtOnDutyEndTime);
                break;

        }
    }

    private void invokeTimePicker(final EditText editTextTimeView) {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(LogSheetActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                editTextTimeView.setText(((selectedHour <= 9) ? "0" + selectedHour : selectedHour) + ":" + ((selectedMinute <= 9) ? "0" + selectedMinute : selectedMinute));
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
}