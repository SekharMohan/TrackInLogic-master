package trackinlogic.trans.pss.com.trackinlogic.features.common.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.model.LogBookHeader;

/**
 * Created by DELL on 01-04-2017.
 */

public class LogSheetInspectionRecyclerAdapter extends
        RecyclerView.Adapter<LogSheetInspectionRecyclerAdapter.MyViewHolder> {


    private ArrayList<LogBookHeader> logBookHeaderArrayList;

    public LogSheetInspectionRecyclerAdapter(ArrayList<LogBookHeader> logBookHeaderArrayList) {
        this.logBookHeaderArrayList = logBookHeaderArrayList;
    }

    @Override
    public LogSheetInspectionRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inspection_row_layout, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(LogSheetInspectionRecyclerAdapter.MyViewHolder holder, int position) {
        LogBookHeader logSheetInspection = logBookHeaderArrayList.get(position);
        holder.txtViewDate.setText(logSheetInspection.getDate());
        holder.txtViewDay.setText(logSheetInspection.getDay() + ", ");
    }

    @Override
    public int getItemCount() {
        return logBookHeaderArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewDay;
        public TextView txtViewDate;
        public MyViewHolder(View view) {
            super(view);
            txtViewDay = (TextView) view.findViewById(R.id.inspection_day_view);
            txtViewDate = (TextView) view.findViewById(R.id.inspection_date_view);
        }
    }

}