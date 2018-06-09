package me.sunnydaydev.tnews.coregeneral.di

import android.content.Context
import dagger.*
import me.sunnydaydev.tnews.coregeneral.CoreInitializer
import me.sunnydaydev.tnews.coregeneral.CoreInitializerIml
import me.sunnydaydev.tnews.coregeneral.Prefs
import me.sunnydaydev.tnews.coregeneral.PrefsImpl
import javax.inject.Singleton

@Singleton
@Component(
        modules = [CoreProvidesModule::class]
)
interface CoreComponent: CoreProvider, AppInitializerProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): CoreComponent.Builder

        fun build(): CoreComponent

    }

    object Initializer {

        fun init(context: Context): CoreComponent {
            return DaggerCoreComponent.builder()
                    .context(context)
                    .build()
        }

    }

}

interface CoreProvider {

    val applicationContext: Context

    val prefs: Prefs

}

interface AppInitializerProvider {

    val coreInitializer: CoreInitializer

}

@Module
internal interface CoreProvidesModule {

    @Binds
    fun bindAppInitializer(impl: CoreInitializerIml): CoreInitializer

    @Binds
    fun bindPrefs(impl: PrefsImpl): Prefs

}