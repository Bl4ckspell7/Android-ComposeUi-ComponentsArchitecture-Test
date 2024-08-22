package com.example.uicomponentstest.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.uicomponentstest.data.repository.product.ProductRepository
import com.example.uicomponentstest.data.repository.product.ProductRepositoryImpl
import com.example.uicomponentstest.data.repository.settings.SettingsRepository
import com.example.uicomponentstest.data.repository.settings.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideSettingsRepository(dataStore: DataStore<Preferences>): SettingsRepository =
        SettingsRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = ProductRepositoryImpl()
}