package com.ray650128.baseframework.utility

import android.annotation.SuppressLint
import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class FileUtils(context: Context) {
    private val rootFolderPath = context.getExternalFilesDir("tmp")!!.path

    fun createImageFile(isCrop: Boolean = false): File? {
        return try {
            val rootFile = File(rootFolderPath)
            if (!rootFile.exists())
                rootFile.mkdirs()

            val fileName = if (isCrop) "image_CROP.jpg" else "image.jpg"
            File(rootFile.absolutePath + File.separator + fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun createCameraFile(): File? {
        return try {
            val rootFile = File(rootFolderPath + File.separator + "camera1")
            if (!rootFile.exists())
                rootFile.mkdirs()

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fileName = "IMG_$timeStamp.jpg"
            File(rootFile.absolutePath + File.separator + fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun createVideoFile(): File? {
        return try {
            val rootFile = File(rootFolderPath + File.separator + "video")
            if (!rootFile.exists())
                rootFile.mkdirs()

            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val fileName = "VIDEO_$timeStamp.mp4"
            File(rootFile.absolutePath + File.separator + fileName)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}