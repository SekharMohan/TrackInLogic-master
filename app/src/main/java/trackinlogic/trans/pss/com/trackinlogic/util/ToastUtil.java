package trackinlogic.trans.pss.com.trackinlogic.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



import trackinlogic.trans.pss.com.trackinlogic.R;

/**
 * Created by sekhar on 23/03/17.
 */

public class ToastUtil {

    public static void showErrorUpdate(Context context,String msg){

        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast. setGravity(Gravity.BOTTOM|Gravity.LEFT|Gravity.FILL_HORIZONTAL, 0, 0);
        View view = toast.getView();

        //To change the Background of Toast)
        view.setBackgroundResource(R.color.colorAccent);
        TextView text = (TextView) view.findViewById(android.R.id.message);

        //Shadow of the Of the Text Color
        text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
        text.setTextColor(Color.WHITE);
        text.setTextSize(14);
        toast.show();

    }

}
