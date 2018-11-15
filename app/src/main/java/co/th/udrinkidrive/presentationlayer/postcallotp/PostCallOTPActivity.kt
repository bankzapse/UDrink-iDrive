package co.th.udrinkidrive.presentationlayer.postcallotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp.PostOTPFragment
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp.PostPhoneOTPFragment

class PostCallOTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_call_otp_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PostPhoneOTPFragment.newInstance())
                    .commitNow()
        }
    }

    fun GotoOTPRequest(){
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left)
                .replace(R.id.container, PostOTPFragment.newInstance())
                .commitNow()
    }

}
