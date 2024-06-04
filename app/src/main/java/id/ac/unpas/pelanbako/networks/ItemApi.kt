package id.ac.unpas.pelanbako.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.pelanbako.models.item
import id.ac.unpas.pelanbako.networks.responses.ItemDeleteResponse
import id.ac.unpas.pelanbako.networks.responses.ItemGetResponse
import id.ac.unpas.pelanbako.networks.responses.ItemPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ItemApi {
    @GET("item")
    suspend fun findAll(): ApiResponse<ItemGetResponse>

    @POST("item")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: item): ApiResponse<ItemPostResponse>

    @PUT("item/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body item: item): ApiResponse<ItemPostResponse>

    @DELETE("item/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<ItemDeleteResponse>
}