package co.th.udrinkidrive.MyFontsStyle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout

/**
 * Created by AndroidDeveloper on 9/27/16 AD.
 */
class MyEdittextFonts : EditText {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        //        this.setTextSize(25);

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(5, 0, 0, 0)
        this.layoutParams = lp

        val tf = Typeface.createFromAsset(context.assets, "fonts/Kittithada.ttf")
        typeface = tf
    }

}