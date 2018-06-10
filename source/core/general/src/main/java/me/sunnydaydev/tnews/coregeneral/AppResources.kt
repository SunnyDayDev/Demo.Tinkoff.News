package me.sunnydaydev.tnews.coregeneral

import android.content.Context
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface AppResources {

    val strings: Strings

    interface Strings {

        operator fun get(name: Int): String

        operator fun get(name: Int, vararg args: Any): String

    }

}

internal class AppResourcesImpl @Inject constructor(
        private val context: Context
): AppResources {

    override val strings: AppResources.Strings get() = StringsImpl(context)

}

internal class StringsImpl(private val context: Context): AppResources.Strings {

    override fun get(name: Int): String = context.getString(name)

    override fun get(name: Int, vararg args: Any): String = context.getString(name, args)

}