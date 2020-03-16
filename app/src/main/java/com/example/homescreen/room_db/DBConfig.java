package com.example.homescreen.room_db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.homescreen.model.Items;
import com.example.homescreen.room_db.dao.ItemListDao;

@Database(entities = {
        Items.class
}, version = 1, exportSchema = false)
public abstract class DBConfig extends RoomDatabase {

    public static final String table_company = "company";

    public abstract ItemListDao itemListDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }





}
