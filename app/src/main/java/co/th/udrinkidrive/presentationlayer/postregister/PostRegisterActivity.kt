package co.th.udrinkidrive.presentationlayer.postregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_post_register.*

class PostRegisterActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_register)

        Utils(this).PushDownClick(bt_confirm)
        Utils(this).PushDownClick(bt_cancel)
        bt_confirm.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)

//        PushDownClick(bt_confirm)
//        PushDownClick(bt_cancel)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
                var intent = Intent(this@PostRegisterActivity, PostMapActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostRegisterActivity, image_loading_register, "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
            R.id.bt_cancel -> {
                onBackPressed()
            }
        }

    }

    fun PushDownClick(bt: MyButtonFonts) {
        PushDownAnim.setPushDownAnimTo(bt)
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE)
                .setDurationPush(35)
                .setDurationRelease(15)
                .setOnClickListener(this@PostRegisterActivity)
    }

}