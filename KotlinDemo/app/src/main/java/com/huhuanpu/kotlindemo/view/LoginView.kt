package com.huhuanpu.kotlindemo.view

import com.huhuanpu.kotlindemo.bean.LoginResponse
import com.huhuanpu.kotlindemo.bean.RegisterResponse

/**
 * Created by huhuanpu on 19-9-9
 */
interface LoginView {

    /**
     *登录成功
     */
    fun loginSuccess(result: LoginResponse)

    /**
     *登录失败
     */
    fun loginFailed(msg: String?)

    /**
     *注册成功
     */
    fun registerSuccess(result: RegisterResponse)

    /**
     * 注册失败
     */
    fun registerFailed(msg: String?)

}