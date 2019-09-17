package com.huhuanpu.kotlindemo.bean

/**
 * Created by huhuanpu on 19-9-9
 *
 *登录返回result
 * token 用户生成的token
 * uid 用户Id
 */
data class LoginResultResponse(val token: String, val uid: String)