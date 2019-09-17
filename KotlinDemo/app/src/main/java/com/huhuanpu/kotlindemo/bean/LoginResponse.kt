package com.huhuanpu.kotlindemo.bean

/**
 * 登录返回
 * retCode 返回吗
 * msg 返回说明
 * result 返回结果
 * msg错误码:
 * 200 成功
 * 10001 appkey 不合法
 */
data class LoginResponse(val msg: String, val result: LoginResultResponse, val retCode: String)