package com.huhuanpu.kotlindemo.presenter

import com.huhuanpu.kotlindemo.bean.LoginResponse
import com.huhuanpu.kotlindemo.bean.RegisterResponse

/**
 * Created by huhuanpu on 19-9-12
 */

interface LoginPresenter {

    /**
     * login
     */
    fun login(username: String, pass: String)

    /**
     * login interface
     */
    interface onLoginListener {
        /**
         * login success
         */
        fun loginSuccess(result: LoginResponse)

        /**
         * login failed
         */
        fun loginFailded(msg : String?)
    }

    /**
     * register
     */
    fun register(name: String, pass: String, email: String)

    /**
     * register interface
     */
    interface onRegisterListener{
        /**
         * register success
         */
        fun registerSuccess(result: RegisterResponse)

        /**
         * register failed
         */
        fun registerFailed(msg: String?)
    }
}