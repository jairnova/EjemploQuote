package com.example.examplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDataBase: RoomDatabase() {
// Es abstract por que solo tenemos un dao, por cada dao vamos a tener que crear una función abstract

    abstract fun getQuoteDao():QuoteDao
}