package com.khalid.login.data.dataSource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khalid.login.data.dataLayer.UserDataSource
import com.khalid.login.model.UserLoginModel

class FakeDataSource:UserDataSource {

    private var onlineNetwork = true
    val checkUser = MutableLiveData<String>()

    fun Network(value:Boolean){
        onlineNetwork = value
    }

    override fun loginUser(userLoginModel: UserLoginModel): LiveData<String> {

        if (!onlineNetwork){
            checkUser.value="Network Error"
        }else if (userLoginModel.email != "khalid@gmail.com" || userLoginModel.password != "12345"){
            checkUser.value = "Not Found"
        }else{
            checkUser.value = "Ok"
        }
        return checkUser
    }
}