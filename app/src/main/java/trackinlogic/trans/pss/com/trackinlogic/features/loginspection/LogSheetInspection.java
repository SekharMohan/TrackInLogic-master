package trackinlogic.trans.pss.com.trackinlogic.features.loginspection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.NavigationMenu;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.features.common.adapter.LogSheetInspectionRecyclerAdapter;
import trackinlogic.trans.pss.com.trackinlogic.model.LogBookHeader;

public class LogSheetInspection extends NavigationMenu {
    @BindView(R.id.inspectionRecyclerView)
    public RecyclerView inspectionRecyclerView;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    public String[] day = new String[] { "THURSDAY",
            "FRIDAY", "SATURDAY", "SUNDAY" };

    public String[] date = new String[] {
            "MARCH 23","MARCH 24", "MARCH 25",
            "MARCH 26" };
    private ArrayList<LogBookHeader> inspectionRowItems;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_log_sheet_inspection, contentFrameLayout);
        ButterKnife.bind(this);
        setTitle(R.string.log_sheet_inspection);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogSheetInspection.this.onBackPressed();
            }
        });

        ButterKnife.bind(this);
        inspectionRowItems = new ArrayList<LogBookHeader>();
        addToLogSheetInspectionAdapter();
        bindRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.log_sheet_inspection, menu);
        return true;
    }

    private void bindRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        inspectionRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new LogSheetInspectionRecyclerAdapter(inspectionRowItems);
        inspectionRecyclerView.setAdapter(mAdapter);
    }

    private void addToLogSheetInspectionAdapter() {
        for (int i = 0; i < day.length; i++) {
            LogBookHeader logBookHeader = new LogBookHeader();
            logBookHeader.setDate(date[i]);
            logBookHeader.setDay(day[i]);
            inspectionRowItems.add(logBookHeader);
        }
    }
}
