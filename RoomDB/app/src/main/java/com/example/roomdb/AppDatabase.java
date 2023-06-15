package com.example.roomdb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.roomdb.HistoryDao;
import com.example.roomdb.UserDao;

@Database(entities = {User.class, Hist.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract HistoryDao historyDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getObInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "dota.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
