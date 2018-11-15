package co.th.udrinkidrive.presentationlayer.postforgotpassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import co.th.udrinkidrive.presentationlayer.postsignin.PostSigninActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_forgot_pass.*

class PostForgotpwActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        Utils(this).PushDownClick(bt_confirm)
        Utils(this).PushDownClick(bt_cancel)
        bt_confirm.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
                onBackPressed()
                finish()
            }
            R.id.bt_cancel -> {
                onBackPressed()
                finish()
            }
        }

    }

    fun PushDownClick(bt: MyButtonFonts) {
        PushDownAnim.setPushDownAnimTo(bt)
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE)
                .setDurationPush(35)
                .setDurationRelease(15)
                .setOnClickListener(this@PostForgotpwActivity)
    }
}