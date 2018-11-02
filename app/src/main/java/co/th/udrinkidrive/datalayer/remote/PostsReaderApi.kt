package co.th.udrinkidrive.datalayer.remote

import co.th.udrinkidrive.datalayer.entity.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface PostsReaderApi {

    @GET("posts")
    fun getAllPosts(): Single<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: String): Single<Post>

}
