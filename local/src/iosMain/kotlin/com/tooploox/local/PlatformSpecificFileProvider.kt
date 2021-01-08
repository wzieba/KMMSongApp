package com.tooploox.local

import platform.Foundation.*


@Suppress("CAST_NEVER_SUCCEEDS")
actual class PlatformSpecificFileProvider : FileProvider {

    actual override fun provideTextFile(): String {
        val pathForResource =
            NSBundle.mainBundle.pathForResource(name = "songs-list", ofType = "json")!!

        return NSString.create(contentsOfFile = pathForResource) as String
    }
}
