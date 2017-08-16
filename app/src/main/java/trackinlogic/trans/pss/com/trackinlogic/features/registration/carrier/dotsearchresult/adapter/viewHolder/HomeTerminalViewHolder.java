package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.R;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminalViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtLine1)
    public   TextView txtLine1;
    @BindView(R.id.txtLine2)
    public TextView txtLine2;
    @BindView(R.id.txtState)
    public TextView txtState;
    @BindView(R.id.txtZip)
    public TextView txtZip;
    @BindView(R.id.txtCity)
    public  TextView txtCity;
    @BindView(R.id.checkBoxSelection)
    public CheckBox checkBox;
    public HomeTerminalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
