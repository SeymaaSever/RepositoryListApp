package com.example.repositorylistapp.service

import androidx.room.TypeConverter
import com.example.repositorylistapp.model.OwnerModel
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun modelToString(ownerModel: OwnerModel): String = Gson().toJson(ownerModel)

    @TypeConverter
    fun stringToModel(string: String): OwnerModel = Gson().fromJson(string, OwnerModel::class.java)
}