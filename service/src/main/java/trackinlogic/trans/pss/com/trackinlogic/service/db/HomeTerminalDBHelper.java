package trackinlogic.trans.pss.com.trackinlogic.service.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

import static trackinlogic.trans.pss.com.trackinlogic.service.db.CarrierAddressDBHelper.CONTACTS_COLUMN_NAME;
import static trackinlogic.trans.pss.com.trackinlogic.service.db.CarrierAddressDBHelper.CONTACTS_COLUMN_STREET;

/**
 * Created by Sekhar Madhiyazhagan on 8/15/2017.
 */

public class HomeTerminalDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Trackin.db";
    public static final String CONTACTS_TABLE_NAME = "Homeaddress";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_STREET2 = "street2";
    public static final String CONTACTS_COLUMN_STREET1 = "street";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_COUNTRY = "country";
    public static final String CONTACTS_COLUMN_STATE = "state";
    public static final String CONTACTS_COLUMN_ZIP = "zip";
    private Context mContext;

    Scheduler ioScheduler;
    public HomeTerminalDBHelper(Context context, Scheduler ioScheduler) {
        super(context, DATABASE_NAME , null, 1);
        this.ioScheduler = ioScheduler;
        this.mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table  "+CONTACTS_TABLE_NAME +
                        "("+CONTACTS_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+CONTACTS_COLUMN_STREET1+" text,"+CONTACTS_COLUMN_STREET2+" text,"+CONTACTS_COLUMN_CITY+" text,"+CONTACTS_COLUMN_COUNTRY+" text,"+CONTACTS_COLUMN_STATE+" text,"+CONTACTS_COLUMN_ZIP+" integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public Observable<Boolean> insertHomeAddress(String street1, String country, String state, String street2, String city, int zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(CONTACTS_COLUMN_STREET2, street2);
                contentValues.put(CONTACTS_COLUMN_STREET1, street1);
                contentValues.put(CONTACTS_COLUMN_CITY, city);
                contentValues.put(CONTACTS_COLUMN_COUNTRY, country);
                contentValues.put(CONTACTS_COLUMN_STATE, state);
                contentValues.put(CONTACTS_COLUMN_ZIP,zip);
                db.insert(CONTACTS_TABLE_NAME, null, contentValues);
                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(ioScheduler);
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME+" where name='"+name+"'", null );
        return res;
    }

    public Observable<Integer> numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
                subscriber.onNext(numRows);
                subscriber.onCompleted();
            }
        }).subscribeOn(ioScheduler);


    }

    public boolean updateContact (Integer id, String name, String country, String state, String street,String city,int zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, name);
        contentValues.put(CONTACTS_COLUMN_STREET, street);
        contentValues.put(CONTACTS_COLUMN_CITY, city);
        contentValues.put(CONTACTS_COLUMN_COUNTRY, country);
        contentValues.put(CONTACTS_COLUMN_STATE, state);
        contentValues.put(CONTACTS_COLUMN_ZIP,zip);
        db.update(CONTACTS_TABLE_NAME, contentValues, "name = ? ", new String[] { name } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}