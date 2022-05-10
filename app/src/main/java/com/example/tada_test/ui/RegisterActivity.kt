package com.example.tada_test.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tada_test.R
import com.example.tada_test.dependencyInjection.component.BaseApp
import com.example.tada_test.dialog.ErrorDialog
import com.example.tada_test.viewmodel.LoginAndRegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var userLoginViewModelFactory: ViewModelProvider.Factory
    private val userLoginVM by viewModels<LoginAndRegisterViewModel> { userLoginViewModelFactory }
    private var mErrorDialog: ErrorDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (application as BaseApp).appComponent.userLoginTaskComponent().create().inject(this)

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

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
    }


    private fun initEvent() {
        btn_submit.setOnClickListener {
            if(et_password.text.isEmpty() || et_username.text.isEmpty() || !cb_terms_condition.isChecked) {
                var errorMsg = ""
                if(et_password.text.isEmpty() || et_username.text.isEmpty()) {
                    errorMsg+= "Please Fill Username Or Password\n"
                }
                if(!cb_terms_condition.isChecked) {
                    errorMsg+= "Please Check Terms and Condition First"
                }
                mErrorDialog?.setDialogData("Register Error", errorMsg)
                mErrorDialog?.show()
            }
            else {
                userLoginVM.requestAddDataUser(et_username.text.toString(), et_password.text.toString())
            }
        }

        userLoginVM.ldAddUserData.observe(this, Observer {
            it?.let {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        userLoginVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Register Error", it.message.toString())
                mErrorDialog?.show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}