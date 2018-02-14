package com.itaycohen.ktmunity.UI

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.itaycohen.ktmunity.BuildConfig
import com.itaycohen.ktmunity.R
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.itaycohen.ktmunity.Network.KtmFirebaseManager



/**
 * Created by Itay.Cohen on 07/01/2018.
 */
open abstract class KtmBaseActivity : AppCompatActivity() {

    val isLollipopOrHigher= Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    protected val sharedPref: SharedPreferences
        get() = getPreferences(Context.MODE_PRIVATE)
    protected var ktmTheme = R.style.brightBoxesAppTheme
        get() {
            return if (sharedPref.getBoolean(IS_DARK_THEME_KEY, true))
                R.style.brightBoxesAppTheme else
                R.style.DarkBoxesAppTheme
        }
        set(value) {
            sharedPref.edit().putBoolean(IS_DARK_THEME_KEY, (value == R.style.brightBoxesAppTheme))
            field = value
        }
    protected val firebaseManager = KtmFirebaseManager(this)

    private val IS_DARK_THEME_KEY = "${BuildConfig.APPLICATION_ID}.isDarkTheme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar, menu)
        val logoutItem = menu?.findItem(R.id.logoutBtn)
        logoutItem?.icon = resources.getDrawable(if (ktmTheme == R.style.brightBoxesAppTheme)
            R.drawable.logout_black700 else
            R.drawable.logout_white100)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.logoutBtn -> showLogoutDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLogoutDialog(){
        val dialogStyleRef = if (ktmTheme == R.style.brightBoxesAppTheme)
            R.style.brightBoxDialog else
            R.style.darkBoxDialog
        val alertDialogBuilder = if (isLollipopOrHigher)
            AlertDialog.Builder(this, dialogStyleRef) else
            AlertDialog.Builder(this)
        val alertDialogFrag = alertDialogBuilder
                .setTitle(getString(R.string.shouldLogOut))
                .setCancelable(true)
                .setNegativeButton(getString(R.string.no), null)
                .setPositiveButton(getString(R.string.yesLogout),
                        DialogInterface.OnClickListener { dialogInterface, i -> logout() })
                .setIcon(if (ktmTheme == R.style.brightBoxesAppTheme)
                    R.drawable.alert_black  else
                    android.R.drawable.ic_dialog_alert)
                .create()
        alertDialogFrag.window.setGravity(Gravity.CENTER);
        alertDialogFrag.show()
    }

    private fun logout() {
        AuthUI.getInstance().signOut(this)
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }

}



