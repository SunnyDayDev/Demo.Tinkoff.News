package me.sunnydaydev.tnews.newscontent

import android.content.Context
import android.os.Bundle
import me.sunnydaydev.mvvmkit.BaseMVVMActivity
import me.sunnydaydev.tnews.coreui.util.createIntent

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class NewsContentActivity : BaseMVVMActivity() {

    companion object {

        fun intent(
                id: String,
                titleTransitionName: String,
                ctx: Context
        ) = createIntent<NewsContentActivity>(ctx).apply {

            Bundle().apply {
                this.id = id
                this.titleTransitionName = titleTransitionName
            }.let(::putExtras)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.newscontent_activity)

        supportPostponeEnterTransition()

        val fragment = NewsContentFragment().apply {
            arguments = intent.extras
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_content, fragment)
                .commit()

    }

}