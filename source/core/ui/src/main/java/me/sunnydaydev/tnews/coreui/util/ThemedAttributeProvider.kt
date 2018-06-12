package me.sunnydaydev.tnews.coreui.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue



/**
 * Created by sunny on 12.06.2018.
 * mail: mail@sunnydaydev.me
 */

class ThemedAttributeProvider(
        private val theme: Resources.Theme,
        private val context: Context
) {

    fun getDimensionPixelSize(attrId: Int): Int {
        val tv = TypedValue()
        if (theme.resolveAttribute(attrId, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        } else {
            error("Attr not resolved.")
        }
    }

}