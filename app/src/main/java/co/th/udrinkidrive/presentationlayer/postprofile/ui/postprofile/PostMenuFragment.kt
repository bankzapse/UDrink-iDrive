package co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import co.th.udrinkidrive.LoadingActivity
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.fragment_post_menu.*

class PostMenuFragment : Fragment()  ,  View.OnClickListener{

    var check_edit : Boolean = false

    companion object {
        fun newInstance() = PostMenuFragment()
    }

    private lateinit var viewModel: PostMenuViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostMenuViewModel::class.java)

//        PushDownClick(ln_reward)
//        PushDownClick(ln_payment)

        ViewAndEvent()//Action Click

//        image_edit.setOnClickListener {
//            if(check_edit){
//                supportFragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left)
//                        .replace(R.id.container, PostProfileFragment.newInstance())
//                        .commitNow()
//                image_edit.setImageResource(android.R.drawable.ic_menu_edit)
//                check_edit = false
//            }else{
//                supportFragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right)
//                        .replace(R.id.container, PostChangeRewardFragment.newInstance())
//                        .commitNow()
//                image_edit.setImageResource(android.R.drawable.ic_delete)
//                check_edit = true
//            }
//        }
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, PostMenuFragment.newInstance())
//                    .commitNow()
//        }

    }

    fun ViewAndEvent(){
        bt_logout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_logout -> {
                val intent = Intent(context,LoadingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Utils(context!!).PopupDefault(R.drawable.img_warning,resources.getString(R.string.pop_logout_topic),resources.getString(R.string.pop_logout_sub_topic),intent,"INTENT",activity!!)

            }
        }

    }

}
