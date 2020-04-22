package com.ray650128.baseframework.base

import android.view.View

/**
 * Created by ljk on 2017/6/28.
 * mvp 中 view的基類
 */
interface BaseView {
    fun startProgressDialog()
    fun startProgressDialog(message: String?)
    fun startProgressDialog(message: String?, isClosable: Boolean)
    fun stopProgressDialog()
    fun showToast(message: String?)
    fun showToastCenter(message: String?)
}