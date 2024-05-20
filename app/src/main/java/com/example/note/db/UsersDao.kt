package com.example.note.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.db.model.User

@Dao
interface UsersDao {
        @Insert
        fun insert(user: User)

        @Update
        fun update(user: User)

        @Query("DELETE FROM users WHERE id = :id")
        fun delete(id: Int)

        //liveData es un observable (esta escuchando la lista de datos)
        @Query("SELECT * FROM users")
        fun getUsers(): LiveData<List<User>>

        @Query("SELECT * FROM users WHERE id = :id")
        fun getById(id: Int): User
    }
