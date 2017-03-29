package trackinlogic.trans.pss.com.trackinlogic.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;


import java.util.ArrayList;

import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.NavigationMenu;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.adapter.LogSheetExpandableAdapter;
import trackinlogic.trans.pss.com.trackinlogic.model.LogBookHeader;

public class LogBookActivity extends NavigationMenu {

    ArrayList<LogBookHeader> headerItems;
    private ExpandableListView logSheetExpandable;
    public String[] day = new String[] { "Thursday",
            "Friday", "Saturday", "Sunday" };

    public String[] date = new String[] {
            "March 23","March 24", "March 25",
            "March 26" };

    public String[] duration = new String[] { "8 hours",
            "9 hours", "8 hours", "10 hours" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_log_book, contentFrameLayout);
        ButterKnife.bind(this);
        headerItems = new ArrayList<LogBookHeader>();
        logSheetExpandable = (ExpandableListView)findViewById(R.id.expandable_log_sheet);
        addToLogSheetHeader();
        LogSheetExpandableAdapter expListAdapter = new LogSheetExpandableAdapter(
                this, headerItems);
        logSheetExpandable.setAdapter(expListAdapter);
        setTitle(R.string.log_book);
        setActionToViews();
    }

    private void setActionToViews() {
        logSheetExpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }


    private void addToLogSheetHeader() {
        for (int i = 0; i < day.length; i++) {
            LogBookHeader logBookHeader = new LogBookHeader();
            logBookHeader.setDate(date[i]);
            logBookHeader.setDay(day[i]);
            logBookHeader.setDuration(duration[i]);
            headerItems.add(logBookHeader);
        }
    }
}
