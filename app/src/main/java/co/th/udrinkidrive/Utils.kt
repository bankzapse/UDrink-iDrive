package co.th.udrinkidrive

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import androidx.core.app.ActivityOptionsCompat
import co.th.udrinkidrive.MyFontsStyle.MyButtonFonts
import co.th.udrinkidrive.presentationlayer.postmap.PostMapActivity
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.thekhaeng.pushdownanim.PushDownAnim
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import co.th.udrinkidrive.MyFontsStyle.MyTextViewFonts
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.constant.Language
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.util.DirectionConverter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.shashank.sony.fancytoastlib.FancyToast

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

    fun AnimationLinearLayoutTop(slide_down: Int, layout: LinearLayout, show :String) {
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

    fun AnimationTableRow(slide_down: Int, layout: TableRow, show :String,layout_advance: LinearLayout) {
        val animation = AnimationUtils.loadAnimation(mContext, slide_down)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                if(show.equals("GONE",true)){
                    layout.visibility = View.GONE
                    layout_advance.visibility = View.GONE
                }else if(show.equals("VISIBLE",true)){
                    layout.visibility = View.VISIBLE
                    layout_advance.visibility = View.VISIBLE
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

    fun showKeyboardView(view: View) {
        val inputMethodManager = mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(view.findFocus(), InputMethodManager.SHOW_IMPLICIT)
    }

    //Action Popup
    fun PopupLoginFailed() {

        var dialog_failed = Dialog(mContext)
        dialog_failed.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_failed.setContentView(co.th.udrinkidrive.R.layout.custom_dialog_login)
        dialog_failed.setCancelable(false)
        dialog_failed.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val bt_confirm = dialog_failed.findViewById<View>(co.th.udrinkidrive.R.id.bt_confirm) as Button
        bt_confirm.setOnClickListener {
            dialog_failed.dismiss()
        }

        dialog_failed.show()

    }

    //Type Toast
    fun ToastError(s:String){
        FancyToast.makeText(mContext,s,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
    }

    //
    fun ZoomMarkerOptionAndPolyLine(googleMap: GoogleMap, current: LatLng, to_location: LatLng) {
        val builder = LatLngBounds.Builder()

        builder.include(current)
        builder.include(to_location)

        val bounds = builder.build()

        val width = mContext!!.resources.displayMetrics.widthPixels
        val height = mContext!!.resources.displayMetrics.heightPixels
        val padding = (width * 0.40).toInt() // offset from edges of the map 10% of screen

        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)

        googleMap.animateCamera(cu)

        GoogleDirection.withServerKey("AIzaSyAoDgVKk5EVsiGqAqOpjZxxvgYw2DPQIIY")
                .from(current)
                .to(to_location)
                .language(Language.THAI)
                .transportMode(TransportMode.DRIVING)
                .avoid(AvoidType.TOLLS)
                .execute(object : DirectionCallback {
                    override fun onDirectionSuccess(direction: Direction, rawBody: String) {
//                        Log.d("Tag","onDirectionSuccess : ${direction.isOK}")
//                        Log.d("Tag","onDirectionSuccess : ${direction.status}")
//                        Log.d("Tag","onDirectionSuccess : ${direction.errorMessage}")
//                        Log.d("Tag","onDirectionSuccess : $rawBody")
                        if(direction.routeList.size > 0){
                            val route = direction.routeList[0]
                            val leg = route.legList[0]
                            val directionPositionList = leg.directionPoint
                            val polylineOptions = DirectionConverter.createPolyline(mContext, directionPositionList, 3, Color.parseColor("#03A3CE"))
                            googleMap.addPolyline(polylineOptions)
                        }

                    }

                    override fun onDirectionFailure(t: Throwable) {
                        // Do something here
                        Log.d("Tag","onDirectionFailure : ${t.message}")
                    }
                })

    }

    //Set permission
    fun setupPermissions(s : String) : Boolean {
        val permission_fine_location = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION )
        val permission_read_external = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE )
        val permission_write_external = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE )
        val permission_read_sms = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.READ_SMS )
        val permission_receive_sms = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.RECEIVE_SMS )
        val permission_call_phone = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.CALL_PHONE )
        val permission_camera = ContextCompat.checkSelfPermission(mContext!!.applicationContext, Manifest.permission.CAMERA )

        val listPermissionsNeeded = ArrayList<String>()
        listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        listPermissionsNeeded.add(Manifest.permission.READ_SMS)
        listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS)
        listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
        listPermissionsNeeded.add(Manifest.permission.CAMERA)

        if(s == "Check"){
            if (permission_fine_location == PackageManager.PERMISSION_GRANTED && permission_read_external == PackageManager.PERMISSION_GRANTED && permission_write_external == PackageManager.PERMISSION_GRANTED
                    && permission_read_sms == PackageManager.PERMISSION_GRANTED && permission_receive_sms == PackageManager.PERMISSION_GRANTED && permission_call_phone == PackageManager.PERMISSION_GRANTED
                    && permission_read_sms == PackageManager.PERMISSION_GRANTED  && permission_camera == PackageManager.PERMISSION_GRANTED) {
                return true
            }else{
                PopupGotoSetting()
                return false
            }
        }

        ActivityCompat.requestPermissions(mContext as Activity, listPermissionsNeeded.toTypedArray(), 1)

        return true
    }

    // Permission Request
    fun PopupGotoSetting() {
        var dialog_custom: Dialog? = null
        if (dialog_custom != null) {
            dialog_custom.dismiss()
        }
        dialog_custom = Dialog(mContext)
        dialog_custom.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_custom.setContentView(co.th.udrinkidrive.R.layout.custom_dialog_login)
        dialog_custom.setCancelable(false)
        dialog_custom.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val image_show_dialog = dialog_custom.findViewById<ImageView>(co.th.udrinkidrive.R.id.image_show_dialog)
        val bt_confirm = dialog_custom.findViewById<Button>(co.th.udrinkidrive.R.id.bt_confirm)
        val tv_text_confirm = dialog_custom.findViewById<TextView>(co.th.udrinkidrive.R.id.tv_text_confirm)
        val tv_text_sub_confirm = dialog_custom.findViewById<TextView>(co.th.udrinkidrive.R.id.tv_text_sub_confirm)

        image_show_dialog.setImageResource(co.th.udrinkidrive.R.drawable.img_warning)
        tv_text_confirm.text = mContext!!.resources.getText(co.th.udrinkidrive.R.string.permission_topic)
        tv_text_sub_confirm.text = mContext!!.resources.getText(co.th.udrinkidrive.R.string.permission_sub_topic)
        bt_confirm.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", mContext!!.packageName, null)
            intent.data = uri
            mContext!!.startActivity(intent)
            dialog_custom!!.dismiss()
        }

        dialog_custom.show()
    }

    //Popup Default
    fun PopupDefault(image: Int , topic: String , sub_topic: String , intent: Intent , check_action: String , activity: Activity){
        var dialog_custom: Dialog? = null
        if (dialog_custom != null) {
            dialog_custom.dismiss()
        }
        dialog_custom = Dialog(mContext)
        dialog_custom.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_custom.setContentView(R.layout.custom_dialog_default)
        dialog_custom.setCancelable(false)
        dialog_custom.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val image_show_dialog = dialog_custom.findViewById<ImageView>(R.id.image_show_dialog)
        val bt_confirm = dialog_custom.findViewById<Button>(R.id.bt_confirm)
        val bt_cancel = dialog_custom.findViewById<Button>(R.id.bt_cancel)
        val tv_text_confirm = dialog_custom.findViewById<TextView>(R.id.tv_text_confirm)
        val tv_text_sub_confirm = dialog_custom.findViewById<TextView>(R.id.tv_text_sub_confirm)

        image_show_dialog.setImageResource(image)
        tv_text_confirm.text = topic
        tv_text_sub_confirm.text = sub_topic
        bt_confirm.setOnClickListener {
            if(check_action == "INTENT") {
                mContext!!.startActivity(intent)
                SharedPrefUtil(mContext as Activity).SaveResultString("Login", "false")
            }else if(check_action == "BOOK"){
                (activity as PostMapActivity).ConfirmOrCancleBooking("CANCEL")
            }
            dialog_custom!!.dismiss()
        }
        bt_cancel.setOnClickListener {
            dialog_custom!!.dismiss()
        }
        dialog_custom.show()
    }


}