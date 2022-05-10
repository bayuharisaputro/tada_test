package com.example.tada_test.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tada_test.R
import com.example.tada_test.dependencyInjection.component.BaseApp
import com.example.tada_test.dialog.ErrorDialog
import com.example.tada_test.util.SharedPref
import com.example.tada_test.viewmodel.LoginAndRegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {
    private var mErrorDialog: ErrorDialog? = null
    private lateinit var sharedPref: SharedPref

    @Inject
    lateinit var userLoginViewModelFactory: ViewModelProvider.Factory
    private val userLoginVM by viewModels<LoginAndRegisterViewModel> { userLoginViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = SharedPref(this)
        (application as BaseApp).appComponent.userLoginTaskComponent().create().inject(this)

        initView()
        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mErrorDialog?.isShowing == true) {
            mErrorDialog?.dismiss()
        }
    }

    private fun initView() {
        mErrorDialog = ErrorDialog(this)
        val txtRegisterDetail = "Don’t have account ? Register"
        val txtRegister = "Register"
        val spanRegister = SpannableString(txtRegisterDetail)
        val posRegister = txtRegisterDetail.indexOf(txtRegister, 0, true)
        if (posRegister != -1) {
            val clickSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)

                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                    ds.color = Color.parseColor("#d84372")
                }
            }
            spanRegister.setSpan(clickSpan, posRegister,
                posRegister + txtRegister.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        txt_register.text = spanRegister
        txt_register.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun initEvent() {
        btn_submit.setOnClickListener {
            if(et_password.text.isEmpty() || et_username.text.isEmpty()) {
                mErrorDialog?.setDialogData("Login Error", "Please Fill Username Or Password")
                mErrorDialog?.show()
            }
            else {
                userLoginVM.requestGetDataUser(et_username.text.toString(),et_password.text.toString() )
            }
        }

        userLoginVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Login Error", it.message.toString())
                mErrorDialog?.show()
            }
        })

        userLoginVM.ldDataLogin.observe(this, Observer {
            it?.let {
                sharedPref.setUsername(it.username)
                sharedPref.setLogin(true)
                val intent = Intent(this@LoginActivity, MainActivity2::class.java)
                startActivity(intent)
                finish()
            }
        })
    }
}