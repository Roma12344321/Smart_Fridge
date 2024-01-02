package com.dev.smart_fridge.di

import android.app.Application
import android.content.Context
import com.dev.smart_fridge.presentation.fragments.FridgeFragment
import com.dev.smart_fridge.presentation.fragments.RecipeDetailInformationActivity
import com.dev.smart_fridge.presentation.fragments.RecipeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: FridgeFragment)

    fun inject(fragment: RecipeFragment)

    fun inject(activity : RecipeDetailInformationActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}