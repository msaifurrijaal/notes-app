package com.example.latihanroomdatabase1.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDB : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getInstance(context: Context) : NoteDB {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDB::class.java,
                        "todo_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}