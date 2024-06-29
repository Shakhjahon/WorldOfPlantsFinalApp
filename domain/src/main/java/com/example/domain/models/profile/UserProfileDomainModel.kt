package com.example.domain.models.profile


data class UserProfileDomainModel(
    val objectId: String,
    val userName: String?,
    val lastname: String?,
    val userAvatar: UserAvatarDomainModel,
) {
    companion object {
        val unknown = UserProfileDomainModel(
            objectId = "",
            userName = "shokh",
            lastname = "asdasdasdasd",
            userAvatar = UserAvatarDomainModel.unknown,
        )
    }
}


data class UserAvatarDomainModel(
    val name: String,
    val fileType: String,
    val url: String,
) {
    companion object {
        val unknown = UserAvatarDomainModel(
            name = "",
            fileType = "",
            url = "",
        )
    }
}
