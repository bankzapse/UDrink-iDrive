package co.th.udrinkidrive.presentationlayer.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.th.udrinkidrive.datalayer.PostRepository
import co.th.udrinkidrive.datalayer.entity.Post
import javax.inject.Inject

class PostDetailViewModel : ViewModel() {

    @Inject
    lateinit var postRepo: PostRepository

    private var post: LiveData<Post>? = null

    fun getPostDetail(postId: String): LiveData<Post> {
        // This is a simple way to cache data. You could cache it in db instead.
        post = post ?: postRepo.getPostById(postId)
        return post!!   // Don't worry I'm a good Engineer ;-)
    }

    // This function is for demonstration purpose only.
    fun updatePost(post: Post) {
        postRepo.insertOrUpdate(post)
    }
}

