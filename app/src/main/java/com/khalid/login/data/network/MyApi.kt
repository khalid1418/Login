package com.khalid.login.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val BASE_URL = "https://62503cde977373573f3945a4.mockapi.io"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



interface MyApi {
    @GET("User/{email}/login2/{password}")
    fun userLogin(@Path("email") email: String , @Path("password")password:String):Call<ResponseBody>

     companion object{
         operator fun invoke():MyApi{
             return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(
                 moshi)).build().create(MyApi::class.java)
         }
     }
}

