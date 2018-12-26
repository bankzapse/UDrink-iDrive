package co.th.udrinkidrive.presentationlayer.postprofile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.th.udrinkidrive.R
import co.th.udrinkidrive.Utils
import kotlinx.android.synthetic.main.activity_post_payment.*

class PostPaymentActivty : AppCompatActivity() , View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_payment)

        ViewAndEvent()

        ActionView()
    }

    fun ViewAndEvent(){
        image_back.setOnClickListener(this)
        bt_add_card.setOnClickListener(this)
    }

    fun ActionView(){
        Utils(this).PushDownClick(bt_add_card)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.image_back -> {
                onBackPressed()
            }
            R.id.bt_add_card -> {
                val intent = Intent(this@PostPaymentActivty, PostAddCreditCardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right)
    }

}