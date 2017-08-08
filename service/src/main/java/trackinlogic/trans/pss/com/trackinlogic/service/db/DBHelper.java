package trackinlogic.trans.pss.com.trackinlogic.service.db;

/**
 * Created by Sekhar Madhiyazhagan on 8/5/2017.
 */

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

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Trackin.db";
    public static final String CONTACTS_TABLE_NAME = "user";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_COUNTRY = "country";
    public static final String CONTACTS_COLUMN_STATE = "state";
    public static final String CONTACTS_COLUMN_ZIP = "zip";

     Scheduler ioScheduler;
    public DBHelper(Context context,  Scheduler ioScheduler) {
        super(context, DATABASE_NAME , null, 1);
        this.ioScheduler = ioScheduler;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table  "+CONTACTS_TABLE_NAME +
                        "("+CONTACTS_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+CONTACTS_COLUMN_NAME+" text,"+CONTACTS_COLUMN_STREET+" text,"+CONTACTS_COLUMN_CITY+" text,"+CONTACTS_COLUMN_COUNTRY+" text,"+CONTACTS_COLUMN_STATE+" text,"+CONTACTS_COLUMN_ZIP+" integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public Observable<Boolean> insertContact(Integer id, String name, String country, String state, String street, String city, int zip) {
        SQLiteDatabase db = this.getWritableDatabase();
       return Observable.create(new Observable.OnSubscribe<Boolean>() {
           @Override
           public void call(Subscriber<? super Boolean> subscriber) {
               ContentValues contentValues = new ContentValues();
               contentValues.put(CONTACTS_COLUMN_NAME, name);
               contentValues.put(CONTACTS_COLUMN_STREET, street);
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

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
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