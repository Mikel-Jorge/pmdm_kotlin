package com.mariana.kudage.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mariana.kudage.model.dao.UsuarioDAO
import com.mariana.kudage.model.entity.Usuario
import kotlin.concurrent.Volatile

@Database(entities = [Usuario::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UsuarioDAO

    companion object {
        private const val DB_KUDAGE = "Kudage.db"

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        DB_KUDAGE).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
