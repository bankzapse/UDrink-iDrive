package co.th.udrinkidrive.presentationlayer.detectonmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.SupportMapFragment

class MySupportMapFragment : SupportMapFragment() {
    var mOriginalContentView: View? = null
    lateinit var mTouchView: TouchableWrapper

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        mOriginalContentView = super.onCreateView(inflater, parent, savedInstanceState)
        mTouchView = TouchableWrapper(activity)
        mTouchView.addView(mOriginalContentView)
        return mTouchView
    }

    override fun getView(): View? {
        return mOriginalContentView
    }
}