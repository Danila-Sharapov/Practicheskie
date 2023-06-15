package com.example.projectbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "guns.db";
    public static final int SCHEMA = 1;
    static final String TABLE_NAME = "gun";

    public static final String COLUMN_ID = "id_gun";
    public static final String COLUMN_HEADER = "header_gun";
    public static final String COLUMN_INFO = "info_gun";


    public DataBaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        + COLUMN_HEADER+" TEXT, "+COLUMN_INFO+" INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addGun(Guns guns){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_HEADER, guns.getHeader());
        contentValues.put(COLUMN_INFO, guns.getInfo());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Guns> getGunsList(){
        ArrayList<Guns> listGuns = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (result.moveToFirst()){
            while (result.moveToNext()){
                int id = result.getInt(0);
                String gunHeader = result.getString(1);
                String gunInfo = result.getString(2);
                Guns guns = new Guns(id, gunHeader, gunInfo);
                listGuns.add(guns);
            }
        }
        result.close();
        return listGuns;
    }

    public ArrayList<Guns> getGun(int id){
        ArrayList<Guns> listGuns = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (result.moveToFirst()){
            while (result.moveToNext()){
                id = result.getInt(0);
                String gunHeader = result.getString(1);
                String gunInfo = result.getString(2);
                Guns guns = new Guns(id, gunHeader, gunInfo);
                listGuns.add(guns);
            }
        }
        result.close();
        return listGuns;
    }
}
