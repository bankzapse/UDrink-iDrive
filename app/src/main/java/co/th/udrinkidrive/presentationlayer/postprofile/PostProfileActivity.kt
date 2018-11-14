package co.th.udrinkidrive.presentationlayer.postprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.th.udrinkidrive.R
import co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile.PostEditFragment
import co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile.PostMenuFragment
import co.th.udrinkidrive.presentationlayer.postprofile.ui.postprofile.PostProfileFragment
import kotlinx.android.synthetic.main.post_profile_activity.*

class PostProfileActivity : AppCompatActivity() {

    var check_edit : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_profile_activity)

        image_back.setOnClickListener {
            onBackPressed()
        }

//        image_edit.setOnClickListener {
//            if(check_edit){
//                supportFragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left)
//                        .replace(R.id.container, PostProfileFragment.newInstance())
//                        .commitNow()
//                image_edit.setImageResource(android.R.drawable.ic_menu_edit)
//                check_edit = false
//            }else{
//                supportFragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.slide_out_left, R.anim.slide_out_right)
//                        .replace(R.id.container, PostEditFragment.newInstance())
//                        .commitNow()
//                image_edit.setImageResource(android.R.drawable.ic_delete)
//                check_edit = true
//            }
//        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PostMenuFragment.newInstance())
                    .commitNow()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_out_left,R.anim.slide_out_right)
    }

}
