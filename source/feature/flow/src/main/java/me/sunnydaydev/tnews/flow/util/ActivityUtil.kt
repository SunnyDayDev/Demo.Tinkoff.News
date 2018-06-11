package me.sunnydaydev.tnews.flow.util

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ActionBarContainer
import me.sunnydaydev.mvvmkit.util.find

/**
 * Created by sunny on 11.06.2018.
 * mail: mail@sunnydaydev.me
 */

val Activity.actionBarView: View?
    get() = (findViewById<View>(android.R.id.content).parent as ViewGroup)
            .let { parent -> parent.find { it is ActionBarContainer && it.parent === parent } }