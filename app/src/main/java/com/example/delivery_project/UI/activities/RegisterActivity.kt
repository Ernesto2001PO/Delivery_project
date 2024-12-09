package com.example.delivery_project.UI.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.delivery_project.UI.viewmodels.UsersViewModel

import com.example.delivery_project.databinding.RegisterFormBinding
import com.example.delivery_project.models.Login.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: RegisterFormBinding
    private val viewModel: UsersViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = RegisterFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            submitForm()

        }
    }

    fun submitForm() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val role = 1

        Log.d("SubmitForm", "Datos de la persona a enviar Name: $name , Email: $email, Password: $password, Role: $role")

        val user = User(
            id = 0,
            name = name,
            email = email,
            password = password,
            role = role
        )

        Log.d("SubmitForm", "User Object: $user")


        viewModel.addUser(user)

    }


}