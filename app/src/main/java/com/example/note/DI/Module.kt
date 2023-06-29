package com.example.note

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun providedatabase (@ApplicationContext context: Context) : Database{
        return Room.databaseBuilder(context,Database::class.java,"NoteDB")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providedao(database: Database) : Dao{
        return database.dao()
    }

    @Provides
    fun providerepo (dao: Dao) : Repository{
        return Repository(dao)
    }


}