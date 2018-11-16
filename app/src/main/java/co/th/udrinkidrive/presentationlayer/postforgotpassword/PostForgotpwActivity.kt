package co.th.udrinkidrive.presentationlayer.postforgotpassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.LoadingActivity
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
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
                var intent = Intent(this@PostForgotpwActivity, LoadingActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostForgotpwActivity, image_loading_forgot, "profile")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                finish()

            }
            R.id.bt_cancel -> {
                onBackPressed()
            }
        }

    }

}