package co.th.udrinkidrive.presentationlayer.postsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import co.th.udrinkidrive.presentationlayer.postregister.PostRegisterActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_post_login.*
import kotlinx.android.synthetic.main.activity_post_signin.*

class PostSigninActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_signin)

        PushDownClick(bt_confirm)
        PushDownClick(bt_cancel)

        tv_register.setOnClickListener(this@PostSigninActivity)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
                var intent = Intent(this@PostSigninActivity, PostMapActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostSigninActivity, image_loading_sign_in, "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
            R.id.bt_cancel -> {
                onBackPressed()
            }
            R.id.tv_register ->{
                var intent = Intent(this@PostSigninActivity, PostRegisterActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostSigninActivity,  image_loading_sign_in , "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
            }
        }

    }

    fun PushDownClick(bt: MyButtonFonts) {
        PushDownAnim.setPushDownAnimTo(bt)
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE)
                .setDurationPush(35)
                .setDurationRelease(15)
                .setOnClickListener(this@PostSigninActivity)
    }

}