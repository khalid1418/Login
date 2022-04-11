package com.khalid.login.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khalid.login.data.network.MyApi
import com.khalid.login.uiLogin.State
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository {
    fun loginUser(email:String , password:String):LiveData<String>{
        var loginResponse= MutableLiveData<String>()

        MyApi().userLogin(email , password)
            .enqueue(object :Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        loginResponse.value=response.body().toString()

                    }else{
                        loginResponse.value=response.message()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value=t.message
                }
            })
        return loginResponse
    }

}
