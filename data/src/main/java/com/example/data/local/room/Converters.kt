package com.example.data.local.room

import androidx.room.TypeConverter
import com.example.data.models.all.UserAvatarResponceModel
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromUserAvatarCacheModel(value: UserAvatarResponceModel?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toUserAvatarCacheModel(value: String): UserAvatarResponceModel {
        return Gson().fromJson(value, UserAvatarResponceModel::class.java)
    }
}