package com.example.gastos.roomdb.saldo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SaldoActual::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SaldoDataBase: RoomDatabase() {

     abstract fun saldoActualDao() : SaldoActualDao

     companion object{
         private var INSTANCE: SaldoDataBase? = null

         fun  getDatabase(context: Context) : SaldoDataBase {
             return INSTANCE ?: synchronized(this) {
                 val instace = Room.databaseBuilder(
                     context.applicationContext,
                     SaldoDataBase::class.java,
                     "saldo_database"
                 ).build()
                 INSTANCE = instace
                 instace
             }
         }
     }



}