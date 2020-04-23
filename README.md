[![](https://jitpack.io/v/ray650128/BaseFramework.svg)](https://jitpack.io/#ray650128/BaseFramework) [![Platform](https://img.shields.io/badge/平台-%20Android%20-brightgreen.svg)](https://github.com/ray650128)

# BaseFramework
***
基於 Android APP 開發的常用套件所製作的框架。

內建常用套件：
*  Glide
*  Retrofit2
*  RxJava
*  自製套件等

***
Changelog

v1.0.0 - 第一次發布

## 目錄
* [如何導入到專案](#Import)
* [快速使用](#Use)
* [所有對外暴露方法](#All)
* [關於我](#About)

<a name="Import"></a>
## 如何導入到項目
> 支持 jcenter。 <br/>

### jcenter 導入方式
在app專案包的 build.gradle 中添加
```kotlin
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

在需要用到這個庫的 module 中的 build.gradle 中的 dependencies 中加入
```kotlin
dependencies {
implementation 'com.github.ray650128:BaseFramework:v1.0.0'
```
<a name="Use"></a>
## 如何使用

### 第一部分：將 Activity 及 Fragment 都繼承自框架中的 BaseActivity 和 BaseFragement
#### 1.Activity 部分

```kotlin
class MainActivity : BaseActivity() {
    // Layout 文件
    override val layoutId: Int = R.layout.activity_main

    // 初始化資料
    override fun initData() {}

    // 初始化 UI 元件
    override fun initWidget() {}
}
```
#### 2.Fragment 部分
```kotlin
/*R.layout.activity_main替換為你的layout id*/
class MainFragment : BaseFragement() {
    // Layout 文件
    override val layoutId: Int = R.layout.activity_main

    // 初始化資料
    override fun initData() {}

    // 初始化 UI 元件
    override fun initWidget() {}
}


```
### 第二步:在任何地方進行使用
#### 頁面的切換
```kotlin
//普通切換
startActivity<跳轉的Activity名稱>()
//自定義轉場動畫
startActivity<跳轉的Activity名稱>("參數A_Key" to "參數A_Value", "參數B_Key" to "參數B_Value" ...)
```
#### 顯示載入中 Dialog
```kotlin
// 顯示預設文字為載入中的 ProgressDialog
startProgressDialog()

// 顯示自訂文字的 ProgressDialog
startProgressDialog("文字")

// 顯示自訂文字的 ProgressDialog，並可控制是否可以手動關閉
startProgressDialog("文字", true/false)
```
#### 顯示 Toast
```kotlin
// 顯示 Toast
showToast("文字")

// 顯示置中的 Toast
showToastCenter("文字")
```
<a name="All"></a>
### 提供的方法或工具類別
```kotlin
coming soon...
```

<a name="About"></a>
### 關於我
Raymond Yang
Android App developer

*gmail:ray650128@gmail.com
