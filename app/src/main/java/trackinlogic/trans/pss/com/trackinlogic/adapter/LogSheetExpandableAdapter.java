package trackinlogic.trans.pss.com.trackinlogic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.activities.LogSheetActivity;
import trackinlogic.trans.pss.com.trackinlogic.model.LogBookHeader;



public class LogSheetExpandableAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private ArrayList<LogBookHeader> headerList;

    public LogSheetExpandableAdapter(Activity context, ArrayList<LogBookHeader>
            headerList) {
        this.context = context;
        this.headerList = headerList;


    }

    @Override
    public int getGroupCount() {
        return headerList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflaInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflaInflater.inflate(R.layout.logsheet_expandable_header,
                    null);
        }
        View view = convertView.findViewById(R.id.headview);
        AppCompatTextView driverDayView = (AppCompatTextView) convertView.findViewById(R.id.driver_day_view);
        AppCompatTextView driverDateView = (AppCompatTextView) convertView.findViewById(R.id.driver_date_view);
        AppCompatTextView driverDurationView = (AppCompatTextView) convertView.findViewById(R.id.driver_duration_view);

        LogBookHeader logBookHeader = headerList.get(groupPosition);



        driverDayView.setText(!TextUtils.isEmpty(logBookHeader.getDay()) ?
                logBookHeader.getDay()+",  " : "");
        driverDateView.setText(!TextUtils.isEmpty(logBookHeader.getDate()) ?
                logBookHeader.getDate() : "");
        driverDurationView.setText(!TextUtils.isEmpty(logBookHeader.getDuration()) ?
                logBookHeader.getDuration()+",  " : "");

        if(isExpanded){
view.setVisibility(View.GONE);
        }else {
            view.setVisibility(View.VISIBLE);
        }

        return convertView;

    }

    @Override
    public View getChildView( int groupPosition, int childPosition,
                              boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.logsheet_expandable_child, null);
        }
       ImageView editView = (ImageView)convertView.findViewById(R.id.editView);

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LogSheetActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
