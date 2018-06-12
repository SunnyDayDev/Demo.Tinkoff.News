package me.sunnydaydev.tnews.coregeneral

import android.content.Context
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface AppResources {

    val strings: Strings

    val dimens: Dimens

    interface Strings {

        operator fun get(name: Int): String

        operator fun get(name: Int, vararg args: Any): String

    }

    interface Dimens {

        fun getDimensionPixelSize(name: Int): Int

    }

}

internal class AppResourcesImpl @Inject constructor(
        private val context: Context
): AppResources {

    override val strings: AppResources.Strings get() = StringsImpl(context)

    override val dimens: AppResources.Dimens get() = DimensImpl(context)

}

internal class StringsImpl(private val context: Context): AppResources.Strings {

    override fun get(name: Int): String = context.getString(name)

    override fun get(name: Int, vararg args: Any): String = context.getString(name, args)

}

internal class DimensImpl(context: Context): AppResources.Dimens {

    private val res = context.resources

    override fun getDimensionPixelSize(name: Int): Int = res.getDimensionPixelSize(name)


}