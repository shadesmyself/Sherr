package com.un.sherr.network

import com.un.sherr.models.*
import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @GET("orders")
    fun getAllOrders(): Observable<DataWrapperResponse<List<GoodResponse>>>

    @GET("orders/{id}")
    fun getOrder(@Path("id") id: Int): Observable<DataWrapperResponse<GoodResponse>>

    @Headers("Content-Type: multipart/form-data")
    @POST("orders")
    fun createOrder(@Body orderRequest: OrderRequest): Observable<DataWrapperResponse<OrderResponse>>

    @GET("category")
     fun getCategories(): Observable<DataWrapperResponse<List<CategoryResponse>>>

    @GET("sub-category")
    fun getSubCategories(
        @Query("search") categoryId: String
    ): Observable<DataWrapperResponse<List<SubcategoryResponse>>>

    @GET("user/details")
    fun getCurrentUserInformation(): Observable<DataWrapperResponse<UserInformationResponse>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/token")
    fun getUserAuthorizationToken(@Body userAuthorizationRequest: UserAuthorizationRequest): Observable<TokenResponse>

//    @Headers("Content-Type: multipart/form-data")
//    @POST("user/avatar")
//    @Multipart
//    fun uploadUserAvatar(@Part body: MultipartBody.Part): Observable<DataWrapperResponse<Avatar>>

    @Headers( "Content-Type: application/json")
    @POST("user/register")
    fun userRegistration(@Body userRegistrationRequest :UserRegistrationRequest): Observable<DataWrapperResponse<UserRegistrationResponse>>

}