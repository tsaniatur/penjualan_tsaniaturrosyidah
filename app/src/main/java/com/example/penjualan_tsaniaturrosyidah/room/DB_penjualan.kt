package com.example.penjualan_tsaniaturrosyidah.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [tbBarang::class],
    version = 1
)
abstract class DB_penjualan : RoomDatabase(){
    abstract fun tbbarangDao():tbBarangDAO

    companion object{
        @Volatile private var instance :DB_penjualan?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?:buildDatabase(context).also{
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DB_penjualan::class.java,
            "penjualan"
        ).build()
    }
}