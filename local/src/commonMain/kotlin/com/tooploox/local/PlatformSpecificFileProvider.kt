package com.tooploox.local

expect class PlatformSpecificFileProvider : FileProvider{
    override fun provideTextFile(): String
}

interface FileProvider {
    fun provideTextFile(): String
}