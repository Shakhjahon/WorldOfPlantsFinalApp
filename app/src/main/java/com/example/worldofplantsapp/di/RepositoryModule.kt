package com.example.worldofplantsapp.di

import com.example.data.local.UserSharedPref
import com.example.data.local.UserSharedPrefImpl
import com.example.data.repository.AuthorRepositoryImpl
import com.example.data.repository.CategoryRepositoryImpl
import com.example.data.repository.DetailsRepositoryImpl
import com.example.data.repository.GetPlantRepositoryImpl
import com.example.domain.repository.author.repository.AuthorRepository
import com.example.domain.repository.category.repository.CategoryRepository
import com.example.domain.repository.detail.repository.DetailsRepository
import com.example.domain.repository.getPlant.repository.GetPlantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserAuthRepository(
        implementation: AuthorRepositoryImpl
    ): AuthorRepository

    @Binds
    fun bindUserSharedPref(
        impl: UserSharedPrefImpl
    ): UserSharedPref

    @Binds
    fun bindFetchRepository(
        impl: GetPlantRepositoryImpl
    ): GetPlantRepository

    @Binds
    fun bindDetailsRepository(
        impl: DetailsRepositoryImpl
    ): DetailsRepository

    @Binds
    fun bindCategoryItems(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}