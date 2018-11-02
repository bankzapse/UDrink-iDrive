package co.th.udrinkidrive.MyFontsStyle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.RadioButton

/**
 * Created by AndroidDeveloper on 9/27/16 AD.
 */
@SuppressLint("AppCompatCustomView")
class MyRadioFonts : RadioButton {


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
        val tf = Typeface.createFromAsset(context.assets, "fonts/OpenSans-Light.ttf")
        typeface = tf
    }

}
