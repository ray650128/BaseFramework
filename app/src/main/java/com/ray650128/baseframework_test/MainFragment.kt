package com.ray650128.baseframework_test

import android.os.Bundle
import android.view.View
import com.ray650128.baseframework.base.BaseFragment
import com.ray650128.baseframework.loadUrl
import com.ray650128.baseframework.utility.RxUtils
import com.ray650128.baseframework_test.net.dataModels.Weather
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_main.*
import com.ray650128.baseframework_test.net.AppApi


class MainFragment : BaseFragment() {

    override val layoutID: Int = R.layout.fragment_main

    override fun initData() {}

    override fun initWidget(rootView: View) {
        btnLoadImage.setOnClickListener {
            imageView.loadUrl("Your Image Url")
        }

        btnCallApi.setOnClickListener {
            getWeather()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // RxJava + Retrofit 串接 Web API
    private fun getWeather() {
        AppApi.instance
            .getWeather("Your Token", "JSON", "新北市", "time")
            .compose(RxUtils.applySchedulersWithLoading(this))
            .subscribe(object : Observer<Weather> {
                override fun onComplete() {}
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: Weather) {
                    showToast("是否成功：" + t.success)
                }

                override fun onError(e: Throwable) {
                    showToast(e.message)
                }

            })
    }
}
