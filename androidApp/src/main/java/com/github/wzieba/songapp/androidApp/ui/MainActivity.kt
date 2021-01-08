package com.github.wzieba.songapp.androidApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.github.wzieba.songapp.androidApp.ui.songs.SongsScreen
import com.github.wzieba.songapp.androidApp.ui.theme.SongAppTheme
import com.github.wzieba.songapp.domain.ObserveSongs
import com.github.wzieba.songapp.domain.SelectSource
import com.github.wzieba.songapp.domain.SongSource
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by closestDI()

    val observeSongs: ObserveSongs by instance()
    val selectSource: SelectSource by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SongAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Songs App")
                            }
                        )
                    }
                ) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        SongsScreen(observeSongs)
                    }
                }
            }
        }
        selectSource.invoke(setOf(SongSource.ITUNES, SongSource.LOCAL))
    }
}
