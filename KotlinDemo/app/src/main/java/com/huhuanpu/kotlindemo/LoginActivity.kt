package com.huhuanpu.kotlindemo

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.huhuanpu.kotlindemo.bean.LoginResponse
import com.huhuanpu.kotlindemo.bean.RegisterResponse
import com.huhuanpu.kotlindemo.presenter.LoginPresenter
import com.huhuanpu.kotlindemo.presenter.LoginPresenterImpl
import com.huhuanpu.kotlindemo.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

/**
 * Created by huhuanpu on 19-9-9
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginView {

    var loginPresenter: LoginPresenter? = null
    var dialog: SweetAlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginPresenter = LoginPresenterImpl(this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    /**
     * 点击
     */
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login ->
                if(checkContent(true)) {
                    dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("logging...")
                    dialog?.setCancelable(false)
                    dialog?.show()
                    loginPresenter?.login(username.text.toString(), password.text.toString())
                }
            R.id.register ->
                if(checkContent(false)) {
                    dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("registering...")
                    dialog?.setCancelable(false)
                    dialog?.show()
                    loginPresenter?.register(username.text.toString(), password.text.toString(), email.text.toString())
                }
        }

    }

    /**
     * 判断
     */
    private fun checkContent(login: Boolean) : Boolean {
        username.error = null
        password.error = null
        email.error = null

        var cancel = false
        var foucusView: View? = null

        if(!login) {
            if(TextUtils.isEmpty(email.text.toString())){
                email.error = "Email 不能为空"
                foucusView = email
                cancel = true
            } else if(!isEmail(email.text.toString())) {
                email.error = "Email格式不对"
                foucusView = email
                cancel = true
            }
        }

        if(TextUtils.isEmpty(password.text.toString())){
            password.error = "password cannot empty"
            foucusView = password
            cancel = true
        } else if(password.text.length < 6) {
            password.error = "password length cannot low than 6"
            foucusView = password
            cancel = true
        }

        if(TextUtils.isEmpty(username.text.toString())){
            username.error = "username cannot be empty"
            foucusView = username
            cancel = true
        }

        if(cancel) {
            // there was an error; don't attempt login and focus on the first
            // form field with an error
            if(foucusView != null){
                foucusView.requestFocus()
            }
        } else {
            return true
        }
        return false

    }


    /**
     * 判断是否是邮件
     * @param email
     * @return
     */
    private fun isEmail(email: String?): Boolean {
        if(email == null){
            return false
        }
        val regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(email).matches()
    }

    override fun loginSuccess(result: LoginResponse) {
        dialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
        dialog?.setTitleText(result.msg)
    }

    override fun loginFailed(msg: String?) {
        dialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        dialog?.setTitleText(msg)
    }

    override fun registerSuccess(result: RegisterResponse) {
        dialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
        dialog?.setTitleText(result.msg)
    }

    override fun registerFailed(msg: String?) {
        dialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        dialog?.setTitleText(msg)
    }


}