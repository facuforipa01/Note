package com.example.note.ui.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.note.db.NotesDatabase
import com.example.note.db.model.User
import com.example.note.repository.UsersRepository
class UserViewModel(application: Application) {
    private var repository: UsersRepository
        get() {
            TODO()
        }
    var all: LiveData<List<User>>
        get() {
            TODO()
        }

    init {
        val db = NotesDatabase.getInstance(application) //aplication es el contexto (o estado global) de la applicacion
        //db es la instancia de la base de datos
        val dao = db.usersDao()
        //dao es quien la manipula (hace todo el crud)
        repository = UsersRepository(dao)
        //repository es quien me va a dar la informacion (como una api)

        all = repository.getUsers()
    }
}