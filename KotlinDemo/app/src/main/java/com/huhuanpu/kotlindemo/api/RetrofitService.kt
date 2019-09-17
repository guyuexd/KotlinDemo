package com.huhuanpu.kotlindemo.api

import com.huhuanpu.kotlindemo.bean.LoginResponse
import com.huhuanpu.kotlindemo.bean.RegisterResponse
import com.huhuanpu.kotlindemo.constant.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by huhuanpu on 19-9-16
 */

interface RetrofitService {
    /**
     * 注册
     * key 用户申请的appKey
     * username 用户名(一个key只能存在唯一的username)
     * password 用户密码
     * email 电子邮件
     */
    @GET("register")
    fun userRegister(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("email") email: String,
        @Query("key") key: String = Constant.KEY
    ): Observable<RegisterResponse>

    /**
     * 登录
     * key 用户申请的appkey
     * username 用户名
     * password 用户密码
     */
    @GET("login")
    fun userLogin(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("key") key: String = Constant.KEY
    ): Observable<LoginResponse>
}