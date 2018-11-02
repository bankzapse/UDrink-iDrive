package co.th.udrinkidrive.datalayer

import android.util.Log
import androidx.lifecycle.LiveData
import co.th.udrinkidrive.datalayer.entity.Post
import co.th.udrinkidrive.datalayer.local.PostDao
import co.th.udrinkidrive.datalayer.remote.PostsReaderApi
import io.reactivex.Scheduler
class PostRepositoryImpl(val localSource: PostDao, val remoteSource: PostsReaderApi, val scheduler: Scheduler) : PostRepository {

    override fun insertOrUpdate(post: Post) {
        localSource.insertOrUpdatePosts(post)
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        remoteSource.getAllPosts()
                .subscribeOn(scheduler)
                .subscribe { posts, _ ->
                    posts?.let { localSource.insertOrUpdatePosts(*posts.toTypedArray()) }
                }
        return localSource.getAllPosts()
    }

    override fun getPostById(id: String): LiveData<Post> {
        remoteSource.getPostById(id)
                .subscribeOn(scheduler)
                .subscribe { post, _ ->
                    post?.let { localSource.insertOrUpdatePosts(post) }
                }
        return localSource.getPostById(id)
    }
}

