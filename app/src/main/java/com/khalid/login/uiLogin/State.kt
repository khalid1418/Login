package com.khalid.login.uiLogin

import androidx.lifecycle.LiveData

interface State {
    fun onStarted(loginResponse: LiveData<String>)
    fun onSuccess()
    fun onFailure(massage:String)
}