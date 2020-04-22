package com.ray650128.baseframework.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.ray650128.baseframework.R

/**
 * Created by wenzhihao on 2017/8/18.
 * 自定義textview 可在佈局檔案設定drawableLeft等大小
 */
class DrawableTextView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : AppCompatTextView(mContext, attrs, defStyleAttr) {
    /**
     * resID
     */
    private var drawableLeft = 0
    private var drawableRight = 0
    private var drawableTop = 0
    private var drawableBottom = 0
    /**
     * 圖標寬度
     */
    private var drawableWidth = 0f
    /**
     * 圖標高度
     */
    private var drawableHeight = 0f

    private fun init() {
        var leftDrawable: Drawable? = null
        if (drawableLeft != 0) {
            leftDrawable = ContextCompat.getDrawable(mContext, drawableLeft)
            leftDrawable!!.setBounds(0, 0, drawableWidth.toInt(), drawableHeight.toInt())
        }
        var rightDrawable: Drawable? = null
        if (drawableRight != 0) {
            rightDrawable = ContextCompat.getDrawable(mContext, drawableRight)
            rightDrawable!!.setBounds(0, 0, drawableWidth.toInt(), drawableHeight.toInt())
        }
        var topDrawable: Drawable? = null
        if (drawableTop != 0) {
            topDrawable = ContextCompat.getDrawable(mContext, drawableTop)
            topDrawable!!.setBounds(0, 0, drawableWidth.toInt(), drawableHeight.toInt())
        }
        var bottomDrawable: Drawable? = null
        if (drawableBottom != 0) {
            bottomDrawable = ContextCompat.getDrawable(mContext, drawableBottom)
            bottomDrawable!!.setBounds(0, 0, drawableWidth.toInt(), drawableHeight.toInt())
        }
        setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable)
    }

    init {
        if (attrs != null) {
            val a = mContext.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
            try {
                drawableLeft = a.getResourceId(R.styleable.DrawableTextView_leftDrawable, 0)
                drawableRight = a.getResourceId(R.styleable.DrawableTextView_rightDrawable, 0)
                drawableTop = a.getResourceId(R.styleable.DrawableTextView_topDrawable, 0)
                drawableBottom = a.getResourceId(R.styleable.DrawableTextView_bottomDrawable, 0)
                drawableWidth = a.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, 30).toFloat()
                drawableHeight = a.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, 30).toFloat()
            } finally {
                a.recycle()
            }
        }
        init()
    }
}