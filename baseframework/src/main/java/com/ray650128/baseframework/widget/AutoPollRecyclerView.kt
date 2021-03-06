package com.ray650128.baseframework.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference


class AutoPollRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    companion object {
        private const val TIME_AUTO_POLL: Long = 16
    }

    var autoPollTask: AutoPollTask? = AutoPollTask(this)
    //var autoPollTask1: AutoPollTask1? = AutoPollTask1(this)

    private var index = 0
    private var running = false //標示是否正在自動輪詢
    private var canRun = false //標示是否可以自動輪詢,可在不需要的是否置false
    private var mTouchSlop = 0

    var timeAutoPoll = 1000L

    /**
     * 持續滑動（走馬燈）
     */
    class AutoPollTask(reference: AutoPollRecyclerView?) : Runnable {
        private val mReference: WeakReference<*>

        override fun run() {
            val recyclerView: AutoPollRecyclerView? = mReference.get() as AutoPollRecyclerView
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                recyclerView.scrollBy(2, 2)
                recyclerView.postDelayed(recyclerView.autoPollTask, TIME_AUTO_POLL)
            }
        }

        //使用弱引用持有外部類引用->防止記憶體洩漏
        init {
            mReference = WeakReference<Any?>(reference)
        }
    }

    /***
     * 一次只能滑一個item（輪播圖）
     */
    /*class AutoPollTask1(reference: AutoPollRecyclerView?) : Runnable {
        private val mReference: WeakReference<AutoPollRecyclerView> = WeakReference<AutoPollRecyclerView>(reference)!!
        private val recyclerView: AutoPollRecyclerView? = mReference.get() as AutoPollRecyclerView

        override fun run() {
            if (recyclerView != null && recyclerView.running && recyclerView.canRun) {
                //recyclerView.smoothScrollToPosition(++recyclerView.index)
                recyclerView.postDelayed(recyclerView.autoPollTask1, recyclerView.timeAutoPoll)
            }
        }

    }*/

    //開啟:如果正在執行,先停止->再開啟
    fun start() {
        if (running) stop()
        canRun = true
        running = true
        postDelayed(autoPollTask, TIME_AUTO_POLL);
        //postDelayed(autoPollTask1, timeAutoPoll)
    }

    fun stop() {
        running = false
        removeCallbacks(autoPollTask);
        //removeCallbacks(autoPollTask1)
    }

    //取消RecyclerView的慣性，使每次手動只能滑一個
    /*private var lastX = 0

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = ev.rawX.toInt()
                if (running) stop()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_OUTSIDE -> {
                val nowX = ev.rawX.toInt()
                if (nowX - lastX > mTouchSlop) { //向下滑動
                    smoothScrollToPosition(if (index === 0) 0 else --index)
                    if (canRun) start()
                    return true
                } else if (lastX - nowX > mTouchSlop) { //向上滑動
                    smoothScrollToPosition(++index)
                    if (canRun) start()
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }*/

    /*override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return false
    }*/

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        return false
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return false
    }

    init {
        autoPollTask = AutoPollTask(this)
        //autoPollTask1 = AutoPollTask1(this)
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
}