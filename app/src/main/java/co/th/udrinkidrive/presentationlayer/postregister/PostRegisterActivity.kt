package co.th.udrinkidrive.presentationlayer.postregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.activity_post_register.*
import android.widget.Toast
import co.th.udrinkidrive.MainActivity
import com.takisoft.datetimepicker.DatePickerDialog
import java.util.*
import co.th.udrinkidrive.MonthYearPickerDialog
import co.th.udrinkidrive.presentationlayer.postcallotp.PostCallOTPActivity
import kotlin.collections.ArrayList

class PostRegisterActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_register)

        Utils(this).PushDownClick(bt_confirm)
        Utils(this).PushDownClick(bt_cancel)
        bt_confirm.setOnClickListener(this)
        bt_cancel.setOnClickListener(this)
        bt_date_of_birth.setOnClickListener(this)
        bt_date_of_birth.visibility = View.GONE

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.bt_confirm -> {
                val list_profile = ArrayList<String>()
                list_profile.add("thananan")
                list_profile.add("anurak")
                list_profile.add("bankzapse@gmail.com")
                var intent = Intent(this@PostRegisterActivity, PostCallOTPActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@PostRegisterActivity, image_loading_register, "profile")
                intent.putExtra("list_profile",list_profile)
                startActivity(intent, options.toBundle())
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
            }
            R.id.bt_date_of_birth -> {
                var calendar = Calendar.getInstance()

                val pd = MonthYearPickerDialog.newInstance(calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR))
                pd.setListener { view, year, month, dayOfMonth ->
                    bt_date_of_birth.text = dayOfMonth.toString()+"/"+month+"/"+year
                }

                pd.show(supportFragmentManager, "MonthYearPickerDialog")

            }
            R.id.bt_cancel -> {
                onBackPressed()
            }
        }

    }

}