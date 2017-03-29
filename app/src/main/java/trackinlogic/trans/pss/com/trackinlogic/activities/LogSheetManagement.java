package trackinlogic.trans.pss.com.trackinlogic.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.FrameLayout;

import trackinlogic.trans.pss.com.trackinlogic.NavigationMenu;
import trackinlogic.trans.pss.com.trackinlogic.R;

public class LogSheetManagement extends NavigationMenu implements View.OnClickListener{

    private AppCompatButton dvinsButton, signatureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_log_sheet_management, contentFrameLayout);
        setTitle(R.string.log_sheet_management);
        initInitializer();

    }

    private void initInitializer() {

        dvinsButton = (AppCompatButton)findViewById(R.id.dvinsButton);
        signatureButton = (AppCompatButton)findViewById(R.id.signButton);
        dvinsButton.setOnClickListener(this);
        signatureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dvinsButton:
                invokeDialog(R.layout.dvins_dialog, "DVINS");
                break;

            case R.id.signButton:
                invokeDialog(R.layout.sign_dialogue, "Sign");
                break;
        }

    }

    private void invokeDialog(int resId, String title){

        Dialog dialog = new Dialog(this);
        dialog.setContentView(resId);
        dialog.setTitle(title);
        /*Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }



}
