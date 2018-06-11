package me.sunnydaydev.tnews.coregeneral

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface Toaster {

    fun makeToast(message: String, short: Boolean = true)

}

internal class ToasterImpl @Inject constructor(
        private val context: Context
): Toaster {

    override fun makeToast(message: String, short: Boolean) {
        val duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        Toast.makeText(context, message, duration).show()
    }

}