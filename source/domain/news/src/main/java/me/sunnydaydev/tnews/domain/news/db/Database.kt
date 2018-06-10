package me.sunnydaydev.tnews.domain.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Inject

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Database(
        version = 1,
        entities = [
            NewsEntity::class
        ],
        exportSchema = true
)
internal abstract class NewsDataBase: RoomDatabase() {

    abstract val newsDao: NewsDao

    class Factrory @Inject constructor(private val context: Context) {

        fun create(): NewsDataBase = Room
                .databaseBuilder(context, NewsDataBase::class.java, "news.db")
                .build()

    }

}