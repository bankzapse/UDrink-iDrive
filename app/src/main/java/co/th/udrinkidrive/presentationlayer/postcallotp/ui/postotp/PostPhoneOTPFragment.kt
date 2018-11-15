package co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postcallotp.PostCallOTPActivity
import kotlinx.android.synthetic.main.post_phone_otp_fragment.*

class PostPhoneOTPFragment : Fragment() {

    companion object {
        fun newInstance() = PostPhoneOTPFragment()
    }

    private lateinit var viewModel: PostPhoneOtpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        return inflater.inflate(R.layout.post_phone_otp_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostPhoneOtpViewModel::class.java)

        val logoMoveAnimation = AnimationUtils.loadAnimation(context, R.anim.shake)
        image_sms_otp.startAnimation(logoMoveAnimation)

        bt_confirm.setOnClickListener {
            val call_otp = PostCallOTPActivity()
            call_otp.GotoOTPRequest()
            (activity as PostCallOTPActivity).GotoOTPRequest()
        }

    }

}
