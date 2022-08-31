package com.example.examplemvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.examplemvvm.domain.model.QuoteItem

@Entity(tableName = "qoute_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "quote") val quote : String,
    @ColumnInfo(name = "author") val author : String
)

fun QuoteItem.toDatabase() = QuoteEntity(quote =  quote, author =  author)