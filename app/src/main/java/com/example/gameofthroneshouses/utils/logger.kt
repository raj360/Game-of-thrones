package com.example.gameofthroneshouses.utils

import android.util.Log
import com.example.gameofthroneshouses.BuildConfig

inline fun log(lambda: () -> String?) {
    if (BuildConfig.DEBUG) {
        Log.e("<----------->", lambda() ?: "")
    }
}
