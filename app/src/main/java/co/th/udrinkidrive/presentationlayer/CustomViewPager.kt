package co.th.washtime.presentationlayer

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by programmer on 10/27/17.
 */
class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    var isPagingEnabled: Boolean = false

    init {
        this.isPagingEnabled = false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isPagingEnabled) super.onTouchEvent(event) else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isPagingEnabled) super.onInterceptTouchEvent(event) else false
    }

}