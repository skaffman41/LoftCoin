package com.skaffman.loftcoin.data.db.room;

import com.skaffman.loftcoin.data.db.model.CoinEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CoinEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
}
