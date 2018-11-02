package co.th.udrinkidrive.datalayer

import androidx.lifecycle.LiveData
import co.th.udrinkidrive.datalayer.entity.Post

interface PostRepository {

    fun insertOrUpdate(post: Post)

    fun getAllPosts(): LiveData<List<Post>>

    fun getPostById(id: String): LiveData<Post>
}
