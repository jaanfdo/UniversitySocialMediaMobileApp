package com.example.jaanfdo.myfinalproject.Buckys;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper{
    private static  final int DATABASE_VERSION = 1;
    private static  final String DATABASE_NAME = "products.db";
    public  static  final String TABLE_PRODUCTS = "products";
    private static  final String COLUMN_ID = "id";
    private static  final String COLUMN_PRODUCTSNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create Table " + TABLE_PRODUCTS + " (" +
                COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTSNAME + " TEXT " + "" +
                ");";
        db.execSQL(query);

        String InsertSql = "INSERT INTO "+TABLE_PRODUCTS+" (productname)VALUES"+
                "('Angular'),('Type Script')";
        db.execSQL(InsertSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);

    }

    public long addProducts(Products products){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTSNAME, products.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        long status = db.insert(TABLE_PRODUCTS, null, values);
        db.close();

        return status;
    }

    public void deleteProducts (String productname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + "WHERE " + COLUMN_PRODUCTSNAME + "=" + productname + ";");
        db.close();
    }

    public void updateProducts (String productname){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_PRODUCTS + "SET " + COLUMN_PRODUCTSNAME + "=" + productname + " WHERE " + COLUMN_PRODUCTSNAME + "=" + productname + ";");
        db.close();
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_PRODUCTS+ " WHERE 1";

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
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_PRODUCTS+ "";
        Cursor c = db.rawQuery(query, null);
        return c;
    }



}
