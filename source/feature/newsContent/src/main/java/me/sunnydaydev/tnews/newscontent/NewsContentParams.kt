package me.sunnydaydev.tnews.newscontent

import android.os.Bundle
import me.sunnydaydev.tnews.coregeneral.util.bundleString

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal typealias NewsContentParams = Bundle

internal var NewsContentParams.id: String by bundleString("news.content.id")
internal var NewsContentParams.title: String by bundleString("news.content.title")
internal var NewsContentParams.titleTransitionName: String by bundleString("news.content.titleTransitionName")

internal object NewsContentParamsFactory {

    fun create(
            id: String,
            title: String,
            titleTransitionName: String
    ): NewsContentParams = Bundle().apply {
        this.id = id
        this.title = title
        this.titleTransitionName = titleTransitionName
    }

}
