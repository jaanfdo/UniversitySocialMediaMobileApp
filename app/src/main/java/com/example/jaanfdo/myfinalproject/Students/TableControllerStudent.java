package com.example.jaanfdo.myfinalproject.Students;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TableControllerStudent extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "StudentDatabase";

    public TableControllerStudent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE students " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "email TEXT ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);

        onCreate(db);
    }

    public boolean create(ObjectStudent objectStudent) {

        ContentValues values = new ContentValues();

        values.put("firstname", objectStudent.firstname);
        values.put("email", objectStudent.email);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("students", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM students";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<ObjectStudent> read() {

        List<ObjectStudent> recordsList = new ArrayList<ObjectStudent>();

        String sql = "SELECT * FROM students ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ObjectStudent objectStudent = new ObjectStudent();
                objectStudent.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                objectStudent.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
                objectStudent.email = cursor.getString(cursor.getColumnIndex("email"));

                recordsList.add(objectStudent);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectStudent readSingleRecord(int studentId) {
        ObjectStudent objectStudent = null;

        String sql = "SELECT * FROM students WHERE id = " + studentId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            objectStudent = new ObjectStudent();
            objectStudent.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            objectStudent.firstname = cursor.getString(cursor.getColumnIndex("firstname"));
            objectStudent.email = cursor.getString(cursor.getColumnIndex("email"));
        }

        cursor.close();
        db.close();

        return objectStudent;
    }

    public boolean update(ObjectStudent objectStudent) {
        ContentValues values = new ContentValues();

        values.put("firstname", objectStudent.firstname);
        values.put("email", objectStudent.email);

        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectStudent.id) };

        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful = db.update("students", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("students", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }
}
