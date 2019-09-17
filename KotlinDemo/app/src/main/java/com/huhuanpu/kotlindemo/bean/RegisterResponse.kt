package com.huhuanpu.kotlindemo.bean

/**
 * Created by huhuanpu on 19-9-9
 */

/**
 * 注册返回
 * retCode 返回码
 * msg 返回说明
 * uid 用户Id
 */
data class RegisterResponse(val retCode: String, val msg: String, val uid: String?)