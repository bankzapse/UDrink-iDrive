package co.th.udrinkidrive.util

import android.util.Log

class LoggerImpl : Logger {

    override fun logDebug(tag: String, message: String) {
        Log.d(tag, message)
    }
}

