package com.example.expenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by purva on 21/4/18.
 */

public class MyDBCostHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cost.db";
    private static final String TABLE_NAME = "costManager";
    private static final String COLOUM_ID = "_id";
    private static final String COLOUM_COST = "cost";

    public MyDBCostHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLOUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLOUM_COST + " INTEGER " +
                " ) ; ";
        db.execSQL(query);
        ManageCost c=new ManageCost(0);
        //add default total , balance;
        addCost(c,db);
        addCost(c,db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }



    public void addCost(ManageCost c,SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLOUM_COST, c.getCost());
        db.insert(TABLE_NAME, null, values);
    }

    public String printTotal() {

        SQLiteDatabase db = getWritableDatabase();
        String query = "  SELECT * FROM " + TABLE_NAME + " WHERE " + COLOUM_ID + " = 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String dbString = cursor.getString(cursor.getColumnIndex("cost"));
        cursor.close();
        return dbString;
    }
    public String printBalance() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "  SELECT * FROM " + TABLE_NAME + " WHERE " + COLOUM_ID + " = 2";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String dbString = cursor.getString(cursor.getColumnIndex("cost"));
        cursor.close();
        return dbString;
    }
    public void costUpdateBalance(int newCost){
        int currentCost = 0;
        if(printBalance() != "")

            currentCost=Integer.parseInt(printBalance());
        int finalCost = currentCost - newCost;
        ContentValues cv = new ContentValues();
        cv.put(COLOUM_COST,finalCost);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, cv, "_id = 2 ", null);
        db.close();
    }
    public void costUpdateTotal(int cost){
        ContentValues cv = new ContentValues();
        cv.put(COLOUM_COST,cost);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, cv, "_id = 1 ", null);
        db.close();
    }
    public void costChangeBalance(int newCost){
        ContentValues cv = new ContentValues();
        cv.put(COLOUM_COST,newCost);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, cv, "_id = 2 ", null);
        db.close();
    }

}
/*
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expenditure.db";
    private static final String TABLE_NAME = "info";
    private static final String COLOUM_ID= "_id";
    private static final String COLOUM_CAUSE = "cause";
    private static final String COLOUM_DETAIL = "detail";
    private static final String COLOUM_COST = "cost";
    private static final String COLOUM_DATE = "date";


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ TABLE_NAME + " (" +
                COLOUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLOUM_DATE + " TEXT, " +
                COLOUM_CAUSE + " TEXT, " +
                COLOUM_DETAIL + " TEXT, " +
                COLOUM_COST + " INTEGER "+
                " ) ; " ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,DATABASE_NAME, factory,DATABASE_VERSION);
    }


    public void addItem(Items item){
        ContentValues values =new ContentValues();
        values.put(COLOUM_DATE,item.getDate());
        values.put(COLOUM_CAUSE,item.getCause());
        values.put(COLOUM_DETAIL,item.getDetail());
        values.put(COLOUM_COST,item.getCost());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String print(){
        String dbString ="";
        SQLiteDatabase db=getWritableDatabase();
        String query="  SELECT * FROM " + TABLE_NAME + " ; ";
        Cursor C=db.rawQuery(query,null);
        C.moveToFirst();

        if (C.moveToFirst()) {
            do {dbString += C.getString(C.getColumnIndex("date"));
                dbString += " ";
                dbString += C.getString(C.getColumnIndex("cause"));
                dbString += " ";
                dbString += C.getString(C.getColumnIndex("detail"));
                dbString += " ";
              dbString += C.getString(C.getColumnIndex("cost"));
                dbString += "\n";
            } while (C.moveToNext());
        }
        db.close();
        return dbString;
    }

    public void clearTable(){
        SQLiteDatabase db = getWritableDatabase();
        String query="DELETE FROM " + TABLE_NAME ;
        db.execSQL(query);
    }
}

 */
