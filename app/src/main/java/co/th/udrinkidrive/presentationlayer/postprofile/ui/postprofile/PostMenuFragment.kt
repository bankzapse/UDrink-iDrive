package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import co.th.udrinkidrive.R
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.post_menu_fragment.*

class PostMenuFragment : Fragment()  ,  View.OnClickListener{

    companion object {
        fun newInstance() = PostMenuFragment()
    }

    private lateinit var viewModel: PostMenuViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.post_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostMenuViewModel::class.java)

//        PushDownClick(ln_reward)
//        PushDownClick(ln_payment)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
//            R.id.bt_signin -> {
//
//            }
//            R.id.bt_register -> {
//
//            }
        }

    }

    fun PushDownClick(ln: CardView) {
        ln.isEnabled = true
        PushDownAnim.setPushDownAnimTo( ln )
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE )
                .setDurationPush( 35 )
                .setDurationRelease( 15 )
                .setOnClickListener(this@PostMenuFragment)
    }

}
