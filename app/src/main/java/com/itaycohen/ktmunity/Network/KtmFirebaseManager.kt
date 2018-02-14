package com.itaycohen.ktmunity.Network

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

/**
 * Created by Itay.Cohen on 09/01/2018.
 */

class KtmFirebaseManager(val context: Context) {

    lateinit var user: FirebaseUser
    var storage = FirebaseStorage.getInstance()

    fun downloadImage(childLocation: String, listener: RequestListener<StorageReference, Bitmap>) {
        val fileRef = storage.reference.child(childLocation)
        Glide.with(context)
                .using(FirebaseImageLoader())
                .load(fileRef)
                .asBitmap()
                .listener(listener)
    }
}