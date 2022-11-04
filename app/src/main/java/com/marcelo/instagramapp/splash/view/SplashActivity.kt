package com.marcelo.instagramapp.splash.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.instagramapp.common.base.DependencyInjector
import com.marcelo.instagramapp.databinding.ActivitySplashBinding
import com.marcelo.instagramapp.login.view.LoginActivity
import com.marcelo.instagramapp.main.MainActivity
import com.marcelo.instagramapp.splash.Splash
import com.marcelo.instagramapp.splash.presentation.SplashPresenter

class SplashActivity : AppCompatActivity(), Splash.View {

    private lateinit var binding: ActivitySplashBinding

    override lateinit var presenter: Splash.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = SplashPresenter(this, DependencyInjector.splashRepository())

        binding.splashImg.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    presenter.authenticated()
                }
            })
            duration = 1000
            alpha(1.0f)
            start()
        }


    }

    override fun goToMainScreen() {
        binding.splashImg.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(baseContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            })
            duration = 1000
            alpha(0.0f)
            start()
        }


    }

    override fun goToLoginScreen() {
        binding.splashImg.animate().apply {
            setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(baseContext, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            })
            duration = 1000
            alpha(0.0f)
            start()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}