package me.sunnydaydev.tnews.domain.news.di

import dagger.Module
import dagger.Provides
import me.sunnydaydev.tnews.domain.news.db.NewsDataBase
import javax.inject.Singleton

/**
 * Created by sunny on 10.06.2018.
 * mail: mail@sunnydaydev.me
 */

@Module
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(factory: NewsDataBase.Factrory) = factory.create()

    @Provides
    fun newsDao(db: NewsDataBase) = db.newsDao

}