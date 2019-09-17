package com.huhuanpu.kotlindemo.presenter

import com.huhuanpu.kotlindemo.bean.LoginResponse
import com.huhuanpu.kotlindemo.bean.RegisterResponse
import com.huhuanpu.kotlindemo.model.LoginModel
import com.huhuanpu.kotlindemo.model.LoginModelImpl
import com.huhuanpu.kotlindemo.view.LoginView

/**
 * Created by huhuanpu on 19-9-12
 */
class LoginPresenterImpl (val loginview: LoginView) : LoginPresenter, LoginPresenter.onLoginListener, LoginPresenter.onRegisterListener{

    val mLoginModel: LoginModel

    init {
        mLoginModel = LoginModelImpl()
    }

    override fun login(username: String, pass: String) {
        mLoginModel.login(this, username, pass)
    }

    override fun loginSuccess(result: LoginResponse) {
        loginview.loginSuccess(result)
    }

    override fun loginFailded(msg: String?) {
        loginview.loginFailed(msg)
    }

    override fun register(name: String, pass: String, email: String) {
        mLoginModel.register(this, name, pass, email)
    }

    override fun registerSuccess(result: RegisterResponse) {
        loginview.registerSuccess(result)
    }

    override fun registerFailed(msg: String?) {
        loginview.registerFailed(msg)
    }

}