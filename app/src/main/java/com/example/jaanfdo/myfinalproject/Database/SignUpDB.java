package com.example.jaanfdo.myfinalproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jaanfdo.myfinalproject.BusinessClass.SignUpBL;

import java.util.ArrayList;

/**
 * Created by jaanfdo on 6/19/2017.
 */

public class SignUpDB extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static   String DATABASE_NAME = "SchoolISS.db";
    public  static   String TABLE_NAME = "signup";


    public SignUpDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table " +TABLE_NAME+ "(id INTEGER primary key autoincrement, username text not null, password Text not null, email Text not null,unicode Time not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add(SignUpBL obj){
        ContentValues values = new ContentValues();
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("email", obj.getEmail());
        values.put("unicode", obj.getUniversitycode());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void delete (SignUpBL obj){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + "WHERE id=" +obj.getId()+ ";");
        db.close();
    }

    public void update (SignUpBL obj){
        ContentValues values = new ContentValues();
        values.put("username", obj.getUsername());
        values.put("password", obj.getPassword());
        values.put("email", obj.getEmail());
        values.put("unicode", obj.getUniversitycode());

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + "SET " + values + " WHERE id=" +obj.getId()+ ";");
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

    public String Display(SignUpBL obj){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM" +TABLE_NAME+ "WHERE id=" +obj.getId()+ ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (c.moveToLast()){
            if (c.getString(c.getColumnIndex("username"))!=null){
                dbString += c.getString(c.getColumnIndex("username"));
                dbString += c.getString(c.getColumnIndex("password"));
                dbString += "\n";
            }
            db.close();
        }
        return dbString;
    }


}
