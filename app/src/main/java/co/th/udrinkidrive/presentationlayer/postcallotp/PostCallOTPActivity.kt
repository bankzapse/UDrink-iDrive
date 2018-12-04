package co.th.udrinkidrive.presentationlayer.postcallotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp.PostOTPFragment
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp.PostPhoneOTPFragment

class PostCallOTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_call_otp_activity)

        val list_profile = intent.extras.getStringArrayList("list_profile")
        if (savedInstanceState == null) {
            val args = Bundle()
            args.putStringArrayList("list_parameter", list_profile)
            val myFragment = PostPhoneOTPFragment()
            myFragment.arguments = args
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,myFragment)
                    .commitNow()
        }

    }

    fun GotoOTPRequest(){
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right)
                .replace(R.id.container, PostOTPFragment.newInstance())
                .commitAllowingStateLoss()
    }

}
