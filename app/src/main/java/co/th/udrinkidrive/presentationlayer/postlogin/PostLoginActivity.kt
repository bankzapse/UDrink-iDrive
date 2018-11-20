package co.th.udrinkidrive.presentationlayer.postlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.postsignin.PostSigninActivity
import com.facebook.*
import kotlinx.android.synthetic.main.activity_post_login.*
import com.facebook.login.LoginResult
import org.json.JSONObject
import com.facebook.AccessToken

class PostLoginActivity : AppCompatActivity() ,  View.OnClickListener{

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.exitTransition = Explode()
        callbackManager = CallbackManager.Factory.create()
        setContentView(R.layout.activity_post_login)

        Utils(this).PushDownClick(bt_signin)
        bt_signin.setOnClickListener(this)
        fl_facebook.setOnClickListener(this)

        // Callback registration
        login_facebook_button.setReadPermissions("email")
        login_facebook_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onSuccess(loginResult: LoginResult) {
                val request = GraphRequest.newMeRequest(loginResult.accessToken, object : GraphRequest.GraphJSONObjectCallback {
                    override fun onCompleted(object_list: JSONObject, response: GraphResponse) {
//                                    Log.d("Tag", response.toString())
                        val accesstoken = loginResult.accessToken
                        Log.d("Tag", object_list.toString())
                        Log.d("Tag", object_list.getString("id"))
                        Log.d("Tag", object_list.getString("name"))
                        Log.d("Tag", accesstoken.token)

                     }
                })

                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                Log.d("Tag","onCancel")
            }

            override fun onError(exception: FacebookException) {
                Log.d("Tag","onError : "+exception.message)
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if(isLoggedIn){
            Log.d("Tag","isLoggedIn : $isLoggedIn")
        }
    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_signin -> {
                var intent = Intent(this@PostLoginActivity,PostSigninActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostLoginActivity,  image_loading_login , "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
            }
            R.id.fl_facebook ->{
                login_facebook_button.callOnClick()
            }
        }

    }

}
