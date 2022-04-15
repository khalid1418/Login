package com.khalid.login.data.dataLayer

class UserRepository(private val userRemoAteDataSource: UserRemoteDataSource) {
    fun checkUser(email: String, password: String) = userRemoAteDataSource.loginUser(email, password)
}