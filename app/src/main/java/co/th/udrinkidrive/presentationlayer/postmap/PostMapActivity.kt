package co.th.udrinkidrive.presentationlayer.postmap

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.th.udrinkidrive.R
import kotlinx.android.synthetic.main.activity_map.*
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import co.th.udrinkidrive.HorizontalAdapter
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.datalayer.service.InternetAvailability
import co.th.udrinkidrive.presentationlayer.detectonmap.TouchableWrapper
import co.th.udrinkidrive.presentationlayer.postprofile.PostProfileActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.seatgeek.placesautocomplete.DetailsCallback
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener
import com.seatgeek.placesautocomplete.model.Place
import com.seatgeek.placesautocomplete.model.PlaceDetails
import kotlinx.android.synthetic.main.custom_confirm_booking.*
import kotlinx.android.synthetic.main.custom_contact.*
import kotlinx.android.synthetic.main.custom_contact_driver.*
import kotlinx.android.synthetic.main.custom_credit_card.*
import kotlinx.android.synthetic.main.custom_finish_trip.*
import kotlinx.android.synthetic.main.custom_info_any.*
import kotlinx.android.synthetic.main.custom_promotion.*
import kotlinx.android.synthetic.main.custom_search_location.*
import kotlinx.android.synthetic.main.table_view_infomation.*

class PostMapActivity : AppCompatActivity() , GoogleMap.OnCameraChangeListener  , TouchableWrapper.UpdateMapAfterUserInterection , View.OnClickListener{

    override fun onCameraChange(p0: CameraPosition?) {
        Log.d("Tag","p0 : $p0")
    }

    internal var googleMap: GoogleMap? = null
    internal var mSupportMapFragment: SupportMapFragment? = null
    lateinit var current: LatLng
    internal var to_location: LatLng? = null
    lateinit var animation:Animation
    lateinit var view_action: LinearLayout
    var action_infomation :Boolean = false
    var action_back_search :Boolean = false
    var avaliable : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        setContentView(R.layout.activity_map)

        mSupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment //Set Google Map
        setMapView()

        AddTablerow()//Table Add location
        tv_add_row.setOnClickListener {
            AddTablerow()
        }

        ViewAndEvent()//Action Click : GONE : VISIBLE

//        setMutiSnapFavoriteLocation()//Add MutiSnap favorite location

