package com.example.data.service

import com.example.data.models.profile.UserProfileResponceModel
import com.example.data.models.profile.UserResponceModel
import com.example.data.models.world.GetItemsPlantResponceModel
import com.example.data.models.world.GetPlantResponceModel
import com.example.data.models.world.GetWorldOfPlantResponceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val CLASS_USER = "classes/_User"
private const val GET_ITEMS = "classes/category_plant"
private const val GET_PLANTS = "classes/world_of_plants"

interface UserService {

    /** Register User **/
    @POST(CLASS_USER)
    suspend fun registerUser(
        @Body userResponceModel: UserResponceModel
    ): Response<UserProfileResponceModel>

    @POST("login")
    suspend fun loginUser(
        @Query("username") userEmail: String,
        @Query("password") userPassword: String,
    ): Response<UserProfileResponceModel>

    /** Get World of Plants **/
    @GET(GET_PLANTS)
    suspend fun getPlant(): Response<GetWorldOfPlantResponceModel>

    @GET(GET_PLANTS)
    suspend fun getPlantDetails(
        @Query("where") plantId: String
    ): Response<GetWorldOfPlantResponceModel>

    @GET(GET_ITEMS)
    suspend fun getPlantItems(): Response<GetItemsPlantResponceModel>

    @GET(GET_PLANTS)
    suspend fun getPlantItemsDetails(
        @Query("where") categoryId: String
    ): Response<GetWorldOfPlantResponceModel>
}