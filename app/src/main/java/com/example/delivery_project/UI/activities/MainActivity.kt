package com.example.delivery_project.UI.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.delivery_project.R
import com.example.delivery_project.UI.viewmodels.UsersViewModel
import com.example.delivery_project.databinding.ActivityMainBinding
import com.example.delivery_project.models.Login.LoginRequest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: UsersViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            login()

        }



        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { token ->
                Toast.makeText(this, "Login exitoso, token: $token", Toast.LENGTH_LONG).show()
                Log.d("Login", "Token: $token")
                // Guarda el token o redirige a otra pantalla
                val intent = Intent(this, Menu_RestaurantActivity::class.java)
                startActivity(intent)
            }
            result.onFailure { error ->
                Toast.makeText(this, "Error en el login: ${error.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }


    fun login() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPasswordLogin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            binding.etEmail.error = "Email is required"
            binding.etPasswordLogin.error = "Password is required"
            return
        }

        val loginRequest = LoginRequest(
            email = email,
            password = password
        )

        Log.d("Login", "Datos a enviar para el login: Email: $email, Password: $password")

        viewModel.loginUser(loginRequest)
    }


}