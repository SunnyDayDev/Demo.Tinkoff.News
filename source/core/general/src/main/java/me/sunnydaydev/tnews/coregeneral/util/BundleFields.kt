package me.sunnydaydev.tnews.coregeneral.util

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

fun bundleString(name: String, defaultValue: String = "") = object: ReadWriteProperty<Bundle, String> {

    override fun getValue(thisRef: Bundle, property: KProperty<*>): String =
            thisRef.getString(name, defaultValue)

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: String) {
        thisRef.putString(name, value)
    }

}