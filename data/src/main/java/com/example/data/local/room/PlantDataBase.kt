package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PlantCacheModel::class], version = 1)
@TypeConverters(Converters::class)

abstract class PlantDataBase : RoomDatabase() {
    abstract fun getPlantDao(): PlantDao

}