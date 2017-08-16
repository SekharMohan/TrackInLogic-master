package trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.List;

import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.registration.carrier.dotsearchresult.adapter.viewHolder.HomeTerminalViewHolder;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.Address;
import trackinlogic.trans.pss.com.trackinlogic.model.registration.homeTerminals.HomeTerminals;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminalAdapter extends RecyclerView.Adapter<HomeTerminalViewHolder> {

    private List<HomeTerminals> homeTerminalsList;
    private int selectedPosition = -1;
    private boolean isHomeTerminalChecked;

   public   HomeTerminalAdapter(List<HomeTerminals> list) {
        homeTerminalsList = list;
    }

    @Override
    public HomeTerminalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_terminal, parent, false);
        return new HomeTerminalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeTerminalViewHolder holder, int position) {
        Address address = homeTerminalsList.get(position).getAddresses().get(0);
        holder.txtLine1.setText(address.getLine1());
        holder.txtLine2.setText(address.getLine2());
        holder.txtCity.setText(address.getCity());
        holder.txtState.setText(address.getStateOrProvience());
        holder.txtZip.setText(address.getPostalCode());
        holder.checkBox.setTag(position);

        if (position == selectedPosition) {
            holder.checkBox.setChecked(true);
        } else holder.checkBox.setChecked(false);

        holder.checkBox.setOnClickListener(onStateChangedListener(holder.checkBox, position));


    }

    @Override
    public int getItemCount() {
        return homeTerminalsList.size();
    }

    private View.OnClickListener onStateChangedListener(final CheckBox checkBox, final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    selectedPosition = position;
                    isHomeTerminalChecked = true;
                } else {
                    selectedPosition = -1;
                    isHomeTerminalChecked = false;
                }
                notifyDataSetChanged();
            }
        };
    }

    public boolean isHomeTerminalAddressChecked() {
        return isHomeTerminalChecked;
    }
}
