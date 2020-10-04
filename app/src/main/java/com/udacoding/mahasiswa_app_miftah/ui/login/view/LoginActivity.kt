package com.udacoding.mahasiswa_app_miftah.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.helper.SessionManager
import com.udacoding.mahasiswa_app_miftah.model.getLogin.DataUser
import com.udacoding.mahasiswa_app_miftah.model.getLogin.ResponseLogin
import com.udacoding.mahasiswa_app_miftah.ui.beranda.view.MainActivity
import com.udacoding.mahasiswa_app_miftah.ui.login.viewModel.ViewModelLogin
import com.udacoding.mahasiswa_app_miftah.ui.register.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(ViewModelLogin::class.java)

        tv_registrasi.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn_login.setOnClickListener {
            val email = edt_email_login.text.toString()
            val password = edt_password_login.text.toString()

            if (email.isNotEmpty() != password.isNotEmpty()) {
                if (email.isEmpty()) {
                    edt_email_login.error = "Lengkapi Email"
                }

                if (password.isEmpty()) {
                    edt_password_login.error = "Lengkapi Email"
                }
            } else {
                viewModel.login(email, password)
            }
        }

        atttachObser()
    }

    private fun atttachObser() {
        viewModel.response.observe(this, Observer { loginSuccess(it) })
        viewModel.onError.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.isEmpty.observe(this, Observer { showEmpty(it) })
    }

    private fun correctPass(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showEmpty(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) pb_login.visibility = View.VISIBLE else pb_login.visibility = View.GONE
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun loginSuccess(user: ResponseLogin) {
        val session = SessionManager(this)
        session.email = user.data?.get(0)?.userEmail
        session.name = user.data?.get(0)?.userNama
        session.login = true
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}