package com.example.worldofplantsapp.presentation.author.login

import com.example.worldofplantsapp.utils.util.emptyString


data class LoginInfoState(
    val email: String = emptyString(),
    val userPassword: String = emptyString(),

    )