        places_autocomplete.setOnPlaceSelectedListener( object : OnPlaceSelectedListener  {
                    override fun onPlaceSelected(place: Place) {
                        Utils(this@PostMapActivity).AnimationLinearLayoutTop(R.anim.slide_up_scroll,ln_google_place,"GONE")
                        Utils(this@PostMapActivity).hideKeyboard(this@PostMapActivity)
                        tv_topic_end_point_to.text = place.terms[0].value
                        tv_end_point_to.text = place.description

//                        for (i in 0 until place.types.size){
//                            Log.d("Tag","place types : $i : ${place.types[i]}")
//                        }
//
//                        for (i in 0 until place.matched_substrings.size){
//                            Log.d("Tag","place matched_substrings : $i : ${place.matched_substrings[i]}")
//                        }
//
//                        for (i in 0 until place.terms.size){
//                            Log.d("Tag","place terms : $i : ${place.terms[i].value}")
//                        }

                        action_infomation = true
                        action_back_search = true
                        ln_search_location.visibility = View.GONE
                        muti_snap_favorite_location.visibility = View.GONE
                        sc_detail.visibility = View.VISIBLE
                        lv_book.visibility = View.VISIBLE

                        places_autocomplete.text.clear()

                        places_autocomplete.getDetailsFor(place , object : DetailsCallback{
                            override fun onSuccess(p0: PlaceDetails?) {
                                Log.d("Tag","PlaceDetails : $p0")
                                val lat = p0!!.geometry.location.lat
                                val lon = p0!!.geometry.location.lng
                                to_location = LatLng(lat,lon)
                                setMapView()
                            }

                            override fun onFailure(p0: Throwable?) {
                                Log.d("Tag","Throwable : $p0")
                            }

                        })
                    }
                }
        )

    }

    fun ViewAndEvent(){

        Utils(this).PushDownClick(bt_confirm_booking)
        image_profile.setOnClickListener(this)
        ln_info_any.setOnClickListener(this)
        ln_main_contact.setOnClickListener(this)
        ln_main_promotion.setOnClickListener(this)
        ln_main_credit_card.setOnClickListener(this)
        image_back.setOnClickListener(this)
        tv_search_current.setOnClickListener(this)
        image_back_search.setOnClickListener(this)
        lv_advance_main.setOnClickListener(this)
        box_advace.setOnClickListener(this)
        bt_cancel_booking.setOnClickListener(this)
        bt_confirm_booking.setOnClickListener(this)
        bt_add_booking.setOnClickListener(this)
        bt_cancel_driver.setOnClickListener(this)
        bt_add_booking_driver.setOnClickListener(this)
        bt_finish_trip.setOnClickListener(this)
    }

    fun setMutiSnapFavoriteLocation(){
        val titles = arrayOf("Android", "Beta", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo")
        val firstAdapter = HorizontalAdapter(titles, this@PostMapActivity)
        val firstManager = LinearLayoutManager(this@PostMapActivity, LinearLayoutManager.HORIZONTAL, false)
        muti_snap_favorite_location.layoutManager = firstManager
        muti_snap_favorite_location.adapter = firstAdapter
    }

    fun setMapView(){

        if (ActivityCompat.checkSelfPermission(this@PostMapActivity,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this@PostMapActivity,
                        android.Manifest.permission.READ_PHONE_STATE) !== PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@PostMapActivity, arrayOf(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_FINE_LOCATION), 1340)
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment!!.getMapAsync { googleMap ->
                if (googleMap != null) {

                    googleMap.clear()
                    googleMap.uiSettings.setAllGesturesEnabled(true)
                    googleMap.uiSettings.isMapToolbarEnabled = false

                    try {
                        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10f, locationListener)
                        val myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                        var longitude = myLocation.longitude
                        var latitude = myLocation.latitude
                        current = LatLng(latitude, longitude)
                        val cameraPosition = CameraPosition.Builder().target(current).zoom(18.0f).build()
                        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                        googleMap.moveCamera(cameraUpdate)
                        AddMarker(googleMap,resources.getDrawable(R.drawable.logo_loading),current)
                    } catch (e: Exception) {
                    }

                    if(to_location != null){
                        AddMarker(googleMap,resources.getDrawable(R.drawable.location_car), to_location!!)
                        Utils(this).ZoomMarkerOptionAndPolyLine(googleMap,current, to_location!!)
                    }

                }
            }
        }
    }

    fun AddMarker(googleMap: GoogleMap, drawable: Drawable, location: LatLng) {
        val pinnedMarker = googleMap.addMarker(MarkerOptions().position(location))
        val height = 80
        val width = 80
        val bitmapdraw = drawable as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)
        Utils(this).pulseMarker(smallMarker, pinnedMarker, 1000)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            //ADD POP UP SLIDE
            R.id.ln_info_any -> {
                ActionSlideShowLeftToRight(ln_any)
            }
            R.id.ln_main_contact -> {
                ActionSlideShowLeftToRight(ln_contact)
            }
            R.id.ln_main_promotion -> {
                ActionSlideShowLeftToRight(ln_promotion)
            }
            R.id.ln_main_credit_card -> {
                ActionSlideShowLeftToRight(ln_credit_card)
                ln_main_credit_card.isEnabled = false
            }
            R.id.image_back -> {
                Utils(this).AnimationLayoutBottom(R.anim.slide_out_left,view_action,"GONE","Custom",image_back,ln_menu_info)
                ln_main_credit_card.isEnabled = true
            }
            //END CALL POPUP SLIDE
            R.id.image_profile -> {
                val intent = Intent(this@PostMapActivity, PostProfileActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
            }
            R.id.tv_search_current -> {
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_up_scroll,ln_search_location,"GONE")
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll,ln_google_place,"VISIBLE")
                places_autocomplete.requestFocus()
                Utils(this).showKeyboardView(places_autocomplete)
            }
            R.id.image_back_search -> {
                if(action_back_search){
                    action_infomation = true
                    Utils(this).AnimationLinearLayoutTop(R.anim.slide_up_scroll,ln_google_place,"GONE")
                    Utils(this).AnimationLayoutBottom(R.anim.slide_up, lv_book, "VISIBLE","General",image_back,ln_menu_info)
                    Utils(this).AnimationLayoutTop(R.anim.slide_down_scroll, sc_detail, "VISIBLE")
                }else {
                    Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll, ln_search_location, "VISIBLE")
                    Utils(this).AnimationLinearLayoutTop(R.anim.slide_up_scroll, ln_google_place, "GONE")
                }
                Utils(this).hideKeyboard(this@PostMapActivity)
            }
            R.id.tableCurrent -> {
                action_infomation = false
                ln_google_place.visibility = View.VISIBLE
                Utils(this).AnimationLayoutBottom(R.anim.slide_down,lv_book,"GONE","General",image_back,ln_menu_info)
                Utils(this).AnimationLayoutTop(R.anim.slide_up_scroll,sc_detail,"GONE")
            }
            R.id.lv_advance_main -> {
                if(!avaliable) {
                    lv_advance_main.visibility = View.GONE
                    avaliable = true
                    Utils(this).AnimationTableRow(R.anim.fade_in, tr_bottom, "VISIBLE", box_advace)
                }
            }
            R.id.box_advace -> {
                lv_advance_main.visibility = View.VISIBLE
                box_advace.visibility = View.GONE
                Utils(this).AnimationTableRow(R.anim.fade_out,tr_bottom,"GONE",box_advace)
                avaliable = false
            }
            //Path Booking
            R.id.bt_confirm_booking -> {
                action_infomation = false
                CaseShowHideStepInfoBookinkAndConfirm("HIDE")
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll,ln_booking,"VISIBLE")
            }
            R.id.bt_add_booking -> {
                ConfirmOrCancleBooking("ADD")
            }
            R.id.bt_cancel_booking -> {
                Utils(this@PostMapActivity).PopupDefault(R.drawable.img_warning,resources.getString(R.string.booking_cancel_topic),resources.getString(R.string.booking_cancel_sub_topic),intent,"BOOK",this@PostMapActivity)
            }
            R.id.bt_cancel_driver -> {
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down,ln_contact_driver,"GONE")
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll, ln_booking, "VISIBLE")
            }
            //Path Driver trip
            R.id.bt_add_booking_driver -> {
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down,ln_contact_driver,"GONE")
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll, ln_end_trip, "VISIBLE")
            }
            R.id.bt_finish_trip -> {
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_out_left,ln_end_trip,"GONE")
                Utils(this).AnimationLinearLayoutTop(R.anim.slide_down_scroll,ln_search_location,"VISIBLE")
            }
        }
    }

    override fun onUpdateMapAfterUserInterection(type : String) {
        if(action_infomation){
            CaseShowHideStepInfoBookinkAndConfirm(type)
        }
    }

    fun ActionSlideShowLeftToRight(ln_action: LinearLayout) {
        Utils(this).AnimationLayoutBottom(R.anim.slide_in_right,ln_action,"VISIBLE","Custom",image_back,ln_menu_info)
        ln_action.visibility = View.VISIBLE
        view_action = ln_action
    }

    fun ConfirmOrCancleBooking(s: String){
        if(s == "ADD"){
            action_infomation = false
            Utils(this).AnimationLinearLayoutTop(R.anim.slide_up, ln_contact_driver, "VISIBLE")
        }else if(s == "CANCEL"){
            action_infomation = true
            CaseShowHideStepInfoBookinkAndConfirm("SHOW")
        }
        Utils(this).AnimationLinearLayoutTop(R.anim.slide_up_scroll,ln_booking,"GONE")

    }

    fun CaseShowHideStepInfoBookinkAndConfirm(type : String){
        if(type == "HIDE") {
            Utils(this).AnimationLayoutBottom(R.anim.slide_down,lv_book,"GONE","General",image_back,ln_menu_info)
            Utils(this).AnimationLayoutTop(R.anim.slide_up_scroll,sc_detail,"GONE")
        }else if(type == "SHOW") {
            Utils(this).AnimationLayoutBottom(R.anim.slide_up, lv_book, "VISIBLE","General",image_back,ln_menu_info)
            Utils(this).AnimationLayoutTop(R.anim.slide_down_scroll, sc_detail, "VISIBLE")
        }
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }

    fun AddTablerow(){

        val inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.custom_table_view, null) as LinearLayout

        val tv_status_row = row.findViewById<TextView>(R.id.tv_status_row)
        val tv_status_local = row.findViewById<TextView>(R.id.tv_status_local)
        val tv_detail_local = row.findViewById<TextView>(R.id.tv_detail_local)
        val image_remove = row.findViewById<ImageView>(R.id.image_remove)
        val tableCurrent = row.findViewById<TableRow>(R.id.tableCurrent)
        if(tableLayoutCurrent.childCount > 5){
            Utils(this@PostMapActivity).ToastError(getString(R.string.table_add_more_limit))
        }else{
            if(tableLayoutCurrent.childCount == 0){
                tv_status_row.text = resources.getString(R.string.location_from)
                tv_status_local.text = "Current Location"
                tv_detail_local.text = "อาคาร Major Tower ทองหล่อ"
                image_remove.visibility = View.GONE
            }else{
                tv_status_row.text = resources.getString(R.string.location_to)+" "+tableLayoutCurrent.childCount.toString()
                tv_status_local.text = "จุดแวะที่ "+tableLayoutCurrent.childCount.toString()
                tv_detail_local.text = "225/9 ซอยทองหล่อ 10 Sukhumvit Rd, Khlong Tan Nuea, Watthana, Bangkok 10110"
                row.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))
            }
            tableLayoutCurrent.addView(row, tableLayoutCurrent.childCount)

            tableCurrent.setOnClickListener(this)
            image_remove.setOnClickListener {
                Log.d("Tag","click : ${tableLayoutCurrent.childCount}")
                tableLayoutCurrent.removeViewAt(tableLayoutCurrent.childCount-1)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        mSupportMapFragment!!.onResume()
        registerReceiver(InternetAvailability(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        mSupportMapFragment!!.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(InternetAvailability())
    }

    override fun onDestroy() {
        super.onDestroy()
        mSupportMapFragment!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mSupportMapFragment!!.onLowMemory()
    }

}