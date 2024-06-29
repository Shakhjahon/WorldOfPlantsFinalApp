package com.example.worldofplantsapp.presentation.author.register

import com.example.worldofplantsapp.utils.util.emptyString

data class RegisterInfoState(
    val userName: String = emptyString(),
    val lastName: String = emptyString(),
    val email: String = emptyString(),
    val userPassword: String = emptyString(),
)