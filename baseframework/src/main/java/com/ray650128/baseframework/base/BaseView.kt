package com.ray650128.baseframework.base

/**
 * Created by Raymond Yang on 2019/10/12.
 * view 的基本接口
 */
interface BaseView {
    fun startProgressDialog()
    fun startProgressDialog(message: String?)
    fun startProgressDialog(message: String?, isClosable: Boolean)
    fun stopProgressDialog()
    fun showToast(message: String?)
    fun showToastCenter(message: String?)
}