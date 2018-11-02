package co.th.udrinkidrive.datalayer.di

import co.th.udrinkidrive.datalayer.PostRepository
import co.th.udrinkidrive.datalayer.PostRepositoryImpl
import co.th.udrinkidrive.datalayer.local.PostDao
import co.th.udrinkidrive.datalayer.remote.PostsReaderApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class PostRepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(localSource: PostDao, remoteSource: PostsReaderApi): PostRepository = PostRepositoryImpl(localSource, remoteSource, Schedulers.io())
}

