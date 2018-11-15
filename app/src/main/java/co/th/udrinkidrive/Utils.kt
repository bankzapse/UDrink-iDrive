package co.th.udrinkidrive

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.thekhaeng.pushdownanim.PushDownAnim

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

    fun AnimationLayoutBottom(slide_down: Int, layout: LinearLayout, show :String, type_box: String , image_back : ImageView) {
        val animation = AnimationUtils.loadAnimation(mContext, slide_down)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                if(show.equals("GONE",true)){
                    layout.visibility = View.GONE
                    if(type_box == "Custom"){
                        image_back.visibility = View.GONE
                    }
                }else if(show.equals("VISIBLE",true)){
                    layout.visibility = View.VISIBLE
                    if(type_box == "Custom"){
                        image_back.visibility = View.VISIBLE
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

}