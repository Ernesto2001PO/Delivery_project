package com.example.delivery_project.interfaces

interface OnProductClickListener {

    fun onPlusClick(position: Int, quantity: Int)
    fun onLessClick(position: Int, quantity: Int)
}