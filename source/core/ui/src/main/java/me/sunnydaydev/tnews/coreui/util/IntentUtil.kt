package me.sunnydaydev.tnews.coreui.util

import android.content.Context
import android.content.Intent
import me.sunnydaydev.mvvmkit.util.createIntent

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

inline fun <reified T: Context> createIntent(ctx: Context) = Intent(ctx, T::class.java)