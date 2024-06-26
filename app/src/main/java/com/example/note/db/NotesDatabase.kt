package com.example.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.note.db.model.Note
import com.example.note.db.model.User

@Database(
    entities = [(Note::class), (User::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun usersDao(): UsersDao

    // unica instancia de esa base de datos (singleton)
    // si ya existe la devuelve, sino la crea
    companion object {
        //volatil se refiere a que sincronice y maneje las concurrencias
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        // si no hay una instancia creala, si no trae la creada
        fun getInstance(context: Context): NotesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "bd_app_notas"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}