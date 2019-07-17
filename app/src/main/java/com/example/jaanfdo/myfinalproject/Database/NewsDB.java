package com.example.jaanfdo.myfinalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaanfdo.myfinalproject.BusinessClass.EventsBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.NewsBL;

/**
 * Created by jaanfdo on 6/28/2017.
 */

public class NewsDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SchoolISS.db";
    private static final String TABLE_NAME = "news";

    public NewsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table " +  TABLE_NAME + " (id INTEGER PRIMARY KEY autoincrement, news text not null, date DateTime not null,time Time not null, owner Text not null)");

        String InsertSql = "INSERT INTO "+TABLE_NAME+" (news, date, time, owner)VALUES"+
                "('Angular','2017-05-07', '13:35','Jaan Fdo'),"+
                "('Azure','2017-05-07', '13:35','Jaan Fdo'),"+
                "('Google','2017-05-07', '13:35','Jaan Fdo')";
        sqLiteDatabase.execSQL(InsertSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP_TABLE_IF_EXISTS", TABLE_NAME));
        onCreate(sqLiteDatabase);
    }

    public void add(NewsBL obj){
        ContentValues values = new ContentValues();
        values.put("news", obj.getNews());
        values.put("date", obj.getDate());
        values.put("time", obj.getTime());
        values.put("owner", obj.getOwner());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void delete (EventsBL obj){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE id=" + obj.getId() + ";");
        db.close();
    }

    public Cursor DisplayAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " +TABLE_NAME, null );
        return res;
    }

    /*public String DisplayAll(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (c.moveToLast()){
            if (c.getString(c.getColumnIndex("productname"))!=null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            db.close();

        }
        return dbString;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public String Display(ScheduleDB schedule){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (c.moveToLast()){
            if (c.getString(c.getColumnIndex("productname"))!=null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            db.close();

        }
        return dbString;
    }*/

}
