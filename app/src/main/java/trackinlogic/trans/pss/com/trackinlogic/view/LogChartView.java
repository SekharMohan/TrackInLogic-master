package trackinlogic.trans.pss.com.trackinlogic.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import trackinlogic.trans.pss.com.trackinlogic.R;
import trackinlogic.trans.pss.com.trackinlogic.model.CycleReset;
import trackinlogic.trans.pss.com.trackinlogic.model.CycleResetMetaData;
import trackinlogic.trans.pss.com.trackinlogic.model.Event;
import trackinlogic.trans.pss.com.trackinlogic.model.Log;
import trackinlogic.trans.pss.com.trackinlogic.model.Remark;
import trackinlogic.trans.pss.com.trackinlogic.model.User;
import trackinlogic.trans.pss.com.trackinlogic.model.Violation;
import trackinlogic.trans.pss.com.trackinlogic.util.BitmapHolder;
import trackinlogic.trans.pss.com.trackinlogic.util.Util;
import trackinlogic.trans.pss.com.trackinlogic.util.time.TimeUtil;

public class LogChartView extends View {
    private static final String[] COLUMN_LABELS = new String[]{"M", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "N", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "M"};
    private static final String[] COLUMN_LABELS_25 = new String[]{"M", "1", "DST", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "N", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "M"};
private static final String[] LEGENDS=new String[]{"OFF","SB","D","ON"};
    public static final int EVENT = 0;
    public static final int MODE_ADD_EVENT = 3;
    public static final int MODE_ADD_REMARK = 4;
    public static final int MODE_DISPLAY = 0;
    public static final int MODE_EDIT_EVENT = 2;
    public static final int MODE_EDIT_REMARK = 5;
    public static final int MODE_NORMAL = 1;
    public static final int REMARK = 1;
    public static final int RESET = 4;
    public static final int SHORT_HAUL = 3;
    private static final String TAG = "LogChartView";
    public static final int VIOLATION = 2;
    private int HOUR = 3600;
    private int MINUTE = 60;
    private int[] adjustTimes;
    private Paint bitmapPaint = new Paint();
    private Bitmap centerKnob;
    private float chartEndX = 0.0f;
    private float chartEndY = 0.0f;
    private float chartLabelMargin = 0.0f;
    private float chartStartX = 0.0f;
    private float chartStartY = 0.0f;
    private float chartWidth = 0.0f;
    private int columnTextSize;
    private List<CycleReset> cycleResets;
    private int dashGap = MODE_DISPLAY;
    private int dashLength = MODE_DISPLAY;
    private Paint daylightSavingsPaint = new Paint();
    private Paint dialPaint = new Paint();
    private boolean drawViolationLines = true;
    private Event editEvent;
    private int editStatus = 1;
    public Dial endDial;
    int endDialPointerID = -1;
    private int eventInterval = (this.MINUTE * 15);
    private float eventIntervalWidth = 0.0f;
    private EventSelectedCallback eventSelectedCallback;
    private List<Event> events;
    private SparseArray<Integer> highlightedItems = new SparseArray();
    private float hourWidth = 0.0f;
    long lastHapticFeedbackTime = 0;
    private Bitmap leftHandle;
    private Bitmap leftHandleRed;
    private Log log;
    float logChartLineStrokeWidth;
    private Paint logChartPaint = new Paint();
    private int mode = MODE_DISPLAY;
    int myHeight;
    int myWidth;
    private boolean normalPhonePortrait;
    private Paint paint = new Paint();
    private boolean phone;
    private boolean portrait;
    private float quarterWidth = 0.0f;
    private int remarkInterval = this.MINUTE;
    private float remarkIntervalWidth = 0.0f;
    float remarkLineBoldStrokeWidth;
    float remarkLineStrokeWidth;
    private Paint remarkPaint = new Paint();
    private List<Remark> remarks;
    private Bitmap rightHandle;
    private Bitmap rightHandleRed;
    private float rowHeight = 0.0f;
    private int rowTextSize = MODE_DISPLAY;
    private int rows = RESET;
    private float secondsWidth = 0.0f;
    public Dial startDial;
    int startDialPointerID = -1;
    private Paint statusLineHorizontalPaint = new Paint();
    float statusLineHorizontalStrokeWidth;
    private Paint statusLineVerticalPaint = new Paint();
    float statusLineVerticalStrokeWidth;
    private TextPaint textColumnPaint = new TextPaint(193);
    private TextPaint textRowPaint = new TextPaint(193);
    private int[] totalTimes={20,200,400,5};
    private int touchCircleRadius;
    private User user;
    float viewHeight;
    float viewWidth;
    private Paint violationLinePaint = new Paint();
    private Map<String, List<Violation>> violationsByType;

    public interface OnTimeChangeListener {
        void onTimeChange(long j);
    }

    public interface EventSelectedCallback {
        void eventSelected(int i);

        void onTouch();
    }

    public class Dial {
        public static final int CENTER = 2;
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public float anchor_x;
        public int color;
        public boolean edits_driving;
        private float flag_bottom;
        private float flag_top;
        private float flag_width;
        public int last_seconds;
        private OnTimeChangeListener listener;
        public float origin_x;
        private float pole_bottom;
        public float stroke_width;
        public float text_height;
        public float text_size;
        public String time;
        public long time_long;
        public boolean touch_enabled = false;
        public boolean visible = false;
        public float x;
        public float y;

        public void set_x(float x) {
            this.time_long = 0;
            set(x, this.y);
        }

        public void set_y(float y) {
            set(this.x, y);
        }

        public void set(float x, float y) {
            this.x = x;
            this.y = y;
            if (this.listener != null) {
                this.listener.onTimeChange(time_long(false));
            }
        }

        public void setTimeChangeListener(OnTimeChangeListener listener) {
            this.listener = listener;
        }

        public void set_flag_coordinates(float top, float bottom, float width, float pole_bottom) {
            this.flag_top = top;
            this.flag_bottom = bottom;
            this.flag_width = width;
            this.pole_bottom = pole_bottom;
        }

        public long time_long(boolean display) {
            float adjusted_x = this.x;
            if (!display && LogChartView.this.log.is_daylight_savings_23() && adjusted_x >= LogChartView.this.chartStartX + (3.0f * LogChartView.this.hourWidth)) {
                adjusted_x -= LogChartView.this.hourWidth;
            }
            return LogChartView.this.log.utc_start_time_long() + ((long) LogChartView.this.getSeconds(adjusted_x));
        }

        public void set_seconds_offset(int seconds_offset) {
            float x = LogChartView.this.getX(seconds_offset);
            if (x < LogChartView.this.chartStartX) {
                x = LogChartView.this.chartStartX;
            }
            if (x > LogChartView.this.chartEndX) {
                x = LogChartView.this.chartEndX;
            }

            this.x = x;
            this.time_long = LogChartView.this.log.utc_start_time_long() + ((long) seconds_offset);
            LogChartView.this.postInvalidate();
        }

        public int get_seconds_offset() {
            long seconds_offset = (this.time_long != 0 ? this.time_long : time_long(true)) - LogChartView.this.log.utc_start_time_long();
//            DebugLog.i(LogChartView.TAG, "GET seconds_offset: " + seconds_offset);
            return (int) seconds_offset;
        }

        public int get_timepicker_seconds_offset() {
            int seconds = get_seconds_offset();
            if (!LogChartView.this.log.is_daylight_savings_25() || seconds < LogChartView.this.HOUR * CENTER) {
                return seconds;
            }
            return seconds - LogChartView.this.HOUR;
        }

        public String get_display_time() {
            return TimeUtil.displayTimeOfDay(time_long(false), LogChartView.this.log.eld_enabled() ? "h:mm:ss" : "h:mm", LogChartView.this.user.time_zone);
        }

