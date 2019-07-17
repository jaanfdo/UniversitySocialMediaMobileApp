package com.example.jaanfdo.myfinalproject.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserDB extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "Users.db";
    protected static final String TABLE_NAME = "Users";

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "CREATE TABLE "+TABLE_NAME+" ( id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT ) ";
            db.execSQL(sql);

            //Toast.makeText(UserDB.this, "table created ", Toast.LENGTH_LONG).show();
            String InsertSql = "INSERT INTO "+TABLE_NAME+" (username, password, email) VALUES('jaanfdo','jaanfdo','jaanfdo@mail.com'),"+
                    "('jaanfdo1','jaanfdo1','jaanfdo1@mail.com'),"+"('jaanfdo2','jaanfdo2','jaanfdo2@mail.com')";
            db.execSQL(InsertSql);
        }
        catch (Exception e) {
            //Toast.makeText(UserDB.this, "ERROR "+ e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor DisplayAll(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);

        return cursor;
    }
}
