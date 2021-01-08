package com.github.wzieba.songapp.androidApp.ui.songs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import com.github.wzieba.songapp.androidApp.ui.songs.component.SongItem
import com.github.wzieba.songapp.domain.ObserveSongs
import com.github.wzieba.songapp.domain.Song
import kotlinx.coroutines.flow.map

@Composable
fun SongsScreen(
    observeSongs: ObserveSongs,
) {
    val songs: List<Song> by observeSongs.invoke().map { result -> result.songs }.asLiveData()
        .observeAsState(emptyList())
    LazyColumnForIndexed(items = songs, contentPadding = PaddingValues(8.dp)) { position, song ->
        Column {
            SongItem(song = song)
            if (position < songs.size) {
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}