        public void draw(Canvas canvas, int position) {
            float x_start;
            Bitmap bitmap;
            float x_bitmap;
            switch (position) {
                case LEFT /*0*/:
                    x_start = this.x - this.flag_width;
                    bitmap = (this.edits_driving && LogChartView.this.log.eld_enabled()) ? LogChartView.this.leftHandleRed : LogChartView.this.leftHandle;
                    x_bitmap = (this.x - ((float) bitmap.getWidth())) + (this.stroke_width / 2.0f);
                    break;
                case RIGHT /*1*/:
                    x_start = this.x;
                    if (this.edits_driving && LogChartView.this.log.eld_enabled()) {
                        bitmap = LogChartView.this.rightHandleRed;
                    } else {
                        bitmap = LogChartView.this.rightHandle;
                    }
                    x_bitmap = this.x - (this.stroke_width / 2.0f);
                    break;
                default:
                    x_start = this.x - (this.flag_width / 2.0f);
                    bitmap = LogChartView.this.centerKnob;
                    x_bitmap = this.x - ((float) (bitmap.getWidth() / CENTER));
                    break;
            }
            LogChartView.this.paint.setTextSize(this.text_size);
            LogChartView.this.paint.setStrokeWidth(this.stroke_width);
            LogChartView.this.paint.setStyle(Style.FILL);
            Resources resources = LogChartView.this.getResources();
            int i = (this.edits_driving && LogChartView.this.log.eld_enabled()) ? R.color.primary_red : R.color.log_chart_dial;
            this.color = resources.getColor(i);
            LogChartView.this.paint.setColor(this.color);
            canvas.drawLine(this.x, this.visible ? this.flag_top : LogChartView.this.chartStartY, this.x, this.pole_bottom, LogChartView.this.paint);
            if (this.visible) {
                LogChartView.this.paint.setStyle(Style.FILL_AND_STROKE);
                canvas.drawRect(x_start, this.flag_top, x_start + this.flag_width, this.flag_bottom, LogChartView.this.paint);
                if (this.touch_enabled) {
                    canvas.drawBitmap(bitmap, x_bitmap, this.pole_bottom, LogChartView.this.paint);
                }
                LogChartView.this.dialPaint.setColor(-1);
                LogChartView.this.dialPaint.setStyle(Style.FILL);
                LogChartView.this.dialPaint.setTextAlign(Align.CENTER);
                Rect rect = new Rect();
                LogChartView.this.dialPaint.getTextBounds("0", LEFT, "0".length(), rect);
                this.text_height = (float) rect.height();
                canvas.drawText(get_display_time(), (this.flag_width / 2.0f) + x_start, ((this.flag_top + this.flag_bottom) / 2.0f) + (this.text_height / 2.0f), LogChartView.this.dialPaint);
            }
        }
    }

    public LogChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void setLogData(User user, Log log, List<Event> events, List<Remark> remarks, Map<String, List<Violation>> violationsByType, List<CycleReset> cycleResets, boolean phone, int orientation, EventSelectedCallback eventSelectedCallback) {
        boolean z;
        this.user = user;
        this.log = log;
        this.events = events;
        this.remarks = remarks;
        this.violationsByType = violationsByType;
        this.cycleResets = cycleResets;
        this.phone = phone;
        this.portrait = orientation == REMARK;
        if (phone && this.portrait) {
            z = true;
        } else {
            z = false;
        }
        this.normalPhonePortrait = z;
        this.eventSelectedCallback = eventSelectedCallback;
        setHighlightedItem(MODE_DISPLAY, -1);
        setHighlightedItem(REMARK, -1);
        setHighlightedItem(RESET, -1);
        setHighlightedItem(VIOLATION, -1);
        init();
    }

    private void init() {
        this.eventInterval = this.log.get_event_seconds_interval(this.user);
        this.rows = this.log.exception_wait_time ? MODE_EDIT_REMARK : RESET;
        this.totalTimes = new int[MODE_EDIT_REMARK];
        this.adjustTimes = new int[MODE_EDIT_REMARK];
        if (this.startDial == null) {
            this.startDial = new Dial();
        }
        if (this.endDial == null) {
            this.endDial = new Dial();
        }
//        LogsController.getInstance().calculateEventDurations(this.eventInterval, this.events, this.log.utc_start_time_long(), this.log.utc_end_time_long());
        for (Event event : this.events) {
            String str = (this.rows == RESET && TextUtils.equals(event.type, Event.WAITING)) ? Event.OFF_DUTY : event.type;
            int index = Event.get_status_index(str);
            int[] iArr = this.totalTimes;
            iArr[index] = iArr[index] + event.duration;
        }
        postInvalidate();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.myWidth = w;
        this.myHeight = 600;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startDial = new Dial();
        endDial = new Dial();
        remarks=new ArrayList<>();
        events=new ArrayList<>();
        for(int i=1;i<5;i++){
            Event e=new Event();
            e.type="test";
            e.duration = i*10;
            events.add(e);
        }
        log=new Log();
//        if (this.log != null && this.events != null && this.remarks != null) {
            initSizes(this.myWidth, this.myHeight);
            drawChartLabels(canvas);
            drawLogChart(canvas);

//         drawRemarks(canvas, true);
//         drawCycleResets(canvas);
//            drawStatusLines(canvas);
//            drawRemarks(canvas, true);
//            drawViolationLines(canvas);
//            drawEditStatusLines(canvas, true);
            drawHighlightedSegment(canvas);
         /*   if (this.log.is_daylight_savings_23()) {
                float x = (this.chartStartX + (this.hourWidth * 2.0f)) + 2.0f;
                Canvas canvas2 = canvas;
                canvas2.drawRect(x, this.chartStartY - 2.0f, (this.hourWidth + x) - (2.0f * 2.0f), (((float) this.rows) * this.rowHeight) + (this.chartStartY + 2.0f), this.daylightSavingsPaint);
            }
*///        }
    }

