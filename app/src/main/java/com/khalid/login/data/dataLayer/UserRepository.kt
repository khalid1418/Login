package com.khalid.login.data.dataLayer

import com.khalid.login.model.UserLoginModel

class UserRepository(private val userRemoAteDataSource: UserDataSource) {
    fun checkUser(userLoginModel: UserLoginModel) = userRemoAteDataSource.loginUser(userLoginModel)
}