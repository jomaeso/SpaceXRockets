package com.josemaeso.spacexrockets.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromJsonToStringList(value: String): List<String> {
        return try {
            val gson = Gson()
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, listType)
        } catch (e: Exception) {
            listOf()
        }
    }
}