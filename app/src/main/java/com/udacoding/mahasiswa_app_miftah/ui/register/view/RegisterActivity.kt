package com.udacoding.mahasiswa_app_miftah.ui.register.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.ui.login.view.LoginActivity
import com.udacoding.mahasiswa_app_miftah.ui.register.viewModel.ViewModelRegister
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelRegister

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this).get(ViewModelRegister::class.java)

        attachObserv()
        initData()
    }

    private fun attachObserv() {
        viewModel.response.observe(this, Observer { initData() })
        viewModel.onError.observe(this, Observer { showError(it) })
        viewModel.passLengt.observe(this, Observer { passLengt(it) })
        viewModel.passConfirm.observe(this, Observer { passConfirm(it) })
        viewModel.passEmpty.observe(this, Observer { passEmpty(it) })
        viewModel.onIntent.observe(this, Observer { onIntent(it) })
    }

    private fun onIntent(it: Boolean?) {
        if (it == true) {
            startActivity(Intent(this, LoginActivity::class.java))
            showToast("Berhasil")

        } else {
            showToast("Gagal")
        }
    }

    private fun passEmpty(it: String) {
        showToast(it)
    }

    private fun passConfirm(it: String) {
        showToast(it)
    }

    private fun passLengt(it: String) {
        showToast(it)
    }

    private fun showError(it: Throwable) {
        showToast(it.localizedMessage)
    }

    private fun initData() {
        //ambil data inputan
        btn_daftar.setOnClickListener {
            val username = edt_username.text.toString()
            val email = edt_email.text.toString()
            val password = edt_password.text.toString()
            val passwordConfirm = edt_password_confirm.text.toString()

            viewModel.register(username, email, password, passwordConfirm)
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}