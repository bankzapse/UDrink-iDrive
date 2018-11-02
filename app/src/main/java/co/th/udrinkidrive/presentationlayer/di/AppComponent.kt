package co.th.udrinkidrive.presentationlayer.di

import co.th.udrinkidrive.datalayer.di.LocalDataModule
import co.th.udrinkidrive.datalayer.di.PostRepositoryModule
import co.th.udrinkidrive.datalayer.di.RemoteDataModule
import co.th.udrinkidrive.presentationlayer.postdetail.PostDetailViewModel
import co.th.udrinkidrive.presentationlayer.postlist.PostListViewModel
import co.th.udrinkidrive.util.Logger
import co.th.udrinkidrive.util.UtilModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                UtilModule::class,

                LocalDataModule::class,
                RemoteDataModule::class,

                PostRepositoryModule::class
        )
)
interface AppComponent {

    val logger: Logger

    fun inject(postListViewModel: PostListViewModel)

    fun inject(postDetailViewModel: PostDetailViewModel)

//    fun inject(PostProfileModel: PostProfileModel)
}

