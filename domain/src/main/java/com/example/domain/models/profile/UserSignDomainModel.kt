package com.example.domain.models.profile


data class UserSignDomainModel(
    val username: String,
    val password: String,
    val email: String,
    val lastname: String,
) {
    companion object {
        val NONE = UserSignDomainModel(
            username = String(),
            password = String(),
            email = String(),
            lastname = String(),
        )
    }
}