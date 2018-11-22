package co.th.udrinkidrive

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.thekhaeng.pushdownanim.PushDownAnim
import android.R
import android.app.Dialog
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import co.th.udrinkidrive.MyFontsStyle.MyTextViewFonts


class Utils(context: Context) {

    private var mContext: Context? = context

     //Animation Google map current
     fun pulseMarker(markerIcon: Bitmap, marker: Marker, onePulseDuration: Long) {
        val handler = Handler()
        val startTime = System.currentTimeMillis()

        val interpolator = CycleInterpolator(1f)
        handler.post(object : Runnable {
            override fun run() {
                try {
                    val elapsed = System.currentTimeMillis() - startTime
                    val t = interpolator.getInterpolation(elapsed.toFloat() / onePulseDuration)
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(scaleBitmap(markerIcon, 1f + 0.05f * t)))
                    handler.postDelayed(this, 16)
                } catch (e: Exception) {
                    handler.removeCallbacks(this)
                }

            }
        })
    }

    fun scaleBitmap(bitmap: Bitmap, scaleFactor: Float): Bitmap {
        val sizeX = Math.round(bitmap.width * scaleFactor)
        val sizeY = Math.round(bitmap.height * scaleFactor)
        return Bitmap.createScaledBitmap(bitmap, sizeX, sizeY, false)
    }

    //Slide animation with Layout (View)

    fun AnimationLayoutTop(slide_down: Int, layout: ScrollView, show :String) {
        val animation = AnimationUtils.loadAnimation(mContext, slide_down)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                if(show.equals("GONE",true)){
                    layout.visibility = View.GONE
                }else if(show.equals("VISIBLE",true)){
                    layout.visibility = View.VISIBLE
                }

            }
        })
        layout.startAnimation(animation)
    }

    fun AnimationLayoutBottom(slide_down: Int, layout: LinearLayout, show :String, type_box: String , image_back : ImageView , layout_menu: LinearLayout) {
        val animation = AnimationUtils.loadAnimation(mContext, slide_down)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                if(show.equals("GONE",true)){
                    layout.visibility = View.GONE
                    if(type_box == "Custom"){
                        image_back.visibility = View.GONE
                        layout_menu.visibility = View.VISIBLE
                    }
                }else if(show.equals("VISIBLE",true)){
                    layout.visibility = View.VISIBLE
                    if(type_box == "Custom"){
                        image_back.visibility = View.VISIBLE
                        layout_menu.visibility = View.GONE
                    }
                }

            }
        })
        layout.startAnimation(animation)
    }

    fun PushDownClick(bt: MyButtonFonts ) {
        PushDownAnim.setPushDownAnimTo( bt )
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE )
                .setDurationPush( 35 )
                .setDurationRelease( 15 )
    }

    fun PushDownClickTextView(tv: MyTextViewFonts) {
        PushDownAnim.setPushDownAnimTo( tv )
                .setScale(PushDownAnim.MODE_SCALE, PushDownAnim.DEFAULT_PUSH_SCALE )
                .setDurationPush( 35 )
                .setDurationRelease( 15 )
    }

    //hideSoftKeyboard

    fun hideSoftKeyboard(view: View) {
        val imm = mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    //Action Popup

    fun PopupLoginFailed() {

        var dialog_failed = Dialog(mContext)
        dialog_failed.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_failed.setContentView(co.th.udrinkidrive.R.layout.custom_dialog_login)
        dialog_failed.setCancelable(false)
        dialog_failed.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val bt_confirm = dialog_failed.findViewById<View>(co.th.udrinkidrive.R.id.bt_confirm) as Button
//        val tv_text_sub_confirm_detail = dialog_failed.findViewById<View>(R.id.tv_text_sub_confirm_detail) as TextView
//        tv_text_sub_confirm_detail.text = resources.getText(R.string.some_thing_wrong)
        bt_confirm.setOnClickListener {
            dialog_failed.dismiss()
        }

        dialog_failed.show()

    }
}