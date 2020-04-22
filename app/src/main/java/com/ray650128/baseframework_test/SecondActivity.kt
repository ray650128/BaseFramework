package com.ray650128.baseframework_test

import android.os.Bundle
import com.ray650128.baseframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent != null) {
            val bundle = intent.extras

            textView.text = bundle?.getString("INPUT_TEXT")
        }
    }
}
