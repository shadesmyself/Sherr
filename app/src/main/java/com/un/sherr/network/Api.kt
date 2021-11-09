package com.un.sherr.network

import com.un.sherr.models.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("api/orders")
    fun getAllOrders(): Observable<DataWrapperResponse<List<GoodResponse>>>

    @GET("api/orders/{id}")
    fun getOrder(@Path("id") id: Int): Observable<DataWrapperResponse<GoodResponse>>

    @Headers("Content-Type: multipart/form-data")
    @POST("api/orders")
    fun createOrder(@Body orderRequest: OrderRequest): Observable<DataWrapperResponse<OrderResponse>>

    @GET("api/category")
     fun getCategories(): Observable<DataWrapperResponse<List<CategoryResponse>>>

    @GET("api/sub-category")
    fun getSubCategories(@Query("search") categoryId: String): Observable<DataWrapperResponse<List<SubcategoryResponse>>>

    @GET("api/user/details")
    fun getCurrentUserInformation(): Observable<DataWrapperResponse<UserInformationResponse>>

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    fun getUserAuthorizationToken(@Field("client_id") clientId : Int, @Field("client_secret") clientSecret: String, @Field("grant_type") grantType: String, @Field("password") password: String, @Field("scope") scope: String,  @Field("username") username: String): Observable<TokenResponse>

    @Headers("Content-Type: multipart/form-data")
    @POST("api/user/avatar")
    @Multipart
    suspend fun uploadUserAvatar(@Part body: MultipartBody.Part): Response<DataWrapperResponse<Avatar>>

    @Headers( "Content-Type: application/json")
    @POST("api/user/register")
    fun userRegistration(@Body userRegistrationRequest :UserRegistrationRequest): Observable<DataWrapperResponse<UserRegistrationResponse>>

    @Headers( "Content-Type: multipart/form-data" , "Authorization: Bearer 1X899GOuLn0CQxFs-zi-59WdO4ysL7UVfN2oSZ6xVPrFH4BeCaXpUpmXU9KZg")
    @Multipart
    @POST("api/user/verify")
    suspend fun getVerify(@Part passport: MultipartBody.Part,@Part registration: MultipartBody.Part): Response<Unit>

    @Headers( "Content-Type: application/json")
    @GET("/api/chats" )
    suspend fun getAllChats(@Header("Authorization") token: String) : Response<Chats>

    @Headers( "Content-Type: application/json")
    @GET("/api/chats/{id}" )
    suspend fun getChatDirect(@Path("id") id: Int, @Header("Authorization") token: String): Response<DataWrapperResponse<List<ChatDirectMain>>>

    @Headers( "Content-Type: application/json")
    @Multipart
    @POST("/api/chat")
    suspend fun sendMessage(@Header("Authorization") token: String, @Part("recipient_id") recipient_id: Int, @Part("message") message: String): Response<Unit>
}