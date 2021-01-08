package com.tooploox.local

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

actual val platformSpecificModule: DI.Module = DI.Module("iOSLocalModule") {
    bind<FileProvider>() with singleton { PlatformSpecificFileProvider() }
}
