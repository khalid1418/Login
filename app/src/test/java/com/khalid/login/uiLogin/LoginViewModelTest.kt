package com.khalid.login.uiLogin

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.khalid.login.data.dataLayer.UserRepository
import com.khalid.login.data.dataSource.FakeDataSource
import com.khalid.login.domin.CheckUserUseCase
import com.khalid.login.model.UserLoginModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {
private lateinit var userUseCase: CheckUserUseCase
private lateinit var repository: UserRepository
private lateinit var fakeDataSource: FakeDataSource
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

@Before
fun setUp(){
    fakeDataSource = FakeDataSource()
    repository = UserRepository(fakeDataSource)
    userUseCase = CheckUserUseCase(repository)
}
    @Test
    fun `empty email return false`(){
        val result =LoginViewModel(userUseCase).entryValid("","1234")
        assertThat(result).isFalse()

    }
    @Test
    fun `email without @ return false`(){
        val result = LoginViewModel(userUseCase).entryValid("khalidgmail.com" , "1234")
        assertThat(result).isFalse()
    }
    @Test
    fun `email without dot return false `(){
        val result = LoginViewModel(userUseCase).entryValid("khalid@gmailcom" , "1234")
        assertThat(result).isFalse()
    }
    @Test
    fun `email correct and empty password return false`(){
        val result = LoginViewModel(userUseCase).entryValid("khalid@gmail.com","")
        assertThat(result).isFalse()
    }
    @Test
    fun `correct email and password return true`(){
        val result = LoginViewModel(userUseCase).entryValid("khalid@gmail.com" , "12345")
        assertThat(result).isTrue()
    }
    @Test
    fun `incorrect email , correct password and online Network`(){
         LoginViewModel(userUseCase).userLogin(UserLoginModel("khalid1111@gmail.com" , "12345"))
        fakeDataSource.Network(true)
        val value = LoginViewModel(userUseCase).userResponse(fakeDataSource.checkUser.value!!)
        assertThat(value).isFalse()
    }

    @Test
    fun `correct email , incorrect password and online Network`(){
        fakeDataSource.Network(true)
        LoginViewModel(userUseCase).userLogin(UserLoginModel("khalid@gmail.com" , "1234567"))
        val value = LoginViewModel(userUseCase).userResponse(fakeDataSource.checkUser.value!!)
        assertThat(value).isFalse()
    }
    @Test
    fun `correct email , correct password and offline Network`(){
        fakeDataSource.Network(false)
        LoginViewModel(userUseCase).userLogin(UserLoginModel("khalid@gmail.com" , "12345"))
        val value = LoginViewModel(userUseCase).userResponse(fakeDataSource.checkUser.value!!)
        assertThat(value).isFalse()
    }
    @Test
    fun `correct email , correct password and online Network`(){
        fakeDataSource.Network(true)
        LoginViewModel(userUseCase).userLogin(UserLoginModel("khalid@gmail.com" , "12345"))
        val value = LoginViewModel(userUseCase).userResponse(fakeDataSource.checkUser.value!!)
        assertThat(value).isTrue()
    }
}