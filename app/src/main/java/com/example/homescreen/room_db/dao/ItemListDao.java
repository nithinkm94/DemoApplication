package com.example.homescreen.room_db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.homescreen.model.Items;

import java.util.List;

@Dao
public interface ItemListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(List<Items> rows);


    @Query("SELECT * FROM Items WHERE title != :s")
    List<Items> getItems(String s);

    @Query("DELETE FROM Items")
    void deleteItems();
}
