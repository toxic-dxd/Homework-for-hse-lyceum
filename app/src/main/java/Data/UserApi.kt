package Data

import retrofit2.http.GET
import retrofit2.http.Path


interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserEntity>

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") id: Int): UserEntity
}