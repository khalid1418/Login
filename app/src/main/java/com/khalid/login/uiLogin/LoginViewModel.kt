package com.khalid.login.uiLogin

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import com.khalid.login.data.repository.UserRepository

class LoginViewModel:ViewModel() {
   var state:State? = null

  fun userLogin(email:String , password:String){
      val loginResponse = UserRepository().loginUser(email , password)
      state?.onSuccess(loginResponse)
  }





}
