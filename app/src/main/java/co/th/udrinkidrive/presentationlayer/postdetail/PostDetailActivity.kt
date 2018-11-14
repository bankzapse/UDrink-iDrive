package co.th.udrinkidrive.presentationlayer.postdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import co.th.udrinkidrive.PostsReaderApplication
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState

class PostDetailActivity : AppCompatActivity() , ObservableScrollViewCallbacks {
    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDownMotionEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {
        val KEY_POST_ID = "postId"
    }

    private lateinit var postId: String

    private val viewModel: PostDetailViewModel by lazy {
        ViewModelProviders.of(this).get(PostDetailViewModel::class.java).also {
            PostsReaderApplication.component.inject(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail_shop)

    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()

    }

//    // this ugly function is just for demonstration purpose.
////    private var isPostModified = false
////    private fun modifyPostFromOtherThread(post: Post) {
////        if (!isPostModified) {
////            Thread(Runnable {
////                SystemClock.sleep(3000)
////                post.title = "<-- Hacked by Black Lens Crew -->"
////                post.body = "LOL : 55555"
////                viewModel.updatePost(post)
////            }).start()
////            isPostModified = true
////        }
////    }
}

