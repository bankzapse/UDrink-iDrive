package co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.th.udrinkidrive.R
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_post_otp.*
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher
import co.th.udrinkidrive.SharedPrefUtil
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity


class PostOTPFragment : Fragment() {

    val smsVerifyCatcher: SmsVerifyCatcher? = null

    companion object {
        fun newInstance() = PostOTPFragment()
    }

    private lateinit var viewModel: PostOTPViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_post_otp, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostOTPViewModel::class.java)

        val logoMoveAnimation = AnimationUtils.loadAnimation(context, R.anim.shake)
        image_sms_otp.startAnimation(logoMoveAnimation)

//        firstPinView.setText("1234", TextView.BufferType.EDITABLE)

//        val smsVerifyCatcher = SmsVerifyCatcher(context as Activity, OnSmsCatchListener { message ->
////            val code = parseCode(message)//Parse verification code
////            etCode.setText(code)//set code in edit text
//            //then you can send verification code to server
//        })
//        smsVerifyCatcher.setPhoneNumberFilter("0910809108")
        firstPinView.addTextChangedListener(object : TextWatcher  {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                Log.d("Tag","length : ${s!!.length}")
                if(s.length == 4){
                    var intent = Intent(activity, PostMapActivity::class.java)
                     startActivity(intent)
                    activity!!.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                    activity!!.finish()
                    SharedPrefUtil(activity!!).SaveResultString("Login","true")
                }
            }

        })
    }

//    override fun onStart() {
//        super.onStart()
//        smsVerifyCatcher!!.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        smsVerifyCatcher!!.onStop()
//    }
//
//    /**
//     * need for Android 6 real time permissions
//     */
//    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        smsVerifyCatcher!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }


}
