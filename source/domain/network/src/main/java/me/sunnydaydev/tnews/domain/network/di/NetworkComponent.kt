package me.sunnydaydev.tnews.domain.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.stringBased
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.JSON
import me.sunnydaydev.tnews.domain.network.TinkoffApi
import me.sunnydaydev.tnews.domain.network.NewsNetworkService
import me.sunnydaydev.tnews.domain.network.NewsNetworkServiceImpl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by sunny on 09.06.2018.
 * mail: mail@sunnydaydev.me
 */

interface NetworkServicesProvider {

    val newsNetworkService: NewsNetworkService

}

@Component(modules = [NetworkModule::class, NetworkServicesModule::class])
interface NetworkComponent: NetworkServicesProvider {

    object Initializer {

        fun init(): NetworkComponent {
            return DaggerNetworkComponent.builder()
                    .build()
        }

    }

}


@Module
internal class NetworkModule {

    @Provides
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Provides
    fun provideJSON(): JSON {
        return JSON()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, json: JSON): Retrofit.Builder {

        val mediaType = MediaType.parse("application/json")!!
        val jsonNewsList = stringBased(mediaType, json::parse, json::stringify)

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(jsonNewsList)
                .client(client)

    }

    @Provides
    fun provideCurrencyApi(retrofit: Retrofit.Builder): TinkoffApi {

        return retrofit
                .baseUrl(TinkoffApi.Urls.BASE_URL)
                .build()
                .create(TinkoffApi::class.java)

    }

}

@Module
internal interface NetworkServicesModule {

    @Binds
    fun bindCurrenciesNetworkService(impl: NewsNetworkServiceImpl): NewsNetworkService

}