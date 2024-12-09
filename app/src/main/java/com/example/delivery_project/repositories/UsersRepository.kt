package com.example.delivery_project.repositories

import com.example.delivery_project.api.JSONPlaceHolderService
import com.example.delivery_project.models.App
import com.example.delivery_project.models.Login.LoginRequest
import com.example.delivery_project.models.Login.LoginResponse
import com.example.delivery_project.models.Login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UsersRepository {

    private val retrofit = RetrofitRepository.getRetrofitInstance( App.instance.applicationContext)
    private val service = retrofit.create(JSONPlaceHolderService::class.java)


    fun createUser(
        user: User,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError(Throwable("Error en la respuesta al crear Persona"))
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun loginUser(
        loginRequest: LoginRequest,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.access_token
                    if (token != null) {
                        onSuccess(token)
                    } else {
                        onError(Throwable("Token nulo"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Respuesta desconocida"
                    onError(Throwable(errorBody))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t)
            }
        })
    }




}