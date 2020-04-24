package com.ray650128.baseframework.base

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ray650128.baseframework.R
import com.ray650128.baseframework.utility.DialogUtils
import com.ray650128.baseframework.utility.ToastUtils

/**
 * @Description: Fragment 基本類別
 * @author: Raymond Yang
 * @date: 2019-10-17
 */
abstract class BaseFragment : RxLifeCycleFragment(), BaseView {

    protected abstract val layoutID: Int

    private var mContext: Context? = null
    private var mResources: Resources? = null
    private var mInflater: LayoutInflater? = null
    private var mConvertView: View? = null
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = activity
        this.mResources = mContext!!.resources
        this.mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mConvertView = inflater.inflate(layoutID, container, false)
        return mConvertView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initWidget(mConvertView!!)
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
        mProgressDialog = DialogUtils.createLoadingDialog(mContext!!, str)
        mProgressDialog!!.show()
    }

    /*
      顯示進度條（不可關閉）
    */
    private fun showProgress(str: String?, canClose: Boolean) {
        mProgressDialog = DialogUtils.createLoadingDialog(mContext!!, str)
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

    protected abstract fun initData()

    protected abstract fun initWidget(rootView: View)
}
