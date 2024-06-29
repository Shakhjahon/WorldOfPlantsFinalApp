package com.example.worldofplantsapp.di

import com.example.domain.repository.author.repository.AuthorRepository
import com.example.domain.repository.category.repository.CategoryRepository
import com.example.domain.repository.detail.repository.DetailsRepository
import com.example.domain.repository.getPlant.repository.GetPlantRepository
import com.example.domain.usecases.author.login.LoginUseCase
import com.example.domain.usecases.author.login.LoginUseCaseImpl
import com.example.domain.usecases.author.register.RegisterUseCase
import com.example.domain.usecases.author.register.RegisterUseCaseImpl
import com.example.domain.usecases.category.CategoryUseCase
import com.example.domain.usecases.category.CategoryUseCaseImpl
import com.example.domain.usecases.detail.DetailsUseCase
import com.example.domain.usecases.detail.DetailsUseCaseImpl
import com.example.domain.usecases.local.delete.ClearTableUseCase
import com.example.domain.usecases.local.delete.ClearTableUseCaseImpl
import com.example.domain.usecases.local.delete.DeletePlantByIdUseCase
import com.example.domain.usecases.local.delete.DeletePlantByIdUseCaseImpl
import com.example.domain.usecases.local.observe.FetchAllSaveUseCase
import com.example.domain.usecases.local.observe.FetchAllSaveUseCaseImpl
import com.example.domain.usecases.local.observe.ObserveIsPlantSavedById
import com.example.domain.usecases.local.observe.ObserveIsPlantSavedByIdImpl
import com.example.domain.usecases.local.save.SavePlantsUseCase
import com.example.domain.usecases.local.save.SavePlantsUseCaseImpl
import com.example.domain.usecases.plant.FetchPlantItemsUseCase
import com.example.domain.usecases.plant.FetchPlantItemsUseCaseImpl
import com.example.domain.usecases.plant.FetchPlantUseCase
import com.example.domain.usecases.plant.FetchPlantUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUserRegisterUseCases(
        authorRepository: AuthorRepository
    ): RegisterUseCase = RegisterUseCaseImpl(authorRepository)

    @Provides
    fun provideUserLoginUseCases(
        authorRepository: AuthorRepository
    ): LoginUseCase = LoginUseCaseImpl(authorRepository)

    @Provides
    fun provideUserPlantUseCase(
        plantRepository: GetPlantRepository
    ): FetchPlantUseCase = FetchPlantUseCaseImpl(plantRepository)

    @Provides
    fun provideDetailsUseCase(
        detailsRepository: DetailsRepository
    ): DetailsUseCase = DetailsUseCaseImpl(detailsRepository)

    @Provides
    fun provideItemsUseCase(
        plantRepository: GetPlantRepository
    ): FetchPlantItemsUseCase = FetchPlantItemsUseCaseImpl(plantRepository)

    @Provides
    fun provideCategoryItemsUseCase(
        plantRepository: CategoryRepository
    ): CategoryUseCase = CategoryUseCaseImpl(plantRepository)

    @Provides
    fun provideDeletePlants(
        detailsRepository: DetailsRepository
    ): DeletePlantByIdUseCase = DeletePlantByIdUseCaseImpl(detailsRepository)

    @Provides
    fun provideFetchAllPlants(
        detailsRepository: DetailsRepository
    ): FetchAllSaveUseCase = FetchAllSaveUseCaseImpl(detailsRepository)

    @Provides
    fun provideObservePlants(
        detailsRepository: DetailsRepository
    ): ObserveIsPlantSavedById = ObserveIsPlantSavedByIdImpl(detailsRepository)

    @Provides
    fun provideSavedPlants(
        detailsRepository: DetailsRepository
    ): SavePlantsUseCase = SavePlantsUseCaseImpl(detailsRepository)

    @Provides
    fun provideAllDelete(
        detailsRepository: DetailsRepository
    ): ClearTableUseCase = ClearTableUseCaseImpl(detailsRepository)
}