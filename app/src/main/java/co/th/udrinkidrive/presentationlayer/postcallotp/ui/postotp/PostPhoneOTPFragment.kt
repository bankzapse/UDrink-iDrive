package co.th.udrinkidrive.presentationlayer.postcallotp.ui.postotp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.datalayer.entity.Item
import co.th.udrinkidrive.presentationlayer.postcallotp.PostCallOTPActivity
import kotlinx.android.synthetic.main.fragment_post_phone_otp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPhoneOTPFragment : Fragment() , View.OnClickListener {

    lateinit var itemlist : ArrayList<Item>
    private lateinit var viewModel: PostPhoneOtpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("Tag","onCreateView")

        return inflater.inflate(R.layout.fragment_post_phone_otp, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostPhoneOtpViewModel::class.java)

        val get_list_profile = arguments!!.getStringArrayList("list_parameter")

        val logoMoveAnimation = AnimationUtils.loadAnimation(context, R.anim.shake)
        image_sms_otp.startAnimation(logoMoveAnimation)

        bt_confirm.setOnClickListener(this)

//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this@PostLoginActivity)
//        val refreshedToken = FirebaseInstanceId.getInstance().token
//        Log.d("Tag","refreshedToken : $refreshedToken")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_confirm ->{
                if(et_phone_number.text.toString().isEmpty()){
//                    Toast.makeText(activity, R.string.otp_request_mobile, Toast.LENGTH_SHORT).show()
                    Utils(activity!!.applicationContext).ToastError(getString(R.string.otp_request_mobile))
                }else{
                    if(et_phone_number.text.length < 10){
//                        Toast.makeText(activity, R.string.otp_mobile_length, Toast.LENGTH_SHORT).show()
                        Utils(activity!!.applicationContext).ToastError(getString(R.string.otp_mobile_length))
                    }else{
//                        val myService: SOService = ApiUtils.getSOService()
//                        myService.Login(et_phone_number.text.toString()).enqueue(callbackService)
                        val call_otp = PostCallOTPActivity()
                        call_otp.GotoOTPRequest()
                        (activity as PostCallOTPActivity).GotoOTPRequest()
                    }
                }
            }
        }
    }

    val callbackService = object : Callback<Item> {

        override fun onResponse(call: Call<Item>, response: Response<Item>) {
            if (response.isSuccessful) {
                if(response.body()!!.result == 1){
                    itemlist = response.body()!!.data

                }else{
                    context?.let { Utils(it).PopupLoginFailed() }
                }

            }else{
                context?.let { Utils(it).PopupLoginFailed() }
            }

        }

        override fun onFailure(call: Call<Item>, t: Throwable) {
            context?.let { Utils(it).PopupLoginFailed() }
        }
    }

}
