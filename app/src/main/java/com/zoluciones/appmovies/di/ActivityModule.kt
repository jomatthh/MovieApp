package com.zoluciones.appmovies.di

import com.zoluciones.appmovies.storage.*
import com.zoluciones.appmovies.storage.db.DbMovieDataSourceImpl
import com.zoluciones.appmovies.storage.remote.RemoteMovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindDbMovieDataSource(dataSourceImpl: DbMovieDataSourceImpl): DbMovieDataSource

    @Binds
    abstract fun bindRemoteMovieDataSource(dataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource

    @Binds
    abstract fun bindDbMovieRepo(repoImpl: DbMovieRepoImpl): DbMovieRepo

    @Binds
    abstract fun bindRemoteMovieRepo(repoImpl: RemoteMovieRepoImpl): RemoteMovieRepo
}