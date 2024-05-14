package com.example.note.repository

import androidx.lifecycle.LiveData
import com.example.note.db.NotesDao
import com.example.note.db.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepository(private val notesDao: NotesDao) {
    //las coroutinas hacen algo similar al async/await
    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun insert(note: Note){
        //el dispatcher crea un hilo para q no se bloquee el proceso
        coroutine.launch(Dispatchers.IO) {
            notesDao.insert(note)
        }
    }
    //aca no se pasa el id porque dentro de nota ya lo contiene
    fun update(note: Note){
        coroutine.launch(Dispatchers.IO) {
            notesDao.update(note)
        }
    }
    fun getNotes(): LiveData<List<Note>>{
        return notesDao.getNotes()
    }
    // aca se envia solo el id pq no es necesaria toda la nota
    fun delete(id: Int){
        coroutine.launch(Dispatchers.IO) {
            notesDao.delete(id)
        }
    }
}
