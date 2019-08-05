package com.example.expenses;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by purva on 21/4/18.
 */

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expenses.db";
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

    public ArrayList<Items> print(){
        ArrayList<Items> list = new ArrayList<>();


        SQLiteDatabase db=getWritableDatabase();
        String query="  SELECT * FROM " + TABLE_NAME + " ; ";
        Cursor C=db.rawQuery(query,null);
        C.moveToFirst();

        if (C.moveToFirst()) {
            do {Items i = new Items();
                i.set_id(C.getInt(C.getColumnIndex("_id")));
                i.setCost(C.getInt(C.getColumnIndex("cost")));
                i.setCause(C.getString(C.getColumnIndex("cause")));
                i.setDetail(C.getString(C.getColumnIndex("detail")));
                i.setDate(C.getString(C.getColumnIndex("date")));
                list.add(i);
            } while (C.moveToNext());
        }
        C.close();
        db.close();
        return list;
    }

    public int total(){
        SQLiteDatabase db=getWritableDatabase();
        String query="  SELECT SUM(COST) FROM " + TABLE_NAME + " ; ";
        Cursor C=db.rawQuery(query,null);
        C.moveToFirst();
        if(C.moveToFirst())
            return  C.getInt(C.getColumnIndex("SUM(COST)"));
        C.close();
        return 0;
    }

    public void clearTable(){
        SQLiteDatabase db = getWritableDatabase();
        String query="DELETE FROM " + TABLE_NAME ;
        db.execSQL(query);
    }
}
