package com.huhuanpu.kotlindemo.model

import com.huhuanpu.kotlindemo.presenter.LoginPresenter

/**
 * Created by huhuanpu on 19-9-12
 */
interface LoginModel {
    /**
     * login
     */
    fun login(onLoginListener: LoginPresenter.onLoginListener, username: String, password: String)

    /**
     * register
     */
    fun register(onRegisterListener: LoginPresenter.onRegisterListener, username: String, password: String, email: String)
}