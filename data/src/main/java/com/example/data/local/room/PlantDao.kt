package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Insert(entity = PlantCacheModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToCache(cacheModel:  List<PlantCacheModel>)

    @Query("SELECT * FROM plant_table")
    fun addAllPlants(): Flow<List<PlantCacheModel>>

    @Query("DELETE FROM plant_table WHERE objectId = :plantId")
    suspend fun deletePlantById(plantId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM plant_table WHERE objectId = :plantId  LIMIT 1)")
    fun observeIsPlantsSaved(plantId: String): Flow<Boolean>

    @Query("DELETE FROM plant_table")
    suspend fun clearTable()
}