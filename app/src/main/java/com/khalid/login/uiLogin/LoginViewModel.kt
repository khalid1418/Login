package com.khalid.login.uiLogin


import androidx.lifecycle.ViewModel
import com.khalid.login.domin.CheckUserUseCase
import com.khalid.login.model.UserLoginModel
import java.util.regex.Pattern

class LoginViewModel(private val checkUserUseCase: CheckUserUseCase):ViewModel() {
   var state:State? = null

  private fun userLogin(userLoginModel: UserLoginModel){
      val loginResponse = checkUserUseCase.invoke(userLoginModel)
      state?.onStarted(loginResponse)
  }

     fun entryValid(
        email: String,
        password: String
    ): Boolean {

         val emailAddressPattern: Pattern by lazy {
             Pattern.compile(
                 "^[A-Za-z](.*)([@])(.+)(\\.)(.+)",
             )
         }
         return if (email.isEmpty() || !emailAddressPattern.matcher(email).matches()) {
             state?.onFailure("email required")
             false
         } else if (password.isBlank()) {
             state?.onFailure("password required")
             false
         }else{
             true

         }
    }

   private fun entryCheckUser(
        email:String,
        password: String
    ):UserLoginModel{
        return UserLoginModel(email , password)
    }

    fun checkUser(
        email:String,
        password:String
    ){
        val user = entryCheckUser(email, password)
        userLogin(user)
    }
    fun userResponse(response:String) {
        when (response) {
            "Not Found" -> state?.onFailure("Email or password is incorrect")

            "android_getaddrinfo failed: EAI_NODATA (No address associated with hostname)" -> state?.onFailure("Error Network")

            else -> state?.onSuccess()
        }
    }



}
