package com.bmi.calculator.presentation.util

import android.graphics.Bitmap

object BitmapUtils {

    fun resizeWithRatio(source: Bitmap, maxLength: Int): Bitmap {
        return try {
            val width = source.width
            val height = source.height

            val targetWidth: Int
            val targetHeight: Int
            if (width > height) {
                if (width < maxLength) return source

                val ratio = height.toDouble() / width.toDouble()
                targetWidth = maxLength
                targetHeight = (maxLength * ratio).toInt()
            } else {
                if (height < maxLength) return source

                val ratio = width.toDouble() / height.toDouble()
                targetHeight = maxLength
                targetWidth = (maxLength * ratio).toInt()
            }

            Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
        } catch (ex: Exception) {
            ex.printStackTrace()
            source
        }
    }

}