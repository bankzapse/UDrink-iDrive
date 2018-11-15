package co.th.udrinkidrive.presentationlayer.postsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.postcallotp.PostCallOTPActivity
import co.th.udrinkidrive.presentationlayer.postforgotpassword.PostForgotpwActivity
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import co.th.udrinkidrive.presentationlayer.postregister.PostRegisterActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_post_signin.*

class PostSigninActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_signin)

//        PushDownClick(bt_confirm)
//        PushDownClick(bt_cancel)
        Utils(this).PushDownClick(bt_confirm)
        Utils(this).PushDownClick(bt_cancel)

        bt_confirm.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forgot_pass.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
                var intent = Intent(this@PostSigninActivity, PostCallOTPActivity::class.java)
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
            R.id.tv_forgot_pass ->{
                var intent = Intent(this@PostSigninActivity, PostForgotpwActivity::class.java)
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