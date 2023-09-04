package com.heixss.spendings.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heixss.spendings.feature.data.database.AppDatabase
import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "spendings1"
        ).setQueryCallback(object : RoomDatabase.QueryCallback {

            override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) {
                // This callback will be called every time a query is executed
                // You can log or process the query and bind arguments here
                println("Executed query: $sqlQuery")
                println("Bind arguments: $bindArgs")
            }
        }, Executor {  })
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMainRepository(appDatabase: AppDatabase): MainRepository {
        return MainRepositoryImpl(appDatabase)
    }
}