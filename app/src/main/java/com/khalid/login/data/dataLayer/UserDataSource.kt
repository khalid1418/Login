package com.khalid.login.data.dataLayer

import androidx.lifecycle.LiveData
import com.khalid.login.model.UserLoginModel

interface UserDataSource {
    fun loginUser(userLoginModel: UserLoginModel):LiveData<String>
}