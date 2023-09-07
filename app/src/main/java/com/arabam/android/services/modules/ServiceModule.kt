package com.arabam.android.services.modules

import com.arabam.android.services.AdvertAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAPIService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sandbox.arabamd.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAdvertAPI(retrofit: Retrofit): AdvertAPI {
        return retrofit.create(AdvertAPI::class.java)
    }

}