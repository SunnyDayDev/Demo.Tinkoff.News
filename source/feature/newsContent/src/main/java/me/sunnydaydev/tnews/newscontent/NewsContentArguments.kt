package me.sunnydaydev.tnews.newscontent

import android.os.Bundle
import me.sunnydaydev.tnews.coregeneral.util.bundleString

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

internal typealias NewsContentParams = Bundle

internal var NewsContentParams.id: String by bundleString("news.content.id")
internal var NewsContentParams.titleTransitionName: String by bundleString("news.content.titleTransitionName")