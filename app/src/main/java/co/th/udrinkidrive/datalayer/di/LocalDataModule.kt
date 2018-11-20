package co.th.udrinkidrive.datalayer.di

import android.content.Context
import androidx.room.Room
import co.th.udrinkidrive.datalayer.local.AppDatabase
import co.th.udrinkidrive.datalayer.local.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule(val context: Context) {

    private val DB_FILE_NAME = "udrink-idrive-db"

    @Provides @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides @Singleton
    fun provideAppDatabase(): AppDatabase = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_FILE_NAME).build()
}
