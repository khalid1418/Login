package com.khalid.login.utlis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khalid.login.uiLogin.LoginViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->{
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(providerCheckUserUseCase()) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}