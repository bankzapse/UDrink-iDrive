package co.th.udrinkidrive

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import android.transition.Explode
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import co.th.udrinkidrive.datalayer.service.InternetAvailability
import kotlinx.android.synthetic.main.activity_loading.*
import co.th.udrinkidrive.presentationlayer.postlogin.PostLoginActivity
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity


class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.enterTransition = Explode()
        setContentView(R.layout.activity_loading)

        val fadeInAnimation = AnimationUtils.loadAnimation(this@LoadingActivity, R.anim.fade_in)
        image_loading.startAnimation(fadeInAnimation)
        tv_sologan.startAnimation(fadeInAnimation)

        object : CountDownTimer(2500, 1000) {

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {

                if(SharedPrefUtil(this@LoadingActivity).GetResultString("Login") == "true"){
                    var intent = Intent(this@LoadingActivity,PostMapActivity::class.java)
                    startActivity(intent)
                }else{
                    var intent = Intent(this@LoadingActivity,PostLoginActivity::class.java)
                    val p1 = Pair.create<View, String> (image_loading, "profile")
                    val p2 = Pair.create<View, String> (tv_sologan, "text")
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@LoadingActivity, p1, p2)
                    startActivity(intent, options.toBundle())
                }

            }

        }.start()
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