package com.example.activitat_05


data class Receta (
    val name: String,
    val desc: String,
    val vegan: Boolean,
    val gluten: Boolean,
    val country: String,
    val image: Int
)