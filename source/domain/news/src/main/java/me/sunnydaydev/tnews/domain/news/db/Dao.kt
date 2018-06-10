package me.sunnydaydev.tnews.domain.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Dao
internal interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<NewsEntity>)

    @get:Query("SELECT * FROM news")
    val allNews: Flowable<List<NewsEntity>>

}