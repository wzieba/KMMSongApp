package com.tooploox.local

import com.github.wzieba.songapp.domain.ObserveSongs
import com.github.wzieba.songapp.domain.SelectSource
import com.github.wzieba.songapp.domain.domain
import org.kodein.di.DI
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object iOSInjector {
    val kodeinContainer = DI.lazy {
        importAll(domain, local)
    }

    fun observeSongs() = kodeinContainer.direct.instance<ObserveSongs>()
    fun selectSource() = kodeinContainer.direct.instance<SelectSource>()
}
