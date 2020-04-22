package com.ray650128.baseframework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.ray650128.baseframework.utility.DisplayUtils


//------------------------[ Common ]------------------------
// Compress Bitmap
fun Bitmap.scale(scaleRatio: Float): Bitmap {
    val matrix = Matrix()
    matrix.postScale(scaleRatio, scaleRatio) //長和寬放大縮小的比例
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}

// ImageView Load URL
fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url?.trim()).into(this)
}

// ImageView Load url with placeHolder
fun ImageView.loadUrlWithPlaceHolder(
    url: String?,
    defaultImage: Int,
    isCenterCrop: Boolean = false
) {
    var requestOptions1 = RequestOptions()
    if (isCenterCrop) {
        requestOptions1 = requestOptions1.centerCrop()
    }
    val requestOptions = requestOptions1
        .placeholder(defaultImage)
        .error(defaultImage);

    Glide.with(context).load(url?.trim()).apply(requestOptions).into(this)
}

// ImageView Load url circle
fun ImageView.loadUrlCircleWithDefault(url: String?, defaultImage: Int) {
    Glide.with(context)
        .asBitmap()
        .load(url?.trim())

        .apply(RequestOptions().centerCrop().placeholder(defaultImage))
        .into(object : BitmapImageViewTarget(this) {
            @Override
            override fun setResource(resource: Bitmap?) {
                val circularBitmapDrawable: RoundedBitmapDrawable? =
                    RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable?.isCircular = true
                setImageDrawable(circularBitmapDrawable)
            }
        })
}


fun ImageView.loadUrlCircleWithDefault2(url: String?) {
    Glide.with(context)
        .load(url?.trim())
        .apply(
            RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(DisplayUtils.getScreenWidth(context))
        )
        .into(this)
}

// EditText Clear
fun EditText.clear() {
    setText("")
}

// EditText Fill Text
fun EditText.fill(content: String) {
    text = Editable.Factory.getInstance().newEditable(content)

}


//------------------------[ Fragment ]------------------------
// Open App Market
fun androidx.fragment.app.Fragment.openMarket() {
    activity?.openMarket()
}


//------------------------[ Activity ]------------------------
// Start Activity with parameter
inline fun <reified T : Activity> Activity.startActivity(vararg args: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*args))
    startActivity(intent)
}

// Start Activity
inline fun <reified T : Activity> Activity.startActivity(uri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

// Start Activity
inline fun <reified T : Activity> Activity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

// Open App Market
fun Activity.openMarket() {
    val appMarketPackageNames = arrayOf(
        "com.android.vending", //Google Play
        "com.xiaomi.market", //小米
        "com.huawei.appmarket", //華為
        "com.oppo.market", //oppo
        "com.meizu.mstore", //魅族
        "com.bbk.appstore", //vivo
        "com.sec.android.app.samsungapps", //三星
        "com.smartisan.appstore", //鎚子
        "com.smartisanos.appstore",//堅果手機
        "com.oneplus.market", //一加手機
        "com.lenovo.leos.appstore", //聯想應用商店
        "zte.com.market", //中興
        "com.letv.app.appstore", //樂視
        "com.android.meitu.appstore",//美圖
        "com.gionee.aora.market",//金立
        "cn.nubia.neostore", //努比亞
        "com.baidu.appsearch", //百度手機助手
        "com.tencent.android.qqdownloader", //應用寶
        "com.qihoo.appstore", //360手機助手
        "com.sogou.appmall", //搜狗手機助手
        "com.wandoujia.phoenix2", //豌豆莢
        "com.dragon.android.pandaspace", //91手機助手
        "com.hiapk.marketpho", //安智應用商店
        "com.yingyonghui.market", //應用匯
        "com.pp.assistant", //pp手機助手
        "com.tencent.qqpimsecure"
    )//qq手機管家
    appMarketPackageNames.first {
        isInstalled(this, it)
    }.let {
        try {
            val shortcutIntent = packageManager?.getLaunchIntentForPackage(it)
            startActivity(shortcutIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun isInstalled(context: Context, packageName: String): Boolean {
    val packageInfo: PackageInfo? = try {
        context.packageManager.getPackageInfo(packageName, 0)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
    return packageInfo != null
}

// Hide Keyboard
fun Activity.hideKeyboard(): Boolean {
    val view = currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
    return false
}