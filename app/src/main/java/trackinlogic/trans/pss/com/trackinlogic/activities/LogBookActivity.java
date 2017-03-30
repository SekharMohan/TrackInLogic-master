package trackinlogic.trans.pss.com.trackinlogic.activities;

import android.os.Bundle;
import android.view.Menu;
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
    public String[] day = new String[] { "THURSDAY",
            "FRIDAY", "SATURDAY", "SUNDAY" };

    public String[] date = new String[] {
            "MARCH 23","MARCH 24", "MARCH 25",
            "MARCH 26" };

    public String[] duration = new String[] { "8 hrs",
            "9 hrs", "8 hrs", "10 hrs" };


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_book_menu, menu);
        return true;
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
