package co.th.udrinkidrive.datalayer.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.th.udrinkidrive.datalayer.entity.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdatePosts(vararg posts: Post)

    @Query("SELECT * FROM post")
    fun getAllPosts(): LiveData<List<Post>>

    // id is changed to arg0 in generated code
    @Query("SELECT * FROM post WHERE id = :user_id")
    fun getPostById(user_id: String): LiveData<Post>

}

