package com.itaycohen.ktmunity.UI.Views

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.Log


/**
 * Created by Itay.Cohen on 18/01/2018.
 */
class KtmCardView : CardView {

    init {
        Log.d("tag", "init")
    }

    constructor(context: Context) : super(context) {
        Log.d("tag", "construc")
    }

    constructor(context: Context, attrSet: AttributeSet) : super(context, attrSet) {
        initView()
    }
    constructor(context: Context, attrSet: AttributeSet, defStyle: Int) : super(context, attrSet, defStyle) {
        initView()
    }

    private fun initView() {

    }



}