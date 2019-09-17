package com.huhuanpu.kotlindemo.model

import com.huhuanpu.kotlindemo.presenter.LoginPresenter
import com.huhuanpu.kotlindemo.util.RetrofitUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by huhuanpu on 19-9-12
 */
class LoginModelImpl : LoginModel {

    var mOnLoginListener: LoginPresenter.onLoginListener? = null
    var monRegisterListener: LoginPresenter.onRegisterListener? = null


    override fun login(onLoginListener: LoginPresenter.onLoginListener, username: String, password: String) {
        if (mOnLoginListener == null) {
            mOnLoginListener = onLoginListener
        }
        RetrofitUtils.retrofitService
            .userLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                when (result.retCode) {
                    "200" -> mOnLoginListener?.loginSuccess(result)
                    else -> mOnLoginListener?.loginFailded(result.msg)
                }
            },
                { error ->
                    mOnLoginListener?.loginFailded(error.message)
                })
    }

    override fun register(
        onRegisterListener: LoginPresenter.onRegisterListener,
        username: String,
        password: String,
        email: String
    ) {
        RetrofitUtils
            .retrofitService
            .userRegister(username, password, email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result ->
                when(result.retCode){
                    "200" -> monRegisterListener?.registerSuccess(result)
                    else -> monRegisterListener?.registerFailed(result.msg)
                }
            },
                {
                    error -> monRegisterListener?.registerFailed(error.message)
                })
    }


}