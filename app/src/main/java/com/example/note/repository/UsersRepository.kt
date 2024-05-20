package com.example.note.repository

import androidx.lifecycle.LiveData
import com.example.note.db.UsersDao
import com.example.note.db.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersRepository(private val usersDao: UsersDao) {
    //las coroutinas hacen algo similar al async/await
    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun insert(user: User){
        //el dispatcher crea un hilo para q no se bloquee el proceso
        coroutine.launch(Dispatchers.IO) {
            usersDao.insert(user)
        }
    }
    //aca no se pasa el id porque dentro de nota ya lo contiene
    fun update(user: User){
        coroutine.launch(Dispatchers.IO) {
            usersDao.update(user)
        }
    }
    fun getUsers(): LiveData<List<User>>{
        return usersDao.getUsers()
    }
    // aca se envia solo el id pq no es necesaria toda la nota
    fun delete(id: Int){
        coroutine.launch(Dispatchers.IO) {
            usersDao.delete(id)
        }
    }
}
