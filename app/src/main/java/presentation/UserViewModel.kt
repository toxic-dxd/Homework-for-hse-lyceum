package presentation

import Data.RetrofitInstance
import Data.UserEntity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import db.UserDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = Room.databaseBuilder(
        application,
        UserDatabase::class.java, "user_database"
    ).build().userDao()

    private val _users = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val usersFromApi = RetrofitInstance.api.getUsers()
                userDao.insertUsers(usersFromApi)
                _users.postValue(usersFromApi)
            } catch (e: Exception) {
                _users.postValue(userDao.getAllUsers())
            }
        }
    }

    suspend fun getUserDetails(id: Int): UserEntity? {
        return RetrofitInstance.api.getUserDetails(id)
    }
}