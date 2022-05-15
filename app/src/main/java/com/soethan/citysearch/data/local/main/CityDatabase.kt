package com.soethan.citysearch.data.local.main

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soethan.citysearch.data.local.dao.CityDao
import com.soethan.citysearch.data.local.entity.CityEntity


@Database(entities = [CityEntity::class],version = 1)
abstract class CityDatabase:RoomDatabase(){
    abstract fun cityDao():CityDao
}