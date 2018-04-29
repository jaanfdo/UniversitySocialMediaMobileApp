package com.example.jaanfdo.myfinalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/4/2017.
 */

public class Schedule extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static   String DATABASE_NAME = "SchoolISS.db";
    public  static   String TABLE_NAME = "schedule";

    public Schedule(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table" +TABLE_NAME+ "" +
                "(id Text primary key autoincrement, course Text null, subjects Text null, date DateTime null, time Time null, class_floor Text null, class_no Text null, lecturer Text null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP_TABLE_IF_EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add(ScheduleBL schedule){
        ContentValues values = new ContentValues();
        values.put("course", schedule.getCourse());
        values.put("subjects", schedule.getSubject());
        values.put("date", schedule.getDate());
        values.put("time", schedule.getTime());
        values.put("class_floor", schedule.getClassfloor());
        values.put("class_no", schedule.getClassno());
        values.put("lecturer", schedule.getLecname());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertSomeCountries() {
        new ScheduleBL("BEng (Hons) in SE","AI","2017-10-1","10:00","2","E2","Mahesha");
    }
    public void delete (ScheduleBL schedule){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE id=" +schedule.getId()+ ";");
        db.close();
    }

    public void update (ScheduleBL schedule){
        ContentValues values = new ContentValues();
        values.put("course", schedule.getCourse());
        values.put("subjects", schedule.getSubject());
        values.put("date", schedule.getDate());
        values.put("time", schedule.getTime());
        values.put("class_floor", schedule.getClassfloor());
        values.put("class_no", schedule.getClassno());
        values.put("lecturer", schedule.getLecname());

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + "SET " + values + " WHERE id=" +schedule.getId()+ ";");
        db.close();
    }

    public String DisplayAll(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "";

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

    public Cursor DisplayAllDetails(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        /*while (c.moveToLast()){
            if (c.getString(c.getColumnIndex("productname"))!=null){
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";
            }
            db.close();
        }*/

        return c;
    }

    public ArrayList<String> getAllSchedule() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.rawQuery( "select * from "+TABLE_NAME+"", null );
        c.moveToFirst();

        while(c.isAfterLast() == false){
            array_list.add(c.getString(c.getColumnIndex("course")));
            array_list.add(c.getString(c.getColumnIndex("date")));
            array_list.add(c.getString(c.getColumnIndex("time")));
            array_list.add(c.getString(c.getColumnIndex("class_floor")));
            array_list.add(c.getString(c.getColumnIndex("class_no")));
            array_list.add(c.getString(c.getColumnIndex("lecturer")));
            c.moveToNext();
        }
        return array_list;
    }

    public String Display(ScheduleBL schedule){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE id=" +schedule.getId()+ ";";

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
}
