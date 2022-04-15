package com.khalid.login.uiLogin


import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.khalid.login.domin.CheckUserUseCase

class LoginViewModel(private val checkUserUseCase: CheckUserUseCase):ViewModel() {
   var state:State? = null

  private fun userLogin(email:String , password:String){
      val loginResponse = checkUserUseCase.invoke(email , password)

      state?.onSuccess(loginResponse)
  }

     fun entryValid(
        email: String,
        password: String
    ): Boolean {
         return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
             state?.onFailure("email required")
             false
         } else if (password.isBlank()) {
             state?.onFailure("password required")
             false
         }else{

             userLogin(email,password)
             true

         }
    }





}
