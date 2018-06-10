package me.sunnydaydev.tnews.newscontent

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.sunnydaydev.tnews.coreui.util.createIntent

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class NewsContentActivity : AppCompatActivity() {

    companion object {

        fun intent(id: String, ctx: Context) = createIntent<NewsContentActivity>(ctx).apply {
            Bundle().apply {
                newsId = id
            }.let(::putExtras)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newscontent_activity)

        val fragment = NewsContentFragment().apply {
            arguments = intent.extras
        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit()

    }

}