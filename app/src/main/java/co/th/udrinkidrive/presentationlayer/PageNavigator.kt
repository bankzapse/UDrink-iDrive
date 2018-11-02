package co.th.udrinkidrive.presentationlayer

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postdetail.PostDetailActivity

object PageNavigator {

    fun gotoPostDetailActivity(context: Context, activity: FragmentActivity?, postId: String) {
        val intent = Intent(context, PostDetailActivity::class.java).apply {
            putExtra(PostDetailActivity.KEY_POST_ID, postId)
        }
        context.startActivity(intent)
//        activity!!.overridePendingTransition(R.anim.swap_in_bottom, R.anim.swap_out_bottom)
    }
}

