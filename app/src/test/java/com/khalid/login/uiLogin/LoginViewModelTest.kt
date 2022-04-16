package com.khalid.login.uiLogin

import com.google.common.truth.Truth.assertThat
import com.khalid.login.data.dataLayer.UserRepository
import com.khalid.login.data.dataSource.FakeDataSource
import com.khalid.login.domin.CheckUserUseCase
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
private lateinit var userUseCase: CheckUserUseCase
private lateinit var repository: UserRepository
private lateinit var fakeDataSource: FakeDataSource

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

}