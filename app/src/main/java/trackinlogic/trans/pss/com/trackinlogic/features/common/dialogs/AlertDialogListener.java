package trackinlogic.trans.pss.com.trackinlogic.features.common.dialogs;

import android.content.DialogInterface;

/**
 * Created by ACMS - India
 */

public interface AlertDialogListener {
    void onPositiveClicked(DialogInterface dialog, int which);

    void onNegativeClicked(DialogInterface dialog, int which);
}