    private void initSizes(int width, int height) {
        if (this.log != null) {
            Resources resources;
            int i;
            this.chartWidth = (this.normalPhonePortrait ? 0.83f : 0.87f) * ((float) width);
            float heightModifier = this.normalPhonePortrait ? this.rows == RESET ? 0.1818f : 0.2121f : this.rows == RESET ? 0.1429f : 0.1786f;
            this.rowHeight = (this.chartWidth * heightModifier) / ((float) this.rows);
            float chartHeight = this.rowHeight * ((float) this.rows);
            this.logChartLineStrokeWidth = 0.0015f * this.chartWidth;
            this.statusLineHorizontalStrokeWidth = 0.005f * this.chartWidth;
            this.statusLineVerticalStrokeWidth = this.statusLineHorizontalStrokeWidth / 2.0f;
            this.remarkLineStrokeWidth = 0.003f * this.chartWidth;
            this.remarkLineBoldStrokeWidth = 0.005f * this.chartWidth;
            this.hourWidth = this.chartWidth / ((float) this.log.hours_in_day_log_chart());
            this.quarterWidth = this.hourWidth / 4.0f;
            this.secondsWidth = this.hourWidth / 3600.0f;
            this.eventIntervalWidth = this.hourWidth / (3600.0f / ((float) this.eventInterval));
            this.remarkIntervalWidth = this.hourWidth / 60.0f;
            this.rowTextSize = (int) ((this.rowHeight * 2.0f) / 3.0f);
            this.textRowPaint.setTextSize((float) this.rowTextSize);
            this.columnTextSize = (int) (this.hourWidth / 2.0f);
            this.textColumnPaint.setTextSize((float) this.columnTextSize);
            float flagTopMargin = (float) getResources().getDimensionPixelOffset(R.dimen.log_chart_flag_margin_top);
            this.dashLength = getResources().getDimensionPixelOffset(R.dimen.log_chart_line_dash_length);
            this.dashGap = getResources().getDimensionPixelOffset(R.dimen.log_chart_line_dash_gap);
            if (!(this.startDial == null || this.endDial == null)) {
                Dial dial = this.startDial;
                float f = (this.normalPhonePortrait ? 1.4f : 1.0f) * ((float) this.rowTextSize);
                this.endDial.text_size = f;
                dial.text_size = f;
                this.chartStartX = ((((float) width) - this.chartWidth) / 2.0f) - ((float) (this.columnTextSize / VIOLATION));
                if (this.mode == 0) {
                    float f2 = this.startDial.text_size;
                    if (this.portrait && this.phone) {
                        f = (-flagTopMargin) / 2.0f;
                    } else {
                        f = flagTopMargin;
                    }
                    f += f2;
                } else {
                    f = this.startDial.text_size + (3.0f * flagTopMargin);
                }
                this.chartStartY = f;
                this.chartEndY = this.chartStartY + chartHeight;
                this.chartLabelMargin = (float) getResources().getDimensionPixelOffset(R.dimen.card_margin_side_half);
                this.touchCircleRadius = ((int) this.hourWidth) * RESET;
                Dial dial2 = this.startDial;
                dial = this.endDial;
                float f3 = this.statusLineHorizontalStrokeWidth;
                dial.stroke_width = f3;
                dial2.stroke_width = f3;
                this.startDial.set_flag_coordinates(flagTopMargin, this.chartStartY - this.logChartLineStrokeWidth, (this.normalPhonePortrait ? 5.0f : 2.0f) * this.hourWidth, this.chartEndY);
                this.startDial.set_y(this.chartEndY);
                this.endDial.set_y(this.chartEndY);
                dial2 = this.startDial;
                dial = this.endDial;
                int color = getResources().getColor(R.color.log_chart_dial);
                dial.color = color;
                dial2.color = color;
                this.endDial.set_flag_coordinates(flagTopMargin, this.chartStartY - this.logChartLineStrokeWidth, (this.normalPhonePortrait ? 5.0f : 2.0f) * this.hourWidth, this.chartEndY);
            }
            /*if (this.log != null && (this.chartEndX == 0.0f || !(this.mode == SHORT_HAUL || this.mode == VIOLATION))) {
                long now = TimeUtil.currentTimeNearestInterval(this.eventInterval, true);
                if (now < this.log.utc_start_time_long() || now >= this.log.utc_end_time_long()) {
                    long seconds=System.currentTimeMillis();
                    *//*long seconds = (long) getSecondDifference(this.log.utc_start_time_long(), now);*//*
                    this.chartEndX = this.chartStartX + (((float) (seconds / ((long) this.eventInterval))) * this.eventIntervalWidth);
                    if (this.log.is_daylight_savings_23() && seconds >= ((long) (this.HOUR * VIOLATION))) {
                        this.chartEndX += this.hourWidth;
                    }
                }
            }*/
            this.viewWidth = (float) width;
           /* if (this.leftHandle == null) {
                resources = getResources();
                i = this.phone ? R.drawable.handle_left : this.portrait ? R.drawable.handle_left_ipad : R.drawable.handle_left_ipad_land;
                this.leftHandle = BitmapHolder.getBitmap(resources, i);
            }
            if (this.leftHandleRed == null) {
                resources = getResources();
                i = this.phone ? R.drawable.handle_left_red : this.portrait ? R.drawable.handle_left_red_ipad : R.drawable.handle_left_red_ipad_land;
                this.leftHandleRed = BitmapHolder.getBitmap(resources, i);
            }
            if (this.rightHandle == null) {
                resources = getResources();
                i = this.phone ? R.drawable.handle_right : this.portrait ? R.drawable.handle_right_ipad : R.drawable.handle_right_ipad_land;
                this.rightHandle = BitmapHolder.getBitmap(resources, i);
            }
            if (this.rightHandleRed == null) {
                resources = getResources();
                i = this.phone ? R.drawable.handle_right_red : this.portrait ? R.drawable.handle_right_red_ipad : R.drawable.handle_right_red_ipad_land;
                this.rightHandleRed = BitmapHolder.getBitmap(resources, i);
            }
            if (this.centerKnob == null) {
                resources = getResources();
                i = this.phone ? R.drawable.handle_center : this.portrait ? R.drawable.handle_center_ipad : R.drawable.handle_center_ipad_land;
                this.centerKnob = BitmapHolder.getBitmap(resources, i);
            }*/
            switch (this.mode) {
                case MODE_DISPLAY /*0*/:
                    this.viewHeight = (this.chartEndY + flagTopMargin) + (flagTopMargin / 2.0f);
                    break;
                case VIOLATION /*2*/:
                case SHORT_HAUL /*3*/:
                case RESET /*4*/:
                case MODE_EDIT_REMARK /*5*/:
                    this.viewHeight = (float) ((int) (this.chartEndY + (((float) this.centerKnob.getHeight()) * 1.35f)));
                    break;
                default:
                    this.viewHeight = (float) ((int) (this.chartEndY + (this.chartStartY * 0.8f)));
                    break;
            }
            initPaints();
        }
    }

    private void initPaints() {
        this.textRowPaint.setTextSize((float) this.rowTextSize);
        this.textRowPaint.setTypeface(Typeface.create("sans-serif", MODE_DISPLAY));
        this.textRowPaint.setColor(getResources().getColor(R.color.secondary_grey));
        this.textRowPaint.setAntiAlias(true);
        this.textRowPaint.setTextAlign(Align.CENTER);
        this.textColumnPaint.setTextSize((float) this.columnTextSize);
        this.textColumnPaint.setTypeface(Typeface.create("sans-serif-light", MODE_DISPLAY));
        this.textColumnPaint.setColor(getResources().getColor(R.color.secondary_grey));
        this.textColumnPaint.setAntiAlias(true);
        this.textColumnPaint.setTextAlign(Align.CENTER);
        this.logChartPaint.setStrokeWidth(this.logChartLineStrokeWidth);
        this.logChartPaint.setStyle(Style.STROKE);
        this.logChartPaint.setAntiAlias(true);
        this.logChartPaint.setColor(getResources().getColor(R.color.light_grey_1));
        this.statusLineHorizontalPaint.setStrokeWidth(this.statusLineHorizontalStrokeWidth);
        this.statusLineHorizontalPaint.setStyle(Style.STROKE);
        this.statusLineHorizontalPaint.setAntiAlias(true);
        this.statusLineHorizontalPaint.setColor(getResources().getColor(R.color.log_chart_event_line_dim));
        this.statusLineVerticalPaint.setStrokeWidth(this.statusLineVerticalStrokeWidth);
        this.statusLineVerticalPaint.setStyle(Style.STROKE);
        this.statusLineVerticalPaint.setAntiAlias(true);
        this.statusLineVerticalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
        this.violationLinePaint.setStrokeWidth(this.statusLineHorizontalStrokeWidth);
        this.violationLinePaint.setStyle(Style.STROKE);
        this.violationLinePaint.setAntiAlias(true);
        this.violationLinePaint.setColor(getResources().getColor(R.color.log_chart_violation_line));
        this.remarkPaint.setStrokeWidth(this.remarkLineStrokeWidth);
        this.remarkPaint.setStyle(Style.STROKE);
        this.remarkPaint.setAntiAlias(true);
        this.remarkPaint.setColor(getResources().getColor(R.color.log_chart_remark_line));
        this.dialPaint.setTextSize(this.startDial.text_size);
        this.dialPaint.setTypeface(Typeface.create("sans-serif-light", MODE_DISPLAY));
        this.dialPaint.setColor(getResources().getColor(R.color.white));
        this.dialPaint.setAntiAlias(true);
        this.dialPaint.setTextAlign(Align.CENTER);
        this.bitmapPaint.setAntiAlias(true);
        this.daylightSavingsPaint.setStyle(Style.FILL);
        this.daylightSavingsPaint.setColor(getResources().getColor(R.color.white));
    }

    private void drawLogChart(Canvas canvas) {
        int i;
        float y;
        float x = this.chartStartX;
        for (i = MODE_DISPLAY; i <= this.log.hours_in_day_log_chart(); i += REMARK) {
            canvas.drawLine(x, this.chartStartY, x, this.chartEndY, this.logChartPaint);
            if (i == this.log.hours_in_day_log_chart()) {
                break;
            }
            int j = REMARK;
            while (j < RESET) {
                x += this.quarterWidth;

                float height = this.rowHeight * (j == VIOLATION ? 0.5f : 0.25f);
                for (int k = MODE_DISPLAY; k < this.rows; k += REMARK) {
                    y = (this.chartStartY + (((float) k) * this.rowHeight)) + (this.rowHeight - height);
                    canvas.drawLine(x, y, x, y + height, this.logChartPaint);
                }
                j += REMARK;
            }
            x += this.quarterWidth;
        }
        y = this.chartStartY;
        for (i = MODE_DISPLAY; i <= this.rows; i += REMARK) {
            canvas.drawLine(this.chartStartX, y, this.chartStartX + this.chartWidth, y, this.logChartPaint);
            y += this.rowHeight;
        }
    }

    private void drawChartLabels(Canvas canvas) {
        drawColumnLabels(canvas);
        drawRowLabels(canvas);
    }

    private void drawColumnLabels(Canvas canvas) {
        int i = MODE_DISPLAY;
        while (i <= this.log.hours_in_day_log_chart()) {
            canvas.drawText(this.log.is_daylight_savings_25() ? COLUMN_LABELS_25[i] : COLUMN_LABELS[i], this.chartStartX + (((float) i) * this.hourWidth), this.chartStartY - ((float) getResources().getDimensionPixelOffset(R.dimen.log_chart_flag_margin_top)), this.textColumnPaint);
            i += REMARK;
        }
    }

