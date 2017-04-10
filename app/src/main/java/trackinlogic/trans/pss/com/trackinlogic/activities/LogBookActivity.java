package trackinlogic.trans.pss.com.trackinlogic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import trackinlogic.trans.pss.com.trackinlogic.NavigationMenu;
import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.adapter.LogSheetExpandableAdapter;
import trackinlogic.trans.pss.com.trackinlogic.model.LogBookHeader;

public class LogBookActivity extends NavigationMenu {
    private LogBookActivity binding;
    private Animation fabOpenAnimation;
    private Animation fabCloseAnimation;
    private boolean isFABOpen = false;
    @BindView(R.id.fab)
    FloatingActionButton fab ;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab2)
    FloatingActionButton fab2;

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



        setTitle(R.string.log_book);
        setActionToViews();
        init();
    }

    private void init() {

        DisplayMetrics metrics = new DisplayMetrics();

        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;



        this.logSheetExpandable.setIndicatorBounds(width - GetPixelFromDips(70), width - GetPixelFromDips(20));
        this.logSheetExpandable.setIndicatorBoundsRelative(width - GetPixelFromDips(70), width - GetPixelFromDips(20));
        LogSheetExpandableAdapter expListAdapter = new LogSheetExpandableAdapter(
                this, headerItems);
        logSheetExpandable.setAdapter(expListAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                startActivity(new Intent(getApplicationContext(),LogSheetInspection.class));


            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                startActivity(new Intent(getApplicationContext(),LogSheetManagement.class));
            }
        });
    }

    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        float scale = this.getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }


    private void setActionToViews() {
logSheetExpandable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println();
    }
});
        logSheetExpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }
    private void showFABMenu(){
        isFABOpen=true;
        fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_75));
        fab2.animate().translationX(-getResources().getDimension(R.dimen.standard_75));
            }

    private void closeFABMenu(){
        if(isFABOpen) {
            isFABOpen = false;
            fab1.animate().translationY(0);
            fab2.animate().translationX(0);
        }

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

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
          super.onBackPressed();
        }else{
            closeFABMenu();
        }
    }
    private int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
