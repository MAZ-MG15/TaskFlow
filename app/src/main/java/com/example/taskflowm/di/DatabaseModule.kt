package com.example.taskflowm.di

import android.content.Context
import androidx.room.Room
import com.example.taskflowm.data.local.database.TaskFlowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskFlowDatabase {
        return Room.databaseBuilder(
            context,
            TaskFlowDatabase::class.java,
            "task_flow_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTaskDao(db: TaskFlowDatabase) = db.taskDao()

    @Provides
    fun provideUserDao(db: TaskFlowDatabase) = db.userDao()
}
