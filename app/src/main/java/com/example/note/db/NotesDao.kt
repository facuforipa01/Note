package com.example.note.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.db.model.Note

@Dao
interface NotesDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("DELETE FROM notes WHERE id = :id")
    fun delete(id: Int)

    //liveData es un observable (esta escuchando la lista de datos)
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    //el asterisco es para que traiga todo de cada nota
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getById(id: Int): Note
}