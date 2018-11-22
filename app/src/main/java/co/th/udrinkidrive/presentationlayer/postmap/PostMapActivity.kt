package co.th.udrinkidrive.presentationlayer.postmap

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.th.udrinkidrive.R
import kotlinx.android.synthetic.main.activity_map.*
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import co.th.udrinkidrive.HorizontalAdapter
import co.th.udrinkidrive.Utils
import co.th.udrinkidrive.presentationlayer.detectonmap.TouchableWrapper
import co.th.udrinkidrive.presentationlayer.postprofile.PostProfileActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.android.synthetic.main.custom_contact.*
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
    lateinit var animation:Animation
    lateinit var view_action: LinearLayout
    var action_infomation :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //Set Google Map
        mSupportMapFragment = supportFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment
        setMapView()

        //Table Add location
        AddTablerow()
        tv_add_row.setOnClickListener {
            AddTablerow()
        }

        //Action Click : GONE : VISIBLE
        ViewAndEvent()

        //Add MutiSnap favorite location
        setMutiSnapFavoriteLocation()

    }

    fun ViewAndEvent(){

        Utils(this).PushDownClick(bt_confirm)

        image_profile.setOnClickListener(this)
        ln_info_any.setOnClickListener(this)
        ln_main_contact.setOnClickListener(this)
        ln_main_promotion.setOnClickListener(this)
        image_back.setOnClickListener(this)
        tv_search_current.setOnClickListener(this)

    }

    fun setMutiSnapFavoriteLocation(){
        val titles = arrayOf("Android", "Beta", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow", "Nougat", "Oreo")
        val firstAdapter = HorizontalAdapter(titles, this@PostMapActivity)
        val firstManager = LinearLayoutManager(this@PostMapActivity, LinearLayoutManager.HORIZONTAL, false)
        muti_snap_favorite_location.layoutManager = firstManager
        muti_snap_favorite_location.adapter = firstAdapter
    }

    fun setMapView(){

//        if (ActivityCompat.checkSelfPermission(this@PostMapActivity,
//                        android.Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this@PostMapActivity,
//                        android.Manifest.permission.READ_PHONE_STATE) !== PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this@PostMapActivity, arrayOf(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.ACCESS_FINE_LOCATION), 1340)
//        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment!!.getMapAsync { googleMap ->
                if (googleMap != null) {

                    googleMap.uiSettings.setAllGesturesEnabled(true)
                    googleMap.uiSettings.isMapToolbarEnabled = false

                    try {
                        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10f, locationListener)
                        val myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
                        var longitude = myLocation.longitude
                        var latitude = myLocation.latitude
                        current = LatLng(latitude, longitude)
                    } catch (e: Exception) {
                    }

                    val cameraPosition = CameraPosition.Builder().target(current).zoom(18.0f).build()
                    val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
                    googleMap.moveCamera(cameraUpdate)

                    val pinnedMarker = googleMap.addMarker(MarkerOptions()
                            .position(current))
                    val height = 80
                    val width = 80
                    val bitmapdraw = resources.getDrawable(R.drawable.logo_loading) as BitmapDrawable
                    val b = bitmapdraw.bitmap
                    val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)

                    Utils(this).pulseMarker(smallMarker, pinnedMarker, 1000)

                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ln_info_any -> {
                Utils(this).AnimationLayoutBottom(R.anim.slide_in_right,ln_any,"VISIBLE","Custom",image_back,ln_menu_info)
                ln_any.visibility = View.VISIBLE
                view_action = ln_any
            }
            R.id.ln_main_contact -> {
                Utils(this).AnimationLayoutBottom(R.anim.slide_in_right,ln_contact,"VISIBLE","Custom",image_back,ln_menu_info)
                ln_contact.visibility = View.VISIBLE
                view_action = ln_contact
            }
            R.id.ln_main_promotion -> {
                Utils(this).AnimationLayoutBottom(R.anim.slide_in_right,ln_promotion,"VISIBLE","Custom",image_back,ln_menu_info)
                ln_promotion.visibility = View.VISIBLE
                view_action = ln_promotion
            }
            R.id.image_back -> {
                Utils(this).AnimationLayoutBottom(R.anim.slide_out_left,view_action,"GONE","Custom",image_back,ln_menu_info)

            }
            R.id.image_profile -> {
                val intent = Intent(this@PostMapActivity, PostProfileActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left)
            }
            R.id.tv_search_current -> {
                action_infomation = true
                ln_search_location.visibility = View.GONE
                muti_snap_favorite_location.visibility = View.GONE
                sc_detail.visibility = View.VISIBLE
                lv_book.visibility = View.VISIBLE
            }
        }
    }


    override fun onUpdateMapAfterUserInterection(type : String) {
        if(action_infomation){
            if(type == "DOWN") {
                Utils(this).AnimationLayoutBottom(R.anim.slide_down,lv_book,"GONE","General",image_back,ln_menu_info)
                Utils(this).AnimationLayoutTop(R.anim.slide_up_scroll,sc_detail,"GONE")
            }else if(type == "UP") {
                Utils(this).AnimationLayoutBottom(R.anim.slide_up, lv_book, "VISIBLE","General",image_back,ln_menu_info)
                Utils(this).AnimationLayoutTop(R.anim.slide_down_scroll, sc_detail, "VISIBLE")
            }
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

//        val view_add = row.findViewById<View>(R.id.view_add)
        val tv_status_row = row.findViewById<View>(R.id.tv_status_row) as TextView
        val tv_status_local = row.findViewById<View>(R.id.tv_status_local) as TextView
        val tv_detail_local = row.findViewById<View>(R.id.tv_detail_local) as TextView
        val image_remove = row.findViewById<View>(R.id.image_remove) as ImageView
        if(tableLayoutCurrent.childCount == 0){
//            view_add.setBackgroundColor(resources.getColor(R.color.blue))
            tv_status_row.text = "FROM"
            tv_status_local.text = "Current Location"
            tv_detail_local.text = "225/9 ซอยทองหล่อ 10 Sukhumvit Rd, Khlong Tan Nuea, Watthana, Bangkok 10110"
            image_remove.visibility = View.GONE
        }else{
            tv_status_row.text = "TO "+tableLayoutCurrent.childCount.toString()
            tv_status_local.text = "-"
            row.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))
        }
//        tableLayoutCurrent.tag = tableLayoutCurrent.childCount
        tableLayoutCurrent.addView(row, tableLayoutCurrent.childCount)

        image_remove.setOnClickListener {
            Log.d("Tag","click : ${tableLayoutCurrent.childCount}")
            tableLayoutCurrent.removeViewAt(tableLayoutCurrent.childCount-1)
        }

    }

    override fun onResume() {
        super.onResume()
        mSupportMapFragment!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mSupportMapFragment!!.onPause()
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