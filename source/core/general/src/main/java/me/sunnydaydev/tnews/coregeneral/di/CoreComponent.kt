package me.sunnydaydev.tnews.coregeneral.di

import android.content.Context
import dagger.*
import me.sunnydaydev.tnews.coregeneral.*
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

    val toaster: Toaster

    val appResources: AppResources

}

interface AppInitializerProvider {

    val coreInitializer: CoreInitializer

}

@Module
internal interface CoreProvidesModule {

    @Binds
    fun bindAppInitializer(impl: CoreInitializerIml): CoreInitializer

    @Binds
    fun bindToaster(impl: ToasterImpl): Toaster

    @Binds
    fun bindAppResources(impl: AppResourcesImpl): AppResources

}