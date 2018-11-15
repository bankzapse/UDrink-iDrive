package co.th.udrinkidrive.presentationlayer.postcallotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp.PostOTPFragment
import co.th.udrinkidrive.R

class PostCallOTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_call_otp_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PostOTPFragment.newInstance())
                    .commitNow()
        }
    }

}
