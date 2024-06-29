package com.example.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.models.all.UserAvatarResponceModel

private const val PLANT_TABLE_NAME = "plant_table"
private const val BIOLOGICAL_NAME = "biological_name"
private const val CATEGORY = "category"
private const val DESCRIPTIONS = "descriptions"
private const val CREATED_AT = "createdAt"
private const val IMAGE = "image"
private const val IMAGE_DETAILS = "image_details"
private const val NAME = "name"
private const val UPDATED_AT = "updatedAt"
private const val LITTLE_SPROUT = "little_sprout"
private const val FERTILIZER = "fertilizer"
private const val KEY_FACTS = "key_facts"
private const val EARTH = "earth"
private const val CLIMATE_ZON = "climate_zon"
private const val FAMOUS = "famous"
private const val PLANT_HEIGHT = "plant_height"
private const val TEMPERATURE = "temperature"
private const val WATER = "water"
private const val ADVICE = "advice"
private const val CATEGORY_ID = "categoryId"


@Entity(tableName = PLANT_TABLE_NAME)
data class PlantCacheModel(
    @PrimaryKey val objectId: String,
    @ColumnInfo(BIOLOGICAL_NAME) val biologicalName: String,
    @ColumnInfo(CATEGORY) val category: String,
    @ColumnInfo(DESCRIPTIONS) val descriptions: String,
    @ColumnInfo(CREATED_AT) val createdAt: String,
    @ColumnInfo(IMAGE) val image: UserAvatarResponceModel,
    @ColumnInfo(IMAGE_DETAILS) val imageDetails: UserAvatarResponceModel,
    @ColumnInfo(NAME) val name: String,
    @ColumnInfo(UPDATED_AT) val updatedAt: String,
    @ColumnInfo(LITTLE_SPROUT) val little: UserAvatarResponceModel,
    @ColumnInfo(FERTILIZER) val fertilizer: String,
    @ColumnInfo(KEY_FACTS) val keyFact: String,
    @ColumnInfo(EARTH) val earth: String,
    @ColumnInfo(CLIMATE_ZON) val climateZon: String,
    @ColumnInfo(FAMOUS) val famous: String,
    @ColumnInfo(PLANT_HEIGHT) val plantHeight: String,
    @ColumnInfo(TEMPERATURE) val temperature: String,
    @ColumnInfo(WATER) val water: String,
    @ColumnInfo(ADVICE) val advice: String,
    @ColumnInfo(CATEGORY_ID) val categoryId: String,
)