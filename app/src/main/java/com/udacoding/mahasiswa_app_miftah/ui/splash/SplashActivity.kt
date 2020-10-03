package com.udacoding.mahasiswa_app_miftah.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.helper.SessionManager
import com.udacoding.mahasiswa_app_miftah.ui.beranda.view.MainActivity
import com.udacoding.mahasiswa_app_miftah.ui.login.view.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val session = SessionManager(this)

        Handler().postDelayed(Runnable {

            if (session.login ?: true) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }, 2100)


        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_splash)
        animation?.setInterpolator(AnticipateOvershootInterpolator())
        animation?.setDuration(2000)
        img_splash.startAnimation(animation)
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
}