    private void drawRowLabels(Canvas canvas) {
        adjustTimes();
        int i = MODE_DISPLAY;
        while (i < this.rows) {
            String text = LEGENDS[i];
            Rect rect = new Rect();
            this.textRowPaint.getTextBounds(text, MODE_DISPLAY, text.length(), rect);
            this.textRowPaint.setTextAlign(Align.RIGHT);
            float y = ((this.chartStartY + (((float) i) * this.rowHeight)) + ((float) (rect.height() / VIOLATION))) + (this.rowHeight / 2.0f);
            canvas.drawText(text, this.chartStartX - this.chartLabelMargin, y, this.textRowPaint);
            this.textRowPaint.setTextAlign(Align.LEFT);
            if (this.totalTimes != null) {
                canvas.drawText(TimeUtil.getHoursDecimalFromSeconds((this.editStatus != -1 ? this.adjustTimes[i] : MODE_DISPLAY) + this.totalTimes[i]), (this.chartStartX + this.chartWidth) + this.chartLabelMargin, y, this.textRowPaint);
            }
            i += REMARK;
        }
    }

    private void drawLine(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint, boolean dotted) {
        if (!dotted) {
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        } else if (startY == stopY) {
            float x = startX;
            boolean drawDash = true;
            while (x < stopX) {
                if (drawDash) {
                    canvas.drawLine(x, startY, ((float) this.dashLength) + x < stopX ? x + ((float) this.dashLength) : stopX, stopY, paint);
                    x += (float) this.dashLength;
                } else {
                    x += (float) this.dashGap;
                }
                if (drawDash) {
                    drawDash = false;
                } else {
                    drawDash = true;
                }
            }
        }
    }

    private void drawStatusLines(Canvas canvas) {
        if (this.mode == SHORT_HAUL && this.editStatus == -1) {
            drawEditStatusLines(canvas, false);
        }
        if (this.mode == VIOLATION || this.mode == SHORT_HAUL) {
            this.statusLineHorizontalPaint.setColor(getResources().getColor(R.color.log_chart_event_line_dim));
            this.statusLineVerticalPaint.setColor(getResources().getColor(R.color.log_chart_event_line_dim));
        } else {
            this.statusLineHorizontalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
            this.statusLineVerticalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
        }
        float x = this.chartStartX;
        float lastX = x;
        float lastY = 0.0f;
        boolean daylightExtend = false;
        int i = MODE_DISPLAY;
        while (i < this.events.size()) {
            float startX = x;
            Event event = (Event) this.events.get(i);
            if (i == 0) {
                lastY = (float) getYFromStatus(event.type);
            }
            int seconds = event.duration;
            if (this.log.is_today() && i == this.events.size() - 1) {
                x = this.chartEndX;
            } else {
                x += ((float) seconds) * this.secondsWidth;
            }
            float y = (float) getYFromStatus(event.type);
            if (!(daylightExtend || adjustDaylightSavingsX(x) == 0.0f || (startX < this.chartStartX + (2.0f * this.hourWidth) && x == this.chartEndX))) {
                x += adjustDaylightSavingsX(x);
                daylightExtend = true;
            }
            int offset = ((int) this.statusLineHorizontalStrokeWidth) / VIOLATION;
            if (lastY < y) {
                offset = -offset;
            }
            canvas.drawLine(lastX, lastY + ((float) offset), lastX, y - ((float) offset), this.statusLineVerticalPaint);
            int color = (this.mode == VIOLATION || this.mode == SHORT_HAUL) ? R.color.log_chart_event_line_dim : R.color.log_chart_event_line;
            this.statusLineHorizontalPaint.setColor(getResources().getColor(color));
            drawLine(canvas, lastX, y, x, y, this.statusLineHorizontalPaint, !TextUtils.isEmpty(event.start_sds_eld_event_offline_id));
            event.start_x = lastX;
            event.end_x = x;
            lastX = x;
            lastY = y;
            i += REMARK;
        }
    }

    private void drawViolationLines(Canvas canvas) {
        if (this.drawViolationLines && this.violationsByType != null && this.mode != SHORT_HAUL && this.mode != VIOLATION) {
            int index = MODE_DISPLAY;
            for (String type : this.violationsByType.keySet()) {
                for (Violation violation : (List<Violation>) this.violationsByType.get(type)) {
                    long violationStartTime;
                    Long violationEndTime = Long.valueOf(violation.end_time_long() != null ? violation.end_time_long().longValue() : TimeUtil.currentTimeNearestInterval(this.eventInterval, true));
                    if (violation.start_time_long() > this.log.utc_start_time_long()) {
                        violationStartTime = violation.start_time_long();
                    } else {
                        violationStartTime = this.log.utc_start_time_long();
                    }
                    float violationStartX = this.chartStartX + (((float) getSecondDifference(this.log.utc_start_time_long(), violationStartTime)) * this.secondsWidth);
                    float violationEndX = Math.min(violationStartX + (((float) getSecondDifference(violationStartTime, violationEndTime.longValue())) * this.secondsWidth), this.chartEndX);
                    float y = (float) getYFromStatus(Event.DRIVING);
                    violationStartX += adjustDaylightSavingsX(violationStartX);
                    violationEndX += adjustDaylightSavingsX(violationEndX);
                    for (Event event : this.events) {
                        if (TextUtils.equals(event.type, Event.DRIVING) || TextUtils.equals(violation.type, Violation.CANADA_DAILY_BREAK_10) || TextUtils.equals(violation.type, Violation.CANADA_OIL_DAILY_BREAK_10)) {
                            float violationDrawStartX = 0.0f;
                            float violationDrawEndX = 0.0f;
                            if (violationStartX >= event.start_x && violationEndX <= event.end_x) {
                                violationDrawStartX = violationStartX;
                                violationDrawEndX = violationEndX;
                            } else if (violationStartX < event.start_x && violationEndX > event.start_x && violationEndX <= event.end_x) {
                                violationDrawStartX = event.start_x;
                                violationDrawEndX = violationEndX;
                            } else if (violationStartX >= event.start_x && violationStartX < event.end_x && violationEndX > event.end_x) {
                                violationDrawStartX = violationStartX;
                                violationDrawEndX = event.end_x;
                            } else if (violationStartX < event.start_x && violationEndX > event.end_x) {
                                violationDrawStartX = event.start_x;
                                violationDrawEndX = event.end_x;
                            }
                            if (violationDrawStartX > 0.0f && violationDrawEndX > 0.0f) {
                                int lineColor = (this.mode == VIOLATION || this.mode == SHORT_HAUL) ? R.color.log_chart_violation_line_dim : R.color.log_chart_violation_line;
                                this.violationLinePaint.setColor(getResources().getColor(lineColor));
                                if (TextUtils.equals(event.type, Event.DRIVING)) {
                                    this.violationLinePaint.setStyle(Style.STROKE);
                                    canvas.drawLine(violationDrawStartX, y, violationDrawEndX, y, this.violationLinePaint);
                                }
                                if (getHighlightedIndex(VIOLATION) == index) {
                                    this.violationLinePaint.setStyle(Style.FILL);
                                    this.violationLinePaint.setColor(getResources().getColor(R.color.log_chart_violation_region_highlight));
                                    canvas.drawRect(violationDrawStartX, this.chartStartY, violationDrawEndX, this.chartEndY, this.violationLinePaint);
                                }
                            }
                        }
                    }
                }
                index += REMARK;
            }
        }
    }

