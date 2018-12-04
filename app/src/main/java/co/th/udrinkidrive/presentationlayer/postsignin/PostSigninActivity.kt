package co.th.udrinkidrive.presentationlayer.postsignin

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.datalayer.apiservice.ApiUtils
import co.th.udrinkidrive.datalayer.apiservice.SOService
import co.th.udrinkidrive.datalayer.entity.Item
import co.th.udrinkidrive.datalayer.service.InternetAvailability
import co.th.udrinkidrive.presentationlayer.postcallotp.PostCallOTPActivity
import co.th.udrinkidrive.presentationlayer.postforgotpassword.PostForgotpwActivity
import co.th.udrinkidrive.presentationlayer.postregister.PostRegisterActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_post_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostSigninActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var itemlist : ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_signin)

        Utils(this).PushDownClick(bt_confirm)
        Utils(this).PushDownClick(bt_cancel)

        bt_confirm.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forgot_pass.setOnClickListener(this)
        ln_sign_main.setOnClickListener(this)

        et_email.setText("bank@gmail.com")
        et_pass.setText("123456")

        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("Tag","refreshedToken : $refreshedToken")

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
//                CallLoginService()
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
            R.id.ln_sign_main ->{
                Utils(this).hideSoftKeyboard(v)
            }
        }

    }

    fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun CallLoginService(){
        if(et_email.text.isEmpty()){
            Utils(this@PostSigninActivity).ToastError(getString(R.string.login_please_email))
        }else {
            if (isEmailValid(et_email.text)) {
                if (et_pass.text.isEmpty()) {
                    Utils(this@PostSigninActivity).ToastError(getString(R.string.login_please_pass))
                } else {
                    if (et_pass.text.length in 4..16) {
                        val myService: SOService = ApiUtils.getSOService()
                        myService.Login(et_email.text.toString(), et_pass.text.toString()).enqueue(callbackService)
                    } else {
                        Utils(this@PostSigninActivity).ToastError(getString(R.string.login_please_pass_not_match))
                    }
                }
            } else {
                Utils(this@PostSigninActivity).ToastError(getString(R.string.login_please_email_type))
            }
        }
    }

    val callbackService = object : Callback<Item> {

        override fun onResponse(call: Call<Item>, response: Response<Item>) {
            if (response.isSuccessful) {
                if(response.body()!!.result == 1){
                    itemlist = response.body()!!.data
                    var intent = Intent(this@PostSigninActivity, PostCallOTPActivity::class.java)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostSigninActivity, image_loading_sign_in, "profile")
                    startActivity(intent, options.toBundle())
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
                }else{
                    Utils(this@PostSigninActivity).PopupLoginFailed()
                }

            }else{
                Utils(this@PostSigninActivity).PopupLoginFailed()
            }

        }

        override fun onFailure(call: Call<Item>, t: Throwable) {
            Utils(this@PostSigninActivity).PopupLoginFailed()
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(InternetAvailability(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(InternetAvailability())
    }

}