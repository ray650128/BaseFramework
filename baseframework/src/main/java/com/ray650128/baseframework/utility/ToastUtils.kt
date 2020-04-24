package com.ray650128.baseframework.utility

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * @Description: Toast 工具類別
 * @author: Raymond Yang
 * @date: 2017-9-12
 */
object ToastUtils {
    private var toast: Toast? = null

    /**
     * 顯示吐司
     * @param context
     * @param text
     * @param duration
     */
    fun toastShow(context: Context?, text: String, duration: Int) {
        toastShow(context, text, duration, false)
    }

    /**
     * 顯示吐司在中間
     * @param context
     * @param text
     * @param duration
     * @param isCenter
     */
    fun toastShow(context: Context?, text: String, duration: Int, isCenter: Boolean) {
        if (context != null) {
            if (toast == null) {
                //如果還沒有用過makeText方法，才使用
                toast = Toast.makeText(context, text, duration)
            } else {
                toast?.setText(text)
                toast?.duration = duration
            }
            if(isCenter) {
                toast?.setGravity(Gravity.CENTER, 0, 0)
            } else {
                toast?.setGravity(Gravity.BOTTOM, 0, 100)
            }
            toast?.show()
        }
    }
}