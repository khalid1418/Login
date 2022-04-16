package com.khalid.login.domin

import com.khalid.login.data.dataLayer.UserRepository
import com.khalid.login.model.UserLoginModel

class CheckUserUseCase(private val repository: UserRepository) {
    operator fun invoke(userLoginModel: UserLoginModel) = repository.checkUser(userLoginModel)
}