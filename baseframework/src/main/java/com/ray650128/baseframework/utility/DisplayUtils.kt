package com.ray650128.baseframework.utility

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.WindowManager

object DisplayUtils {
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * getDensity(context)
    }

    fun convertPixelToDp(px: Float, context: Context): Float {
        return px / getDensity(context)
    }

    fun convertDpToSp(dp: Float, context: Context): Float {
        return (convertDpToPixel(dp, context) / context.resources.displayMetrics.scaledDensity)
    }

    fun getDensity(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.density
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.height
    }

    fun attachBaseContext(context: Context): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 8.0需要使用createConfigurationContext處理
            updateResources(context)
        } else {
            context
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context): Context {
        val resources = context.resources
        val configuration = resources.configuration
        if(configuration.fontScale != 1.0f) {
            configuration.fontScale = 1.0f
        }

        return context.createConfigurationContext(configuration)
    }
}
