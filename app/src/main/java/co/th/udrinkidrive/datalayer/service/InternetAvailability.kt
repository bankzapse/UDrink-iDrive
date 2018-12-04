package co.th.udrinkidrive.datalayer.service

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import co.th.udrinkidrive.R

class InternetAvailability : BroadcastReceiver() {

    private var dialog_failed: Dialog? = null

    override fun onReceive(context: Context, intent: Intent) {
//        Log.d("Tag","onReceive : $context")
        if (isConnected(context)) {
            PopupLoginFailed(context, "true")
            Log.d("Tag","onReceive : true")
        } else {
            PopupLoginFailed(context, "false")
            Log.d("Tag","onReceive : false")
        }
    }

    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        return wifiInfo != null && wifiInfo.isConnected || mobileInfo != null && mobileInfo.isConnected
    }

    fun PopupLoginFailed(context: Context, status: String) {
        if (dialog_failed != null) {
            dialog_failed!!.dismiss()
        }
        dialog_failed = Dialog(context)
        dialog_failed!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog_failed!!.setContentView(R.layout.custom_dialog_login)
        dialog_failed!!.setCancelable(false)
        dialog_failed!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val image_show_dialog = dialog_failed!!.findViewById<ImageView>(R.id.image_show_dialog)
        val bt_confirm = dialog_failed!!.findViewById<Button>(R.id.bt_confirm)
        val tv_text_confirm = dialog_failed!!.findViewById<TextView>(R.id.tv_text_confirm)
        val tv_text_sub_confirm = dialog_failed!!.findViewById<TextView>(R.id.tv_text_sub_confirm)

        image_show_dialog.setImageResource(R.drawable.img_error)
        tv_text_confirm.text = context.resources.getText(R.string.internet_topic)
        tv_text_sub_confirm.text = context.resources.getText(R.string.internet_sub_topic)
        bt_confirm.setOnClickListener {
            if (status.equals("false", ignoreCase = true)) {
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
            dialog_failed!!.dismiss()
        }

        if (status.equals("false", ignoreCase = true)) {
            dialog_failed!!.show()
        }

    }

}
