package com.ray650128.baseframework.utility

import android.util.Log
import com.ray650128.baseframework.base.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Description: RxJava 工具類別
 * @author: Raymond Yang
 * @date: 2019-10-1
 */
object RxUtils {
    private const val TAG = "RxUtils"

    /**
     * 用來在網路請求前後顯示隱藏進度條
     * @param view
     * @param <T>
     * @return
     */
    fun <T> applySchedulersWithLoading(view: BaseView): ObservableTransformer<T, T>? {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    Log.d(TAG, "apply: doOnSubscribe")
                    view.startProgressDialog() //顯示進度條
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose {
                    Log.d(TAG, "apply: doOnDispose")
                    view.stopProgressDialog() //隱藏進度條
                }
                .doFinally {
                    Log.d(TAG, "apply: doFinally")
                    view.stopProgressDialog() //隱藏進度條
                }
                .compose(bindToLifecycle(view))
        }
    }

    /**
     * 用來在網路請求前後顯示隱藏/進度條
     * @param view
     * @param showLoading
     * @param <T>
     * @return
     */
    fun <T> applySchedulers(view: BaseView, showLoading: Boolean): ObservableTransformer<T, T>? {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .doOnSubscribe {
                        Log.d(TAG, "apply: doOnSubscribe")
                        if (showLoading) {
                            view.startProgressDialog() //顯示進度條
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnDispose {
                        Log.d(TAG, "apply: doOnDispose")
                        if (showLoading) {
                            view.stopProgressDialog() //隱藏進度條
                        }
                    }
                    .doFinally {
                        Log.d(TAG, "apply: doFinally")
                        if (showLoading) {
                            view.stopProgressDialog() //隱藏進度條
                        }
                    }
                    .compose(bindToLifecycle(view))
        }
    }

    /**
     * 沒有載入進度條
     * @param view
     * @param <T>
     * @return
     */
    fun <T> applySchedulers(view: BaseView?): ObservableTransformer<T, T>? {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle(view))
        }
    }

    private fun <T> bindToLifecycle(view: BaseView?): LifecycleTransformer<T>? {
        return if (view is LifecycleProvider<*>) {
            (view as LifecycleProvider<*>).bindToLifecycle()
        } else {
            throw IllegalArgumentException("view isn't activity or fragment")
        }
    }
}