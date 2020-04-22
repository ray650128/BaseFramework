package com.ray650128.baseframework.utility

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionUtils {

    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun checkGranted(context: Context, permissions: Array<String>): Int {
        var grantCount = 0
        for (i in permissions) {
            if(isGranted(context, i)) {
                grantCount++
            }
        }
        return grantCount
    }

    fun isAllGranted(grantResults: IntArray): Boolean {
        var grantCount = 0
        for (grant in grantResults) {
            if (grant != -1) grantCount++
        }

        return grantCount == grantResults.size
    }
}