package co.th.washtime.presentationlayer

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewParent

import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView

/**
 * Created by programmer on 11/1/17.
 */

class CustomMapView : MapView {

    private var mViewParent: ViewParent? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    constructor(context: Context, options: GoogleMapOptions) : super(context, options) {}

    fun setViewParent(viewParent: ViewParent?) { //any ViewGroup
        mViewParent = viewParent
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> if (null == mViewParent) {
                parent.requestDisallowInterceptTouchEvent(true)
            } else {
                mViewParent!!.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP -> if (null == mViewParent) {
                parent.requestDisallowInterceptTouchEvent(false)
            } else {
                mViewParent!!.requestDisallowInterceptTouchEvent(false)
            }
            else -> {
            }
        }

        return super.onInterceptTouchEvent(event)
    }
}