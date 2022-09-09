package com.tiamosu.fly.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.net.toFile
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.FileUtils
import java.io.File
import java.util.*

/**
 * @author ti
 * @date 2022/7/6.
 */
object FlyUriUtils {

    fun uriToFile(context: Context, uri: Uri): File? {
        if (uri.scheme.equals(ContentResolver.SCHEME_FILE)) {
            return uri.path?.let { File(it) }
        }

        var file: File? = null //android10以上转换
        if (uri.scheme.equals(ContentResolver.SCHEME_CONTENT)) { //把文件复制到沙盒目录
            val contentResolver = context.contentResolver
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor?.moveToFirst() == true) {
                val columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val displayName = cursor.getString(columnIndex)
                kotlin.runCatching {
                    contentResolver.openInputStream(uri).use { `is` ->
                        val cachePath = context.externalCacheDir?.absolutePath ?: ""
                        val dir = "${cachePath}${File.separator}file_cache"
                        FileUtils.createOrExistsDir(dir)
                        var cache = File(dir, displayName)
                        if (FileUtils.isFileExists(cache)) {
                            //如果存在,说明已经写入过了,那么判断hash值是不是一样
                            val oldHash = Arrays.hashCode(FileUtils.getFileMD5(uri.toFile()))
                            val newHash = Arrays.hashCode(FileUtils.getFileMD5(cache))
                            if (newHash == oldHash) {
                                return cache
                            }
                            cache = File(cachePath, "${System.currentTimeMillis()}${displayName}")
                        }
                        if (FileIOUtils.writeFileFromIS(cache.absolutePath, `is`)) {
                            file = cache
                        }
                    }
                }
            }
            cursor?.close()
        }
        return file
    }
}