package com.tiamosu.fly.kts

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * @author ti
 * @date 2022/9/13.
 */

val appContext by lazy { AppContentProvider.application }

class AppContentProvider : ContentProvider() {
    companion object {
        lateinit var application: Application
    }

    override fun onCreate(): Boolean {
        application = checkNotNull((context?.applicationContext as? Application))
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}