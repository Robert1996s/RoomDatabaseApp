package com.example.repository

import androidx.lifecycle.LiveData
import com.example.Data.UserDao
import com.example.Model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>>  = userDao.readAllData()

    fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}