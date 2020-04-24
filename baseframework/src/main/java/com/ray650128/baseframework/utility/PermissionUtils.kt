package com.ray650128.baseframework.utility

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * @Description: 權限工具類別
 * @author: Raymond Yang
 * @date: 2019-3-14
 */
object PermissionUtils {

    /**
     * 檢查單項權限是否允許
     * @param context
     * @param permission
     * @return
     */
    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 檢查多項權限是否允許
     * @param context
     * @param permissions
     * @return
     */
    fun checkGranted(context: Context, permissions: Array<String>): Int {
        var grantCount = 0
        for (i in permissions) {
            if(isGranted(context, i)) {
                grantCount++
            }
        }
        return grantCount
    }

    /**
     * 檢查所有權限是否允許
     * @param grantResults
     * @return
     */
    fun isAllGranted(grantResults: IntArray): Boolean {
        var grantCount = 0
        for (grant in grantResults) {
            if (grant != -1) grantCount++
        }

        return grantCount == grantResults.size
    }
}