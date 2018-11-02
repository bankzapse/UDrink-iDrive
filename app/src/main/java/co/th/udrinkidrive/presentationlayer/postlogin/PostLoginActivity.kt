package co.th.udrinkidrive.presentationlayer.postlogin

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postregister.PostRegisterActivity
import co.th.udrinkidrive.presentationlayer.postsignin.PostSigninActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE
import kotlinx.android.synthetic.main.activity_post_login.*

class PostLoginActivity : AppCompatActivity() ,  View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.exitTransition = Explode()
        setContentView(R.layout.activity_post_login)

        if (ActivityCompat.checkSelfPermission(this@PostLoginActivity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this@PostLoginActivity,
                        android.Manifest.permission.READ_PHONE_STATE) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@PostLoginActivity, arrayOf(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_FINE_LOCATION), 1340)
        }

        PushDownClick(bt_signin)
        PushDownClick(bt_register)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_signin -> {
                var intent = Intent(this@PostLoginActivity,PostSigninActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostLoginActivity,  image_loading_login , "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
            }
            R.id.bt_register -> {
                var intent = Intent(this@PostLoginActivity,PostRegisterActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostLoginActivity,  image_loading_login , "profile")
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out)
            }
        }

    }

    fun PushDownClick(bt: MyButtonFonts) {
        PushDownAnim.setPushDownAnimTo( bt )
                .setScale( MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE )
                .setDurationPush( 35 )
                .setDurationRelease( 15 )
                .setOnClickListener(this@PostLoginActivity)
    }


}
