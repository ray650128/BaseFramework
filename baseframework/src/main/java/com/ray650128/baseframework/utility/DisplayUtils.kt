package com.ray650128.baseframework.utility

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.WindowManager

/**
 * @Description: Display 工具類別
 * @author: Raymond Yang
 * @date: 2019-10-1
 */
object DisplayUtils {
    /**
     * DP 轉 PX
     * @param dp        DP 數值
     * @param context   Context
     * @return
     */
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * getDensity(context)
    }

    /**
     * PX 轉 DP
     * @param px        PX 數值
     * @param context   Context
     * @return
     */
    fun convertPixelToDp(px: Float, context: Context): Float {
        return px / getDensity(context)
    }

    /**
     * DP 轉 SX
     * @param dp        DP 數值
     * @param context   Context
     * @return
     */
    fun convertDpToSp(dp: Float, context: Context): Float {
        return (convertDpToPixel(dp, context) / context.resources.displayMetrics.scaledDensity)
    }

    /**
     * 取得解析度
     * @param context   Context
     * @return
     */
    fun getDensity(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.density
    }

    /**
     * 取得螢幕寬
     * @param context   Context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }

    /**
     * 取得螢幕高
     * @param context   Context
     * @return
     */
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
