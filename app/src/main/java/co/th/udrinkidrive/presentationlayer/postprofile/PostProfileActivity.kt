package co.th.udrinkidrive.presentationlayer.postprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import co.th.udrinkidrive.LoadingActivity
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import kotlinx.android.synthetic.main.activity_post_profile.*

class PostProfileActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_profile)

        image_back.setOnClickListener {
            onBackPressed()
        }

        ViewAndEvent()

    }

    fun ViewAndEvent(){
        bt_logout.setOnClickListener(this)
        tv_reward.setOnClickListener(this)
        tv_payment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            //ADD POP UP SLIDE
            R.id.bt_logout -> {
                val intent = Intent(this@PostProfileActivity, LoadingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                Utils(this).PopupDefault(R.drawable.img_warning,resources.getString(R.string.pop_logout_topic),resources.getString(R.string.pop_logout_sub_topic),intent,"INTENT",this)
            }
            R.id.tv_reward -> {
                val intent = Intent(this@PostProfileActivity, PostRewardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
            }
            R.id.tv_payment -> {
                val intent = Intent(this@PostProfileActivity, PostPaymentActivty::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right)
    }

}
