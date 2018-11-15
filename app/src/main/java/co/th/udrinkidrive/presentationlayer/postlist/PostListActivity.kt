package co.th.udrinkidrive.presentationlayer.postlist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.th.udrinkidrive.PostsReaderApplication
import co.th.udrinkidrive.R
import co.th.udrinkidrive.datalayer.entity.Post
import co.th.udrinkidrive.postsreader.presentation.postlist.PostAdapter
import co.th.udrinkidrive.presentationlayer.PageNavigator
import kotlinx.android.synthetic.main.activity_post_list.*


class PostListActivity : AppCompatActivity() {

    private val TAG = "show_time"

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(PostListViewModel::class.java).also {
            PostsReaderApplication.component.inject(it)
        }
    }

    private val logger = PostsReaderApplication.component.logger
    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.logDebug(TAG, "onCreate")
        setContentView(R.layout.activity_post_list)
        setupRecyclerView()

        getAllPosts()
    }

    override fun onDestroy() {
        logger.logDebug(TAG, "onDestroy")
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        rvPosts.apply {
            setHasFixedSize(true)

            val linearLayoutManager = LinearLayoutManager(this@PostListActivity)
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))

            postAdapter.onPostClickListener = {
//                PageNavigator.gotoPostDetailActivity(this@PostListActivity, this@PostListActivity ,it)
            }
            adapter = postAdapter
        }
    }

    private fun getAllPosts() {
        try {
            viewModel.getAllPosts().observe(this, Observer {
                if (it != null) {
                    showPosts(it)
//                    modifyPostFromOtherThread(it[0])
                Log.d("Tag","it get all : $it")
                } else {
                    Toast.makeText(this, "What! Something went wrong.", Toast.LENGTH_SHORT).show()
                }
            })
        }catch (e : Exception){
            Log.e("Tag","e : ${e.message}")
        }
    }

    private fun showPosts(posts: List<Post>) {
        postAdapter.posts = posts.sortedBy { it.id }
    }

//    // this ugly function is just for demonstration purpose.
//    private var isPostModified = false
//    private fun modifyPostFromOtherThread(post: Post) {
//        if (!isPostModified) {
//            Thread(Runnable {
//                SystemClock.sleep(3000)
//                post.title = "<-- Hacked by Black Lens Crew -->"
//                post.body = "LOL : 55555"
//                viewModel.updatePost(post)
//            }).start()
//            isPostModified = true
//        }
//    }
}

