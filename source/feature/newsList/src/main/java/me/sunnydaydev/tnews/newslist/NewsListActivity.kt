package me.sunnydaydev.tnews.newslist

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.sunnydaydev.tnews.coreui.util.createIntent

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

class NewsListActivity : AppCompatActivity() {

    companion object {

        fun intent(ctx: Context) = createIntent<NewsListActivity>(ctx)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newslist_activity)
    }

}