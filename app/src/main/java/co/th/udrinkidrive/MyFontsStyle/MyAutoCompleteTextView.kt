package co.th.udrinkidrive.MyFontsStyle

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.AutoCompleteTextView

/**
 * Created by AndroidDeveloper on 10/17/16 AD.
 */
@SuppressLint("AppCompatCustomView")
class MyAutoCompleteTextView : AutoCompleteTextView {
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

