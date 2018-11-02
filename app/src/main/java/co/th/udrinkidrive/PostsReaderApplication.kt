package co.th.udrinkidrive

import android.app.Application
import co.th.udrinkidrive.datalayer.di.LocalDataModule
import co.th.udrinkidrive.datalayer.di.PostRepositoryModule
import co.th.udrinkidrive.datalayer.di.RemoteDataModule
import co.th.udrinkidrive.presentationlayer.di.AppComponent
import co.th.udrinkidrive.presentationlayer.di.DaggerAppComponent
import co.th.udrinkidrive.util.UtilModule

class PostsReaderApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    private val BASE_URL = "http://jsonplaceholder.typicode.com"

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
                .utilModule(UtilModule())
                .localDataModule(LocalDataModule(applicationContext))
                .remoteDataModule(RemoteDataModule(BASE_URL))
                .postRepositoryModule(PostRepositoryModule())
                .build()
    }
}

