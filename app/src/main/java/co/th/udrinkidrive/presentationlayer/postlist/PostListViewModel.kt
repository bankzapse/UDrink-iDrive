package co.th.udrinkidrive.presentationlayer.postlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.th.udrinkidrive.datalayer.PostRepository
import co.th.udrinkidrive.datalayer.entity.Post
import co.th.udrinkidrive.util.Logger
import javax.inject.Inject

class PostListViewModel : ViewModel() {

    private val TAG = "show_time"

    @Inject lateinit var logger: Logger
    @Inject lateinit var postRepo: PostRepository

    private var counter = 0
    private var posts: LiveData<List<Post>>? = null

    fun getAllPosts(): LiveData<List<Post>> {
        // this is for demonstration purpose only
        counter++
        logger.logDebug(TAG, "Counter: $counter")
        Log.d("Tag","Counter: $counter")

        // This is a simple way to cache data. You could cache it in db instead.
        posts = posts ?: postRepo.getAllPosts()
        return posts!!  // Trust me I'm an Engineer ;-)
    }

    // This function is for demonstration purpose only.
    fun updatePost(post: Post) {
        postRepo.insertOrUpdate(post)
    }
}
