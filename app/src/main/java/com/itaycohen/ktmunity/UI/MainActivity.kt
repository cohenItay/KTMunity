package com.itaycohen.ktmunity.UI

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.itaycohen.ktmunity.R
import com.itaycohen.ktmunity.UI.Views.KtmCardView
import com.itaycohen.ktmunity.databinding.ActivityMainBinding

class MainActivity : KtmBaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binder = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //binder.splashGreeting.setText(getString(R.string.splashScreen_Greetings, SplashUser.name))
        //binder.splashRootLayout.setBackgroundResource()
    }




}
