package com.example.examplemvvm.di

import android.content.Context
import androidx.room.Room
import com.example.examplemvvm.data.database.QuoteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    //databaseBuilder es el objeto que nos va crear la base de datos este solo necesita el context,
    // la clase donde esta la base de datos creada, el nombre de la base de datos
    // el context no lo provee dager
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDataBase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDataBase) = db.getQuoteDao()
}