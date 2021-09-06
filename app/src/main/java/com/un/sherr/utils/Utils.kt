package com.un.sherr.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File


class Utils {
    companion object {

        fun dpToPixels(dp: Int, context: Context): Float {
            return dp * context.resources.displayMetrics.density
        }

        fun checkMapPermission(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val permissions = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                for (mPermission in permissions) {
                    val result = ActivityCompat.checkSelfPermission(context, mPermission)
                    if (result == PackageManager.PERMISSION_DENIED) {
                        (context as Activity).requestPermissions(permissions, Constants.ACCESS_LOCATION_PERMISSIONS)
                        return false
                    }
                }
            }
            return true
        }

        fun checkCameraPermission(context: Context): Boolean {
            if (!checkCameraHardware(context))
                return false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val permissions = arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                for (mPermission in permissions) {
                    val result = ActivityCompat.checkSelfPermission(context, mPermission)
                    if (result == PackageManager.PERMISSION_DENIED) {
                        (context as Activity).requestPermissions(permissions, Constants.ACCESS_LOCATION_PERMISSIONS)
                        return false
                    }
                }
            }
            return true
        }

        /** Check if this device has a camera */
        private fun checkCameraHardware(context: Context): Boolean {
            return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
        }

        fun getImages(context: Context): ArrayList<File> {
            val result = arrayListOf<File>()
            val dir = ContextCompat.getExternalFilesDirs(context, "photos")[0]
            dir.mkdirs() // if folder not exists create empty
            val files: Array<File> = dir.listFiles() ?: return arrayListOf()
            val numberOfImages = files.size
            if (numberOfImages != 0) {
                for (f in files) result.add(f)
            }
            return result
        }
        fun deleteImages(context: Context) {
            val dir = ContextCompat.getExternalFilesDirs(context, "photos")[0]
            dir.mkdirs() // if folder not exists create empty
            val files: Array<File> = dir.listFiles() ?: arrayOf()
            val numberOfImages = files.size
            if (numberOfImages != 0) {
                for (f in files) f.delete()
            }
        }

    }
}