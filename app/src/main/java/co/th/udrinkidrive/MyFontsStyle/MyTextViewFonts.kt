package co.th.udrinkidrive.MyFontsStyle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by AndroidDeveloper on 9/27/16 AD.
 */
@SuppressLint("AppCompatCustomView")
class MyTextViewFonts : TextView {

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

        val tf = Typeface.createFromAsset(context.assets, "fonts/Kittithada.ttf")
        typeface = tf
    }

}