package com.itaycohen.ktmunity

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import java.util.logging.Logger

/**
 * Created by Itay.Cohen on 07/01/2018.
 */
class KtmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}

fun logd(msg: String) = Log.d("debug", msg)