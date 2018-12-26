package co.th.udrinkidrive.presentationlayer.postprofile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.th.udrinkidrive.R
import kotlinx.android.synthetic.main.activity_post_add_credit_card.*
import kotlinx.android.synthetic.main.flash_card_layout_back.*
import kotlinx.android.synthetic.main.flash_card_layout_front.*

class PostAddCreditCardActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_add_credit_card)

        ViewAndEvent()

        ActionView()
    }

    fun ViewAndEvent(){
        image_font_card.setOnClickListener(this)
        image_back_card.setOnClickListener(this)
        image_back.setOnClickListener(this)
    }

    fun ActionView(){

    }

    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.image_font_card -> {
                easyFlipView.flipTheView()
            }
            R.id.image_back_card -> {
                easyFlipView.flipTheView()
            }
            R.id.image_back -> {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right)
    }

}