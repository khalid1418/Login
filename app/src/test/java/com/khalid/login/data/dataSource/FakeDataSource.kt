package com.khalid.login.data.dataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.khalid.login.data.dataLayer.UserDataSource
import com.khalid.login.model.UserLoginModel

class FakeDataSource:UserDataSource {


    override fun loginUser(userLoginModel: UserLoginModel): LiveData<String> {
        return MutableLiveData()


    }
}