    private void drawEditStatusLines(Canvas canvas, boolean drawInBetweenStatusDials) {
        if (this.mode != 0 && this.mode != REMARK && this.mode != RESET && this.mode != MODE_EDIT_REMARK) {
            if (this.editStatus != -1 || !drawInBetweenStatusDials) {
                int i;
                Event event;
                float y;
                float regionStartX = 0.0f;
                float regionEndX = 0.0f;
                this.statusLineHorizontalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
                this.statusLineVerticalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
                if (this.mode == VIOLATION) {
                    Event currentEvent = (Event) this.events.get(getHighlightedIndex(MODE_DISPLAY));
                    regionStartX = this.startDial.x < currentEvent.start_x ? this.startDial.x : currentEvent.start_x;
                    regionEndX = this.endDial.x > currentEvent.end_x ? this.endDial.x : currentEvent.end_x;
                    if (regionEndX > this.chartEndX) {
                        regionEndX = this.chartEndX;
                    }
                } else if (this.mode == SHORT_HAUL) {
                    regionStartX = this.startDial.x;
                    regionEndX = this.endDial.x;
                }
                long startTime = this.startDial.time_long(true);
                long endTime = this.endDial.time_long(true);
                Event eventBeforeStart = null;
                Event eventBeforeEnd = null;
                Event eventAtStartDial = null;
                Event eventAtEndDial = null;
                Event eventAfterEnd = null;
                Event eventBeforeCurrent = null;
                Event eventAfterCurrent = null;
                long currentTime = TimeUtil.currentTimeNearestInterval(this.eventInterval, true);
                boolean currentDay = currentTime >= this.log.utc_start_time_long() && currentTime < this.log.utc_end_time_long();
                for (i = MODE_DISPLAY; i < this.events.size(); i += REMARK) {
                    event = (Event) this.events.get(i);
                    if (i == getHighlightedIndex(MODE_DISPLAY) - 1) {
                        eventBeforeCurrent = event;
                    }
                    if (i == getHighlightedIndex(MODE_DISPLAY) + REMARK) {
                        eventAfterCurrent = event;
                    }
                    if (event.time_long() == startTime) {
                        eventAtStartDial = event;
                    }
                    if (event.time_long() == endTime) {
                        eventAtEndDial = event;
                    }
                    if (event.time_long() < startTime) {
                        eventBeforeStart = event;
                    }
                    if (event.time_long() < endTime) {
                        eventBeforeEnd = event;
                    }
                    if (eventAfterEnd == null && event.time_long() > endTime) {
                        eventAfterEnd = event;
                    }
                }
                if (this.mode != VIOLATION || this.startDial.x <= this.editEvent.start_x) {
                    if (startTime != this.log.utc_start_time_long() && (eventBeforeStart == null || Event.get_status_index(eventBeforeStart.type) != this.editStatus)) {
                        if (eventBeforeStart != null) {
                            canvas.drawLine(this.startDial.x, (float) getYFromStatus(eventBeforeStart.type), this.startDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                        } else if (eventAtStartDial != null) {
                            canvas.drawLine(this.startDial.x, (float) getYFromStatus(eventAtStartDial.type), this.startDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                        }
                    }
                } else if (eventBeforeCurrent != null) {
                    y = (float) getYFromStatus(eventBeforeCurrent.type);
                    canvas.drawLine(eventBeforeCurrent.end_x, y, this.startDial.x, y, this.statusLineHorizontalPaint);
                    canvas.drawLine(this.startDial.x, y, this.startDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                }
                if (this.mode != VIOLATION || this.endDial.x >= this.editEvent.end_x) {
                    if (endTime != this.log.utc_end_time_long() && ((eventAtEndDial == null || Event.get_status_index(eventAtEndDial.type) != this.editStatus) && !((eventAtEndDial == null && eventBeforeEnd != null && Event.get_status_index(eventBeforeEnd.type) == this.editStatus) || (currentDay && regionEndX == this.chartEndX)))) {
                        if (eventAtEndDial != null) {
                            canvas.drawLine(this.endDial.x, (float) getYFromStatus(eventAtEndDial.type), this.endDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                        } else if (eventBeforeEnd != null) {
                            canvas.drawLine(this.endDial.x, (float) getYFromStatus(eventBeforeEnd.type), this.endDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                        }
                    }
                } else if (eventAfterCurrent != null) {
                    y = (float) getYFromStatus(eventAfterCurrent.type);
                    canvas.drawLine(this.endDial.x, y, eventAfterCurrent.start_x, y, this.statusLineHorizontalPaint);
                    canvas.drawLine(this.endDial.x, y, this.endDial.x, (float) getYFromStatus(Event.get_status(this.editStatus)), this.statusLineVerticalPaint);
                }
                if (drawInBetweenStatusDials) {
                    y = (float) getYFromStatus(Event.get_status(this.editStatus));
                    boolean dash = this.mode == VIOLATION && this.log.eld_enabled()  && !TextUtils.equals(Event.get_status(this.editStatus), Event.DRIVING);
                    drawLine(canvas, this.startDial.x, y, this.endDial.x, y, this.statusLineHorizontalPaint, dash);
                }
                for (i = MODE_DISPLAY; i < this.events.size(); i += REMARK) {
                    float f;
                    float f2;
                    Paint paint;
                    boolean z;
                    event = (Event) this.events.get(i);
                    y = (float) getYFromStatus(event.type);
                    Event nextEvent = null;
                    int nextEventY = MODE_DISPLAY;
                    if (i + REMARK < this.events.size()) {
                        nextEvent = (Event) this.events.get(i + REMARK);
                        nextEventY = getYFromStatus(nextEvent.type);
                    }
                    this.statusLineHorizontalPaint.setColor(getResources().getColor(R.color.log_chart_event_line));
                    if (event.start_x < regionStartX) {
                        f = event.start_x;
                        if (event.end_x < regionStartX) {
                            f2 = event.end_x;
                        } else {
                            f2 = regionStartX;
                        }
                        paint = this.statusLineHorizontalPaint;
                        if (TextUtils.isEmpty(event.start_sds_eld_event_offline_id)) {
                            z = false;
                        } else {
                            z = true;
                        }
                        drawLine(canvas, f, y, f2, y, paint, z);
                        if (nextEvent != null && nextEvent.start_x < regionStartX) {
                            canvas.drawLine(event.end_x, y, event.end_x, (float) nextEventY, this.statusLineVerticalPaint);
                        }
                    }
                    if (event.end_x > regionEndX) {
                        if ((currentDay && this.startDial.x == this.chartEndX) || this.endDial.x == this.chartEndX) {
                            y = (float) getYFromStatus(Event.get_status(this.editStatus));
                            if (event.start_x > regionEndX) {
                                f = event.start_x;
                            } else {
                                f = regionEndX;
                            }
                            f2 = event.end_x;
                            paint = this.statusLineHorizontalPaint;
                            if (TextUtils.isEmpty(event.start_sds_eld_event_offline_id)) {
                                z = false;
                            } else {
                                z = true;
                            }
                            drawLine(canvas, f, y, f2, y, paint, z);
                        } else {
                            drawLine(canvas, event.start_x > regionEndX ? event.start_x : regionEndX, y, event.end_x, y, this.statusLineHorizontalPaint, !TextUtils.isEmpty(event.start_sds_eld_event_offline_id));
                            if (nextEvent != null) {
                                canvas.drawLine(event.end_x, y, event.end_x, (float) nextEventY, this.statusLineVerticalPaint);
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawRemarks(Canvas canvas, boolean drawHighlighted) {
        if (this.mode == REMARK || this.mode == 0) {
            int i = MODE_DISPLAY;
            while (i < this.remarks.size()) {
                if (!drawHighlighted || i == getHighlightedIndex(REMARK)) {
                    Bitmap icon = BitmapHolder.getBitmap(getResources(), MODE_DISPLAY, (int) (this.chartStartY * 0.65f), R.drawable.arrow);
                    float x = getX(((Remark) this.remarks.get(i)).time_long());
                    x += adjustDaylightSavingsX(x);
                    if (i == getHighlightedIndex(REMARK) && this.mode == REMARK) {
                        this.remarkPaint.setStrokeWidth(this.remarkLineBoldStrokeWidth);
                        this.remarkPaint.setColor(getResources().getColor(R.color.log_chart_remark_line_selected));
                        canvas.drawBitmap(icon, x - ((float) (icon.getWidth() / VIOLATION)), this.chartStartY - ((float) icon.getHeight()), this.remarkPaint);
                        canvas.drawLine(x, this.chartStartY, x, this.chartEndY, this.remarkPaint);
                    } else {
                        this.remarkPaint.setStrokeWidth(this.remarkLineStrokeWidth);
                        this.remarkPaint.setColor(getResources().getColor(R.color.log_chart_remark_line));
                        canvas.drawLine(x, this.chartStartY, x, this.chartEndY, this.remarkPaint);
                    }
                }
                i += REMARK;
            }
        }
    }

    private void drawCycleResets(Canvas canvas) {
        if (this.mode == REMARK || this.mode == 0) {
            for (int i = MODE_DISPLAY; i < this.cycleResets.size(); i += REMARK) {
                CycleReset cycleReset = (CycleReset) this.cycleResets.get(i);
                if (TextUtils.isEmpty(cycleReset.type) || TextUtils.equals(cycleReset.status, "claimed")) {
                    Bitmap icon = BitmapHolder.getBitmap(getResources(), MODE_DISPLAY, (int) (this.chartStartY * 0.65f), ((CycleResetMetaData) CycleReset.CYCLE_RESET_TYPES.get(cycleReset.type)).drawable_id);
                    float x = this.chartStartX + (((float) (getSecondDifference(this.log.utc_start_time_long(), cycleReset.end_time_long()) / this.eventInterval)) * this.eventIntervalWidth);
                    x += adjustDaylightSavingsX(x);
                    if (getHighlightedIndex(RESET) == i) {
                        this.paint.setColor(getResources().getColor(R.color.log_chart_reset_line_selected));
                        this.paint.setStrokeWidth(this.remarkLineBoldStrokeWidth);
                        canvas.drawBitmap(icon, x - ((float) (icon.getWidth() / VIOLATION)), this.chartStartY - ((float) icon.getHeight()), this.paint);
                    } else {
                        this.paint.setColor(getResources().getColor(R.color.log_chart_reset_line));
                        this.paint.setStrokeWidth(this.remarkLineStrokeWidth);
                    }
                    this.paint.setStyle(Style.FILL);
                    this.paint.setTextAlign(Align.CENTER);
                    canvas.drawLine(x, this.chartStartY, x, this.chartEndY, this.paint);
                    new Point().set((int) x, (int) this.chartStartY);
                }
            }
        }
    }

    private void drawHighlightedSegment(Canvas canvas) {
        Canvas canvas2;
        switch (this.mode) {
            case REMARK /*1*/:
                int index = getHighlightedIndex(MODE_DISPLAY);
                if (index >= 0 && index < this.events.size()) {
                    Event event = (Event) this.events.get(getHighlightedIndex(MODE_DISPLAY));
                    this.paint.setStyle(Style.FILL_AND_STROKE);
                    this.paint.setColor(getResources().getColor(R.color.log_chart_event_region_highlight));
                    canvas2 = canvas;
                    canvas2.drawRect(event.start_x, this.logChartLineStrokeWidth + this.chartStartY, event.end_x, this.chartEndY - this.logChartLineStrokeWidth, this.paint);
                    return;
                }
                return;
            case VIOLATION /*2*/:
            case SHORT_HAUL /*3*/:
                this.paint.setStyle(Style.FILL_AND_STROKE);
                this.paint.setColor(getResources().getColor(R.color.log_chart_event_region_highlight));
                canvas2 = canvas;
                canvas2.drawRect(this.startDial.x, this.logChartLineStrokeWidth + this.chartStartY, this.endDial.x, this.chartEndY - this.logChartLineStrokeWidth, this.paint);
                this.startDial.draw(canvas, MODE_DISPLAY);
                this.endDial.draw(canvas, REMARK);
                return;
            case RESET /*4*/:
            case MODE_EDIT_REMARK /*5*/:
                this.startDial.draw(canvas, VIOLATION);
                return;
            default:
                return;
        }
    }

    private void adjustTimes() {
        this.adjustTimes = new int[MODE_EDIT_REMARK];
        this.startDial.edits_driving = false;
        this.endDial.edits_driving = false;
        if (this.mode == SHORT_HAUL || this.mode == VIOLATION) {
            int[] iArr;
            int i;
            long editStart = this.editEvent != null ? this.editEvent.time_long() : 0;
            long editEnd = this.editEvent != null ? this.editEvent.time_long() + ((long) this.editEvent.duration) : 0;
            long start = this.log.utc_start_time_long() + ((long) this.startDial.get_seconds_offset());
            long end = this.log.utc_start_time_long() + ((long) this.endDial.get_seconds_offset());
            long startSpan = Math.min(start, editStart);
            long endSpan = Math.max(end, editEnd);
            Event editPrevious = null;
            Event editNext = null;
            if (this.log.is_daylight_savings_23()) {
                long t2h = this.log.utc_start_time_long() + 7200;
                double startOffset = (double) (((float) (start - this.log.utc_start_time_long())) / 3600.0f);
                double endOffset = (double) (((float) (end - this.log.utc_start_time_long())) / 3600.0f);

                if (start >= t2h) {
                    start = Math.max(t2h, start - 3600);
                }
                if (end >= t2h) {
                    end = Math.max(t2h, end - 3600);
                }
                startOffset = (double) (((float) (start - this.log.utc_start_time_long())) / 3600.0f);
                endOffset = (double) (((float) (end - this.log.utc_start_time_long())) / 3600.0f);

            }
            for (int i2 = MODE_DISPLAY; i2 < this.events.size(); i2 += REMARK) {
                Event event = (Event) this.events.get(i2);
                Event previous = i2 + -1 >= 0 ? (Event) this.events.get(i2 - 1) : null;
                Event next = i2 + REMARK < this.events.size() ? (Event) this.events.get(i2 + REMARK) : null;
                String str = (this.rows == RESET && TextUtils.equals(event.type, Event.WAITING)) ? Event.OFF_DUTY : event.type;
                int index = Event.get_status_index(str);
                if (this.mode == VIOLATION) {
                    if (event.equals(this.editEvent)) {
                        editPrevious = previous;
                        editNext = next;
                    }
//                    if (LogsController.getInstance().isEventDrivingOrSDS(event) && startSpan < event.time_long() && endSpan > event.time_long()) {
                        this.startDial.edits_driving = true;
                        this.endDial.edits_driving = true;
//                    }
                }
                if (start >= event.time_long() && (next == null || start < next.time_long())) {
//                    if (!event.equals(this.editEvent) && LogsController.getInstance().isEventDrivingOrSDS(event)) {
                        this.startDial.edits_driving = true;
//                    }
                    if (next == null || end <= next.time_long()) {
                        if (index != -1) {
                            iArr = this.adjustTimes;
                            iArr[index] = (int) (((long) iArr[index]) - (end - start));
                        }
//                        if (!event.equals(this.editEvent) && LogsController.getInstance().isEventDrivingOrSDS(event)) {
                            this.endDial.edits_driving = true;
//                        }
                    } else if (index != -1) {
                        iArr = this.adjustTimes;
                        iArr[index] = (int) (((long) iArr[index]) - (next.time_long() - start));
                    }
                }
                if (start < event.time_long() && end > event.time_long()) {
                    if (next == null || end <= next.time_long()) {
                        if (index != -1) {
                            iArr = this.adjustTimes;
                            iArr[index] = (int) (((long) iArr[index]) - (end - event.time_long()));
                        }
//                        if (LogsController.getInstance().isEventDrivingOrSDS(event)) {
                            this.endDial.edits_driving = true;
//                        }
                    } else {
                        if (index != -1) {
                            iArr = this.adjustTimes;
                            iArr[index] = (int) (((long) iArr[index]) - (next.time_long() - event.time_long()));
                        }
//                        if (LogsController.getInstance().isEventDrivingOrSDS(event)) {
                            this.startDial.edits_driving = true;
                            this.endDial.edits_driving = true;
//                        }
                    }
                }
            }
            if (this.mode == VIOLATION) {
                int[] iArr2;
                int editEventIndex = Event.get_status_index(this.editEvent.type);
                if (editPrevious != null && start > editStart) {
                    iArr = this.adjustTimes;
                    i = Event.get_status_index(editPrevious.type);
                    iArr[i] = (int) (((long) iArr[i]) + (start - editStart));
                    if (editEventIndex != -1) {
                        iArr2 = this.adjustTimes;
                        iArr2[editEventIndex] = (int) (((long) iArr2[editEventIndex]) - (start < editEnd ? start - editStart : editEnd - editStart));
                    }
                }
                if (editNext != null && end < editEnd) {
                    iArr = this.adjustTimes;
                    i = Event.get_status_index(editNext.type);
                    iArr[i] = (int) (((long) iArr[i]) + (editNext.time_long() - end));
                    if (editEventIndex != -1) {
                        iArr2 = this.adjustTimes;
                        iArr2[editEventIndex] = (int) (((long) iArr2[editEventIndex]) - (end > editStart ? editEnd - end : editEnd - editStart));
                    }
                }
            }
            if (this.editStatus != -1) {
                iArr = this.adjustTimes;
                i = this.editStatus;
                iArr[i] = (int) (((long) iArr[i]) + (end - start));
            }
        }
    }

    private float adjustDaylightSavingsX(float x) {
        return (!this.log.is_daylight_savings_23() || x < this.chartStartX + (2.0f * this.hourWidth)) ? 0.0f : this.hourWidth;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.events == null || this.events.size() == 0) {
            return true;
        }
        if (this.mode == 0) {
            return false;
        }
        int action = MotionEventCompat.getActionMasked(motionEvent);
        int pointerIndex = MotionEventCompat.getActionIndex(motionEvent);
        int pointerID = motionEvent.getPointerId(pointerIndex);
        float x = MotionEventCompat.getX(motionEvent, pointerIndex);
        float y = MotionEventCompat.getY(motionEvent, pointerIndex);
        float intervalWidth = getTouchIntervalWidth();
        switch (this.mode) {
            case REMARK /*1*/:
                if (x > this.chartStartX && x < this.chartStartX + this.chartWidth && y > this.chartStartY && y < this.chartEndY) {
                    int lastIndex;
                    boolean eventSelected = false;
                    int i = MODE_DISPLAY;
                    while (i < this.events.size()) {
                        Event event = (Event) this.events.get(i);
                        if (x < event.start_x || x > event.end_x) {
                            i += REMARK;
                        } else {
                            eventSelected = true;
                            if (i != getHighlightedIndex(MODE_DISPLAY)) {

                                setHighlightedItem(MODE_DISPLAY, i);
                                this.eventSelectedCallback.eventSelected(i);
                            }
                            lastIndex = this.events.size() - 1;
                            if (!(eventSelected || lastIndex == getHighlightedIndex(MODE_DISPLAY))) {

                                setHighlightedItem(MODE_DISPLAY, lastIndex);
                                this.eventSelectedCallback.eventSelected(lastIndex);
                            }
                        }
                    }
                    lastIndex = this.events.size() - 1;

                    setHighlightedItem(MODE_DISPLAY, lastIndex);
                    this.eventSelectedCallback.eventSelected(lastIndex);
                    break;
                }
                setHighlightedItem(MODE_DISPLAY, -1);
                this.eventSelectedCallback.eventSelected(-1);
                invalidate();
                break;
            case VIOLATION /*2*/:
            case SHORT_HAUL /*3*/:
            case RESET /*4*/:
            case MODE_EDIT_REMARK /*5*/:
                switch (action) {
                    case MODE_DISPLAY /*0*/:
                    case MODE_EDIT_REMARK /*5*/:
                        if (!this.startDial.touch_enabled || !Util.isPointInCircle(x, y, this.startDial.x, this.startDial.y, this.touchCircleRadius) || (this.endDial.touch_enabled && Math.abs(x - this.startDial.x) >= Math.abs(x - this.endDial.x))) {
                            if (this.endDial.touch_enabled && Util.isPointInCircle(x, y, this.endDial.x, this.endDial.y, this.touchCircleRadius)) {
                                this.endDialPointerID = pointerID;
                                this.endDial.anchor_x = x;
                                this.endDial.origin_x = this.endDial.x;
                                break;
                            }
                        }
                        this.startDialPointerID = pointerID;
                        this.startDial.anchor_x = x;
                        this.startDial.origin_x = this.startDial.x;
                        break;

                    case REMARK:
                        if (this.mode == VIOLATION) {
                            this.eventSelectedCallback.onTouch();
                        }
                        float resultX;
                        if (pointerID == this.startDialPointerID) {
                            resultX = getX(getSeconds(this.startDial.x, true));
                            if (!this.endDial.visible || resultX < this.endDial.x) {
                                this.startDial.set_x(resultX);
                            } else {
                                this.startDial.set_x(this.endDial.x - intervalWidth);
                            }
                            this.startDialPointerID = -1;
                        } else if (pointerID == this.endDialPointerID) {
                            if (this.endDial.x != this.chartEndX) {
                                resultX = getX(getSeconds(this.endDial.x, true));
                                if (!this.startDial.visible || resultX > this.startDial.x) {
                                    this.endDial.set_x(resultX);
                                } else {
                                    this.endDial.set_x(this.startDial.x + intervalWidth);
                                }
                            }
                            this.endDialPointerID = -1;
                        }
                        invalidate();
                        break;
                    case VIOLATION /*2*/:
                        int pointerCount = motionEvent.getPointerCount();
                        for (int index = MODE_DISPLAY; index < pointerCount; index += REMARK) {
                            float adjustedX;
                            int id = motionEvent.getPointerId(index);
                            float moveX = MotionEventCompat.getX(motionEvent, index);
                            if (id == this.startDialPointerID && this.startDial.touch_enabled) {
                                adjustedX = this.startDial.origin_x - (this.startDial.anchor_x - moveX);
                                if (this.endDial.visible && adjustedX >= this.endDial.x) {
                                    this.startDial.set_x(this.endDial.x - intervalWidth);
                                } else if (adjustedX < this.chartStartX) {
                                    this.startDial.set_x(this.chartStartX);
                                } else if (adjustedX >= this.chartEndX - intervalWidth) {
                                    this.startDial.set_x(this.chartEndX - intervalWidth);
                                } else {
                                    this.startDial.set_x(getX(getSeconds(adjustedX, true)));
                                }
                                if (getSeconds(this.startDial.x) % (this.MINUTE * 15) == 0 && this.startDial.last_seconds != getSeconds(this.startDial.x)) {
                                    if (System.currentTimeMillis() - this.lastHapticFeedbackTime > 250) {
                                        performHapticFeedback(SHORT_HAUL);
                                        this.lastHapticFeedbackTime = System.currentTimeMillis();
                                    }
                                    this.startDial.last_seconds = getSeconds(this.startDial.x);
                                }
                            }
                            if (id == this.endDialPointerID && this.endDial.touch_enabled) {
                                adjustedX = this.endDial.origin_x - (this.endDial.anchor_x - moveX);
                                if (this.startDial.visible && adjustedX <= this.startDial.x) {
                                    this.endDial.set_x(this.startDial.x + intervalWidth);
                                } else if (adjustedX <= this.chartStartX + intervalWidth) {
                                    this.endDial.set_x(this.chartStartX + intervalWidth);
                                } else if (adjustedX > this.chartEndX) {
                                    this.endDial.set_x(this.chartEndX);
                                } else {
                                    this.endDial.set_x(getX(getSeconds(adjustedX, true)));
                                }
                                if (getSeconds(this.endDial.x) % (this.MINUTE * 15) == 0 && this.endDial.last_seconds != getSeconds(this.endDial.x)) {
                                    if (System.currentTimeMillis() - this.lastHapticFeedbackTime > 250) {
                                        performHapticFeedback(SHORT_HAUL);
                                        this.lastHapticFeedbackTime = System.currentTimeMillis();
                                    }
                                    this.endDial.last_seconds = getSeconds(this.endDial.x);
                                }
                            }
                        }
                        invalidate();
                        break;
                    default:
                        break;
                }
        }
        return true;
    }

    public void setHighlightedItem(int type, int index) {
        for (int i = MODE_DISPLAY; i < this.highlightedItems.size(); i += REMARK) {
            this.highlightedItems.put(this.highlightedItems.keyAt(i), Integer.valueOf(-1));
        }
        this.highlightedItems.put(type, Integer.valueOf(index));
        postInvalidate();
        requestLayout();
        refreshDrawableState();
    }

    public int getHighlightedIndex(int type) {
        return this.highlightedItems.get(type) != null ? ((Integer) this.highlightedItems.get(type)).intValue() : -1;
    }

    public void setEditStatus(int status) {
        this.editStatus = status;
        postInvalidate();
    }

    public void setMode(int mode) {

        this.mode = mode;
        this.drawViolationLines = true;
        Dial dial;
        long now;
        int secondDifference;
        switch (mode) {
            case MODE_DISPLAY /*0*/:
            case REMARK /*1*/:
                if (this.startDial != null) {
                    this.startDial.time_long = 0;
                }
                if (this.endDial != null) {
                    this.endDial.time_long = 0;
                    break;
                }
                break;
            case VIOLATION /*2*/:
                this.editEvent = (Event) this.events.get(getHighlightedIndex(MODE_DISPLAY));
                dial = this.startDial;
                this.endDial.visible = true;
                dial.visible = true;
                this.startDial.set(this.editEvent.start_x, this.chartEndY);
                this.endDial.set(this.editEvent.end_x, this.chartEndY);
                Dial dial2 = this.startDial;
                boolean z = (this.events.size() == REMARK || getHighlightedIndex(MODE_DISPLAY) == 0) ? false : true;
                dial2.touch_enabled = z;
                dial2 = this.endDial;
                z = (this.events.size() == REMARK || getHighlightedIndex(MODE_DISPLAY) == this.events.size() - 1) ? false : true;
                dial2.touch_enabled = z;
            /*    if (this.log.eld_enabled()) {
                    if (!LogsController.getInstance().isEventDrivingOrSDS(this.editEvent)) {
                        Event editPrevious = null;
                        Event editNext = null;
                        for (int i = MODE_DISPLAY; i < this.events.size(); i += REMARK) {
                            Event event = (Event) this.events.get(i);
                            Event previous = i + -1 >= 0 ? (Event) this.events.get(i - 1) : null;
                            Event next = i + REMARK < this.events.size() ? (Event) this.events.get(i + REMARK) : null;
                            if (event.equals(this.editEvent)) {
                                editPrevious = previous;
                                editNext = next;
                                if (editNext != null && TextUtils.equals(editNext.type, Event.DRIVING)) {
                                    this.endDial.touch_enabled = false;
                                }
                                if (editPrevious != null && TextUtils.equals(editPrevious.type, Event.DRIVING)) {
                                    this.startDial.touch_enabled = false;
                                    break;
                                }
                            }
                        }
                        this.endDial.touch_enabled = false;
                        this.startDial.touch_enabled = false;
                        break;
                    }
                    this.startDial.touch_enabled = false;
                    this.endDial.touch_enabled = false;
                    break;
                }*/
                break;
            case SHORT_HAUL /*3*/:
                int left;
                int right;
                now = TimeUtil.currentTimeNearestInterval(this.eventInterval, false);
                if (now < this.log.utc_start_time_long() || now >= this.log.utc_end_time_long()) {
                    secondDifference = getSecondDifference(this.log.utc_start_time_long(), this.log.utc_end_time_long());
                    left = (secondDifference / VIOLATION) - (this.HOUR * VIOLATION);
                    right = (secondDifference / VIOLATION) + (this.HOUR * VIOLATION);
                } else {
                    secondDifference = getSecondDifference(this.log.utc_start_time_long(), now);
                    if (secondDifference >= this.HOUR * 6) {
                        left = (secondDifference / VIOLATION) - (this.HOUR * VIOLATION);
                        right = (secondDifference / VIOLATION) + (this.HOUR * VIOLATION);
                    } else if (secondDifference >= this.HOUR * RESET) {
                        left = (secondDifference / VIOLATION) - this.HOUR;
                        right = (secondDifference / VIOLATION) + this.HOUR;
                    } else if (secondDifference >= this.HOUR * VIOLATION) {
                        left = (secondDifference / VIOLATION) - (this.MINUTE * 30);
                        right = (secondDifference / VIOLATION) + (this.MINUTE * 30);
                    } else {
                        if (secondDifference >= this.HOUR) {
                            left = (secondDifference / VIOLATION) - (this.MINUTE * 15);
                            right = (secondDifference / VIOLATION) + (this.MINUTE * 15);
                        } else {
                            left = MODE_DISPLAY;
                            right = secondDifference > this.MINUTE * 15 ? secondDifference : this.MINUTE * 15;
                        }
                    }
                }
                int i2 = (!this.log.is_daylight_savings_23() || left < this.HOUR * VIOLATION) ? MODE_DISPLAY : this.MINUTE * 30;
                left += i2;
                i2 = (!this.log.is_daylight_savings_23() || right < this.HOUR * VIOLATION) ? MODE_DISPLAY : this.MINUTE * 30;
                right += i2;
                this.startDial.set(getX(getSeconds(getX(left), true)), this.chartEndY);
                this.endDial.set(getX(getSeconds(getX(right), true)), this.chartEndY);
                dial = this.startDial;
                this.endDial.visible = true;
                dial.visible = true;
                dial = this.startDial;
                this.endDial.touch_enabled = true;
                dial.touch_enabled = true;
                break;
            case RESET /*4*/:
                now = TimeUtil.currentTimeNearestInterval(this.eventInterval, false);
                if (now < this.log.utc_start_time_long() || now >= this.log.utc_end_time_long()) {
                    secondDifference = getSecondDifference(this.log.utc_start_time_long(), this.log.utc_end_time_long());
                } else {
                    secondDifference = getSecondDifference(this.log.utc_start_time_long(), now);
                }
                this.startDial.set(getX(secondDifference / VIOLATION), this.chartEndY);
                dial = this.startDial;
                this.startDial.touch_enabled = true;
                dial.visible = true;
                dial = this.endDial;
                this.endDial.touch_enabled = false;
                dial.visible = false;
                this.startDial.set(getX(getSeconds(this.startDial.x)), this.startDial.y);
                break;
            case MODE_EDIT_REMARK /*5*/:
                float x = getX(((Remark) this.remarks.get(getHighlightedIndex(REMARK))).time_long());
                this.startDial.set(x + adjustDaylightSavingsX(x), this.chartEndY);
                dial = this.startDial;
                this.startDial.touch_enabled = true;
                dial.visible = true;
                dial = this.endDial;
                this.endDial.touch_enabled = false;
                dial.visible = false;
                break;
        }
        postInvalidate();
        requestLayout();
    }

    public int getMode() {
        return this.mode;
    }

    public float getX(int seconds) {
        return this.chartStartX + (this.secondsWidth * ((float) seconds));
    }

    public int getSeconds() {
        return getSeconds(this.chartEndX);
    }

    public boolean isDialInDaylightSavingsRollover() {
        float x_2am = this.chartStartX + (2.0f * this.hourWidth);
        float x_3am = this.chartStartX + (3.0f * this.hourWidth);
        return this.log.is_daylight_savings_23() && ((this.startDial.touch_enabled && this.startDial.x > x_2am && this.startDial.x < x_3am) || ((this.endDial.touch_enabled && this.endDial.x > x_2am && this.endDial.x < x_3am) || (this.startDial.touch_enabled && this.endDial.touch_enabled && this.startDial.x == x_2am && this.endDial.x == x_3am)));
    }

    private float getX(long time) {
        float interval = (this.mode == VIOLATION || this.mode == SHORT_HAUL) ? (float) this.eventInterval : (float) this.remarkInterval;
        float intervalWidth = (this.mode == VIOLATION || this.mode == SHORT_HAUL) ? this.eventIntervalWidth : this.remarkIntervalWidth;
        return this.chartStartX + ((((float) getSecondDifference(this.log.utc_start_time_long(), time)) / interval) * intervalWidth);
    }

    private int getYFromStatus(String status) {
        if (this.rows == RESET && TextUtils.equals(status, Event.WAITING)) {
            status = Event.OFF_DUTY;
        }
        return (int) ((((double) this.chartStartY) + (((double) this.rowHeight) / 2.0d)) + ((double) (((float) Event.get_status_index(status)) * this.rowHeight)));
    }

    private int getTouchInterval() {
        return (this.log.eld_enabled() || this.user.minute_logs || this.mode == MODE_EDIT_REMARK || this.mode == RESET) ? this.MINUTE : this.MINUTE * 15;
    }

    private float getTouchIntervalWidth() {
        return ((float) getTouchInterval()) * this.secondsWidth;
    }

    private int getSeconds(float x) {
        return getSeconds(x, false);
    }

    private int getSeconds(float x, boolean fromTouch) {
        boolean event = this.mode == VIOLATION || this.mode == SHORT_HAUL;
        int interval = fromTouch ? getTouchInterval() : event ? this.eventInterval : this.remarkInterval;
        float intervalWidth = fromTouch ? getTouchIntervalWidth() : event ? this.eventIntervalWidth : this.remarkIntervalWidth;
        int seconds = Math.round(((x - this.chartStartX) / intervalWidth) * ((float) interval));
        if (interval == REMARK) {
            return seconds;
        }
        int remainder = seconds % interval;
        seconds -= remainder;
        if (remainder > interval / VIOLATION) {
            return seconds + interval;
        }
        return seconds;
    }

    private int getSecondDifference(long start, long end) {
        return (int) (end - start);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) {
            setMeasuredDimension(widthMeasureSpec, 50);
            return;
        }
        int width;
        int height;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        initSizes(widthSize, heightSize);
        int desiredWidth = (int) this.viewWidth;
        int desiredHeight = (int) this.viewHeight;
        if (widthMode == 1073741824) {
            width = widthSize;
        } else if (widthMode == Integer.MIN_VALUE) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }
        if (heightMode == 1073741824) {
            height = heightSize;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }
        setMeasuredDimension(width, height);
    }
}
