package com.itaycohen.ktmunity.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.itaycohen.ktmunity.R
import com.itaycohen.ktmunity.logd
import java.util.*


class SplashActivity : KtmBaseActivity() {

    private val IS_FIRST_LOGIN_KEY = "IS_FIRST_LOGIN_KEY"
    private var isFirstLogin : Boolean
        get() = sharedPref.getBoolean(IS_FIRST_LOGIN_KEY, true)
        set(value){
            sharedPref.edit().putBoolean(IS_FIRST_LOGIN_KEY, value).apply()
        }
    private val REQUEST_CODE_SIGN_IN = 11
    private lateinit var backgroundImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initThemeIfAllowed()
        startAuthActivityForResult()
    }

    private fun initThemeIfAllowed(): Boolean {
        if (isLollipopOrHigher)
            application.setTheme(ktmTheme)
        else {
            application.setTheme(R.style.brightBoxesAppTheme)
            return false
        }
        return true
    }

    private fun startAuthActivityForResult() {
        val authProviders = listOf(
                AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build())
        val intent = AuthUI
                .getInstance()
                .createSignInIntentBuilder()
                .setTosUrl(getString(R.string.terms_link))
                .setPrivacyPolicyUrl(getString(R.string.terms_link))
                .setTheme(R.style.AuthActivityTheme)
                .setAvailableProviders(authProviders)
                .build()
        startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode != REQUEST_CODE_SIGN_IN)
            return

        if (resultCode == Activity.RESULT_OK) {
            firebaseManager.user = FirebaseAuth.getInstance().currentUser!!
            val intent = Intent(this, MainActivity::class.java)
        } else {
            finish();
        }

    }

    private fun downloadBackgroundAuthImage() {
        val imagesCount = resources.getInteger(R.integer.authActivityImagesPoolCount)
        //firebaseManager.downloadImage(getString(R.string.fireBase_authActivityBackgroundImageRef, (1..imagesCount).random()), )
    }
}

private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive+1 - start) + start
