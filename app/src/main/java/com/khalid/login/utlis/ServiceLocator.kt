package com.khalid.login.utlis

import com.khalid.login.data.dataLayer.UserRemoteDataSource
import com.khalid.login.data.dataLayer.UserRepository
import com.khalid.login.data.network.MyApi
import com.khalid.login.domin.CheckUserUseCase

fun providerUserApi():MyApi = MyApi.invoke()

fun providerUserRemoteDataSource():UserRemoteDataSource = UserRemoteDataSource(providerUserApi())

fun providerUserRepository():UserRepository = UserRepository(providerUserRemoteDataSource())

fun providerCheckUserUseCase():CheckUserUseCase = CheckUserUseCase(providerUserRepository())