package co.th.udrinkidrive

import android.content.Context
import android.content.SharedPreferences

class SharedPrefUtil(context: Context) {

    private var mContext: Context? = context

    var sharedPref: SharedPreferences = mContext!!.getSharedPreferences("udrink_idrive", 0)
    var editor: SharedPreferences.Editor = sharedPref.edit()

    fun SaveResultString(name_shared: String , result: String){
        editor.putString(name_shared, result)
        editor.commit()
    }

    fun SaveResultInt(name_shared: String , result: Int){
        editor.putInt(name_shared, result)
        editor.commit()
    }

    fun GetResultString(name_shared: String) : String{
        var get_result = sharedPref.getString(name_shared, "")

        return get_result
    }

    fun GetResultInt(name_shared: String) : Int{
        var get_result = sharedPref.getInt(name_shared, 0)

        return get_result
    }

}