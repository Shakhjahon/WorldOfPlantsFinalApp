package com.example.worldofplantsapp.di

import com.example.data.cloud.author.cloud.AuthorCloudDataSourse
import com.example.data.cloud.author.cloud.AuthorCloudDataSourseImpl
import com.example.data.cloud.category_cloud.CategoryCloudDataSourse
import com.example.data.cloud.category_cloud.CategoryCloudDataSourseImpl
import com.example.data.cloud.detail.cloud.DetailCloudDataSourse
import com.example.data.cloud.detail.cloud.DetailCloudDataSourseImpl
import com.example.data.cloud.plant.cloud.GetPlantCloudDataSourse
import com.example.data.cloud.plant.cloud.GetPlantCloudDataSourseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CloudDataSourseModule {

    @Binds
    fun bindUserAuthCloudDataSourse(
        implementation: AuthorCloudDataSourseImpl
    ): AuthorCloudDataSourse

    @Binds
    fun bindPlantCloudDataSourse(
        implementation: GetPlantCloudDataSourseImpl
    ): GetPlantCloudDataSourse

    @Binds
    fun bindDetailsCloudDataSourse(
        implementation: DetailCloudDataSourseImpl
    ): DetailCloudDataSourse

    @Binds
    fun bindCategoryItem(
        implementation: CategoryCloudDataSourseImpl
    ): CategoryCloudDataSourse
}