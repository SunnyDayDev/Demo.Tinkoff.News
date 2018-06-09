package me.sunnydaydev.tnews.coreui.util

import androidx.annotation.IdRes

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface HasFragmentContainer {

    @get:IdRes
    val fragmentContainerId: Int

}