package com.khalid.login.uiLogin

import androidx.lifecycle.LiveData

interface State {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(massage:String)
}