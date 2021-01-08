package com.tooploox.local

import android.content.Context

actual class PlatformSpecificFileProvider constructor(private val context: Context) : FileProvider {

    actual override fun provideTextFile(): String =
        context.assets.open("songs-list.json").readBytes().decodeToString()
}
