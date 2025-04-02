package com.example.avivhomeproject.property.data.di

import com.example.avivhomeproject.property.data.PropertyRepositoryImpl
import com.example.avivhomeproject.property.data.network.PropertyEndpoint
import com.example.avivhomeproject.property.domain.PropertyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PropertyDataModule {

    companion object {
        @Singleton
        @Provides
        fun providePropertyEndpoint(retrofit: Retrofit): PropertyEndpoint {
            return retrofit.create(PropertyEndpoint::class.java)
        }
    }

    @Binds
    fun bindPropertyRepository(searchRepositoryImpl: PropertyRepositoryImpl): PropertyRepository
}
