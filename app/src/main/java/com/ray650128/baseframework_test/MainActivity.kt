package com.ray650128.baseframework_test

import android.Manifest
import android.os.Bundle
import android.os.Handler
import androidx.core.app.ActivityCompat
import com.ray650128.baseframework.base.BaseActivity
import com.ray650128.baseframework.openMarket
import com.ray650128.baseframework.startActivity
import com.ray650128.baseframework.utility.PermissionUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    companion object {
        // 權限
        const val PERMISSION_REQ_CODE = 0x101

        val PERMISSION_LIST = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    override fun initData(savedInstanceState: Bundle?) {}

    override fun initWidget(savedInstanceState: Bundle?) {

        btnShowProgress.setOnClickListener {
            startProgressDialog()
            // 延遲 1 秒關閉
            Handler().postDelayed({ stopProgressDialog() }, 1000)
        }

        btnShowMarket.setOnClickListener {
            if (isFastDoubleClick) {    // 防止連點
                showToast("你按太快囉！")
            } else {
                openMarket() // 開啟 App Market
            }
        }

        btnShowActivity.setOnClickListener {
            if (isFastDoubleClick) {    // 防止連點
                showToast("你按太快囉！")
            } else {
                startActivity<SecondActivity>() // 不帶參數開啟 Activity
            }
        }

        btnShowActivity2.setOnClickListener {
            if (isFastDoubleClick) {    // 防止連點
                showToast("你按太快囉！")
            } else {
                startActivity<SecondActivity>("INPUT_TEXT" to "Hello World!") // 帶參數開啟 Activity
            }
        }

        btnShowToast.setOnClickListener {
            showToast("吐司出現了")
        }

        btnShowToast2.setOnClickListener {
            showToastCenter("吐司在中間出現了")
        }

        btnRequestPermission.setOnClickListener {
            checkPermission() // 檢查權限
        }
    }

    // 檢查權限
    private fun checkPermission() {
        val grantCount = PermissionUtils.checkGranted(this@MainActivity, PERMISSION_LIST)

        if (grantCount == PERMISSION_LIST.size) {
            showToast("已允許權限")
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                PERMISSION_LIST,
                PERMISSION_REQ_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQ_CODE -> {
                if (grantResults.isNotEmpty() && PermissionUtils.isAllGranted(grantResults)) {
                    showToast("已允許權限")
                } else {
                    showToast("不允許權限")
                }
                return
            }
            else -> {
            }
        }
    }
}
