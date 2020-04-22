package com.ray650128.baseframework.utility

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.ray650128.baseframework.R


object DialogUtils {

    /**
     * 建立載入中Dialog
     * @param context
     * @param msg
     * @return
     */
    fun createLoadingDialog(context: Context, msg: String?): Dialog {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.dialog_loading, null)
        // 得到加载view
        val layout = v.findViewById<View>(R.id.dialog_view) as LinearLayout
        // 加载布局
        val msgTV = v.findViewById<View>(R.id.textMessage) as TextView
        msgTV.text = msg
        val loadingDialog = Dialog(context, R.style.LoadingDialog)// 创建自定义样式dialog
        loadingDialog.setCancelable(true)// 不可以用"返回键"取消
        loadingDialog.setContentView(layout, LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT))
        return loadingDialog
    }

}
