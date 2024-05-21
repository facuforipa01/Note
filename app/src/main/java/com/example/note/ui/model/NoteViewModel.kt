package com.example.note.ui.model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.note.core.TextFieldState
import com.example.note.db.NotesDatabase
import com.example.note.db.model.Note
import com.example.note.repository.NotesRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class NoteViewModel(application: Application): ViewModel() {

    private val _text = mutableStateOf(TextFieldState())
    val text: State<TextFieldState> = _text
    //esta parte se encarga de ver cuando se edita la nota

    private var currentID: Int? = null

    private var repository: NotesRepository
        get() {
            TODO()
        }
    var all: LiveData<List<Note>>
        get() {
            TODO()
        }

    init {
        val db = NotesDatabase.getInstance(application) //aplication es el contexto (o estado global) de la applicacion
        //db es la instancia de la base de datos

        val dao = db.notesDao()
        //dao es quien la manipula (hace todo el crud)

        repository = NotesRepository(dao)
        //repository es quien me va a dar la informacion (como una api)

        all = repository.getNotes()

    }
    private fun load(id: Int?){
        viewModelScope.launch {
            if(id != null){
                repository.findById(id).also { note ->
                    currentID = note.id
                    _text.value = text.value.copy(
                        text = note.text
                    )
                }
            }else{
                currentID = null
                _text.value = text.value.copy(
                    text = "text"
                )
            }
        }
    }
}