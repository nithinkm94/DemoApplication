package com.example.homescreen.room_db;

import android.content.Context;

import androidx.room.Room;

import com.example.homescreen.model.HeaderModel;
import com.example.homescreen.model.Items;

import java.util.List;

/*Singleton class*/
public class DBRepository {

    private static DBRepository dbRepository;

    public static String DB_NAME = "db_demo";

    static DBConfig DBConfig;

    private DBRepository() {
    }

    public static DBRepository getInstance(Context context) {
//
        DBConfig = Room.databaseBuilder(context, DBConfig.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
        if (dbRepository == null) {
            dbRepository = new DBRepository();
        }
        return dbRepository;
    }

    public List<Items> getItems() {
        return DBConfig.itemListDao().getItems("");
//        return null;
    }

    public void insetItems(List<Items> rows) {
        DBConfig.itemListDao().insertItems(rows);
    }

    public void deleteItems() {
        DBConfig.itemListDao().deleteItems();
    }
}
