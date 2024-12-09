package com.example.delivery_project.UI.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.delivery_project.models.Login.LoginRequest
import com.example.delivery_project.models.Login.LoginResponse
import com.example.delivery_project.models.Login.User
import com.example.delivery_project.repositories.UsersRepository


class UsersViewModel(aplication : Application): AndroidViewModel(aplication) {


    private val _loginResult = MutableLiveData<Result<String>>() // Token o error
    val loginResult: LiveData<Result<String>> get() = _loginResult



    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse



    fun addUser(user: User) {
        UsersRepository.createUser(
            user,
            onSuccess = { createdPersona ->

                Log.d("UsersViewModel", "Usuario creado: $createdPersona")


            },
            onError = { error ->
                Log.e("SubmitPerson", "Error al crear persona", error)
            }
        )
    }

    fun loginUser(loginRequest: LoginRequest) {
        UsersRepository.loginUser(
            loginRequest,
            onSuccess = { token ->

                val sharedPreferences = getApplication<Application>().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("auth_token", token)
                editor.apply()

                // Notificar el resultado
                _loginResult.postValue(Result.success(token))
            },
            onError = { error ->
                _loginResult.postValue(Result.failure(error))
            }
        )

    }










}