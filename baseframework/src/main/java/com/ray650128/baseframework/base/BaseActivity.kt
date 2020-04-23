package com.ray650128.baseframework.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.ray650128.baseframework.R
import com.ray650128.baseframework.utility.DialogUtils
import com.ray650128.baseframework.utility.DisplayUtils
import com.ray650128.baseframework.utility.ToastUtils

abstract class BaseActivity : RxLifeCycleActivity(), BaseView {

    abstract val layoutId: Int

    private var mContext: Context? = null
    private var mProgressDialog: Dialog? = null

    // 防止瘋狂連點
    private var lastClickTime: Long = 0

    val isFastDoubleClick: Boolean
        get() {
            val time = System.currentTimeMillis()
            val timeD = time - lastClickTime
            if (timeD in 1..999) {
                return true
            }
            lastClickTime = time
            return false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        mContext = this

        initData(savedInstanceState)
        initWidget(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(DisplayUtils.attachBaseContext(newBase!!))
    }

    override fun startProgressDialog() {
        showProgress()
    }

    override fun startProgressDialog(message: String?) {
        showProgress(message)
    }

    override fun startProgressDialog(message: String?, isClosable: Boolean) {
        showProgress(message, isClosable)
    }

    override fun stopProgressDialog() {
        dismissProgress()
    }

    override fun showToast(message: String?) {
        if(message == null) return
        ToastUtils.toastShow(mContext, message, Toast.LENGTH_SHORT)
    }

    override fun showToastCenter(message: String?) {
        if(message == null) return
        ToastUtils.toastShow(mContext, message, Toast.LENGTH_SHORT, true)
    }

    /*
       顯示進度條
     */
    private fun showProgress(str: String?) {
        mProgressDialog = DialogUtils.createLoadingDialog(this, str)
        mProgressDialog!!.show()
    }

    /*
      顯示進度條（不可關閉）
    */
    private fun showProgress(str: String?, canClose: Boolean) {
        mProgressDialog = DialogUtils.createLoadingDialog(this, str)
        if (mProgressDialog == null || !mProgressDialog!!.isShowing) {
            mProgressDialog!!.setCanceledOnTouchOutside(canClose)
            mProgressDialog!!.show()
        }
    }

    /*
      顯示進度條
    */
    private fun showProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.show()
        } else {
            showProgress(getString(R.string.text_message_loading))
        }
    }

    /*
      關閉進度條
    */
    private fun dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    protected abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun initWidget(savedInstanceState: Bundle?)
}