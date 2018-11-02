package co.th.udrinkidrive.MyFontsStyle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by programmer on 10/31/17.
 */
@SuppressLint("AppCompatCustomView")
class MyTextViewFontsBold : TextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
        attrs.styleAttribute
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        //        this.setTextSize(25);

        val tf = Typeface.createFromAsset(context.assets, "fonts/Kittithada-Bold.ttf")
        typeface = tf
    }

}