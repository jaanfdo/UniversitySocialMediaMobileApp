package com.example.jaanfdo.myfinalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaanfdo.myfinalproject.BusinessClass.ScheduleBL;
import com.example.jaanfdo.myfinalproject.BusinessClass.TeacherAppointmentBL;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/18/2017.
 */

public class AppointmentDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SchoolISS.db";
    private static final String TABLE_NAME = "schedule";

    public AppointmentDB(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table " +TABLE_NAME+ "" +
                "(id INTEGER primary key autoincrement, course Text not null, date DateTime not null, time Time not null, lecturer Text not null,reason Text, user Text not null)");

        String InsertSql = "INSERT INTO "+TABLE_NAME+" (course, date, time, lecturer, reason, user)VALUES"+
                "('Bsc (Hon) in Software Enginering','2017-05-07', '13:35', 'Malsha', '', 'Jaan Fdo'),"+
                "('Bsc (Hon) in Software Enginering','2017-05-07', '13:35', 'Mahesha', '','Jaan Fdo'),"+
                "('Bsc (Hon) in Software Enginering','2017-05-07', '13:35', 'Narmada', '','Jaan Fdo')";
        sqLiteDatabase.execSQL(InsertSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP_TABLE_IF_EXISTS", TABLE_NAME));
        onCreate(sqLiteDatabase);
    }

    public void add(TeacherAppointmentBL TAppointment){
        ContentValues values = new ContentValues();
        values.put("course", TAppointment.getCourse());
        values.put("date", TAppointment.getDate());
        values.put("time", TAppointment.getTime());
        values.put("lecturer", TAppointment.getLecturer());
        values.put("reason", TAppointment.getReason());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void delete (TeacherAppointmentBL TAppointment){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE id=" +TAppointment.getId()+ ";");
        db.close();
    }

    public void update (TeacherAppointmentBL TAppointment){
        ContentValues values = new ContentValues();
        values.put("course", TAppointment.getCourse());
        values.put("date", TAppointment.getDate());
        values.put("time", TAppointment.getTime());
        values.put("lecturer", TAppointment.getLecturer());
        values.put("reason", TAppointment.getReason());

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + "SET " + values + " WHERE id=" +TAppointment.getId()+ ";");
        db.close();
    }

    public ArrayList<TeacherAppointmentBL> getAllAppointment() {
        ArrayList<TeacherAppointmentBL> array_list = new ArrayList<TeacherAppointmentBL>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.rawQuery( "select * from "+TABLE_NAME+"", null );
        c.moveToFirst();

        while(c.isAfterLast() == false){
            TeacherAppointmentBL detail = new TeacherAppointmentBL();

            detail.setCourse(c.getString(c.getColumnIndex("course")));
            detail.setLecturer(c.getString(c.getColumnIndex("lecturer")));
            detail.setDate(c.getString(c.getColumnIndex("date")));
            detail.setTime(c.getString(c.getColumnIndex("time")));
            detail.setReason(c.getString(c.getColumnIndex("reason")));
            detail.setUser(c.getString(c.getColumnIndex("user")));

            array_list.add(detail);

            c.moveToNext();
        }
        return array_list;
    }

//    public Cursor DisplayAllDetails(){
//        String dbString = "";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM" +TABLE_NAME+ "";
//
//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//
//        return c;
//    }
//
//    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c =  db.rawQuery( "select * from "+TABLE_NAME+"", null );
//        c.moveToFirst();
//
//        while(c.isAfterLast() == false){
//            array_list.add(c.getString(c.getColumnIndex("course")));
//            array_list.add(c.getString(c.getColumnIndex("date")));
//            array_list.add(c.getString(c.getColumnIndex("time")));
//            array_list.add(c.getString(c.getColumnIndex("lecturer")));
//            array_list.add(c.getString(c.getColumnIndex("reason")));
//            c.moveToNext();
//        }
//        return array_list;
//    }
//
//    public String Display(TeacherAppointmentBL TAppointment){
//        String dbString = "";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE 1";
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
//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
//        return res;
//    }
}
