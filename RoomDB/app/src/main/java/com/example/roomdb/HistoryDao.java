package com.example.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.Hist;
import com.example.roomdb.User;

import java.util.List;
@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(Hist history);
    @Query("SELECT * FROM history")
    List<Hist> getAllHistory();
    @Query("SELECT * FROM history WHERE userId = :user_id")
    List<Hist> getHistory(int user_id);
}
