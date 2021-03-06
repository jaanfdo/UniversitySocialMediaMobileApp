package com.example.jaanfdo.myfinalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaanfdo on 6/4/2017.
 */

public class ScheduleDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "SchoolInfoSS.db";
    private static final String TABLE_NAME = "tblSchedule";

    public ScheduleDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table " + TABLE_NAME +" (id INTEGER primary key autoincrement, course Text not null, subjects Text not null, date DateTime , time Time , class_floor Text , class_no Text , lecturer Text )");

        String InsertSql = "INSERT INTO "+TABLE_NAME+" (course, subjects, date, time, class_floor, class_no, lecturer)VALUES"+
                "('Bsc (Hon) in Software Enginering','Angular','2017-05-07', '13:35','3','3-F','Malsha'),"+
                "('Bsc (Hon) in Software Enginering','Azure','2017-05-07', '13:35','4','4-A','Mahesha'),"+
                "('Bsc (Hon) in Software Enginering','GO','2017-05-07', '13:35','3','3-F','Malsha')";
        sqLiteDatabase.execSQL(InsertSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean create(ScheduleBL schedule){
        ContentValues values = new ContentValues();
        values.put("course", schedule.getCourse());
        values.put("subjects", schedule.getSubject());
        values.put("date", schedule.getDate());
        values.put("time", schedule.getTime());
        values.put("class_floor", schedule.getClassfloor());
        values.put("class_no", schedule.getClassno());
        values.put("lecturer", schedule.getLecname());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert(TABLE_NAME, null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public void delete (ScheduleBL schedule){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE id=" +schedule.getId()+ ";");
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
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + values + " WHERE id=" +schedule.getId()+ ";");
        db.close();
    }

    public Cursor DisplayAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.rawQuery( "select * from "+TABLE_NAME, null );
        return c;
    }

    public Cursor DisplayRecordByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME +" WHERE id = " + id, null);
        return cursor;
    }



//    public String Display(ScheduleBL schedule){
//        String dbString = "";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE id=" +schedule.getId()+ ";";
//
//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//
//        while (c.moveToLast()){
//            if (c.getString(c.getColumnIndex("productname"))!=null){
//                dbString += c.getString(c.getColumnIndex("productname"));
//                dbString += "\n";
//            }
//            db.close();
//
//        }
//        return dbString;
//    }
//
//    public List<ScheduleBL> DisplayAllDetails(){
//        List<ScheduleBL> recordsList = new ArrayList<ScheduleBL>();
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = String.format("SELECT * FROM ", TABLE_NAME);
//        Cursor c = db.rawQuery(query, null);
//
//        if(c.moveToFirst()) {
//            while (c.moveToLast()){
//                ScheduleBL schedule = new ScheduleBL();
//                schedule.setId(c.getString(c.getColumnIndex("id")));
//                schedule.setCourse(c.getString(c.getColumnIndex("course")));
//                schedule.setSubject(c.getString(c.getColumnIndex("subjects")));
//                schedule.setDate(c.getString(c.getColumnIndex("date")));
//                schedule.setTime(c.getString(c.getColumnIndex("time")));
//                schedule.setClassfloor(c.getString(c.getColumnIndex("class_floor")));
//                schedule.setClassno(c.getString(c.getColumnIndex("class_no")));
//                schedule.setLecname(c.getString(c.getColumnIndex("lecturer")));
//
//                recordsList.add(schedule);
//            }
//        }
//        c.close();
//        db.close();
//
//        return recordsList;
//    }
//
//    public String DisplayAll(){
//        String dbString = "";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = String.format("SELECT * FROM", TABLE_NAME);
//
//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//
//        while (c.moveToLast()){
//            if (c.getString(c.getColumnIndex("productname"))!=null){
//                dbString += c.getString(c.getColumnIndex("productname"));
//                dbString += "\n";
//            }
//            db.close();
//
//        }
//        return dbString;
//    }
}
