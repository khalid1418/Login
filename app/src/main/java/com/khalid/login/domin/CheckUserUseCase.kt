package com.khalid.login.domin

import com.khalid.login.data.dataLayer.UserRepository

class CheckUserUseCase(private val repository: UserRepository) {
    operator fun invoke(email:String , password:String) = repository.checkUser(email , password)
}