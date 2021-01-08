package com.github.wzieba.songapp.androidApp.ui.songs.component

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.wzieba.songapp.androidApp.ui.theme.SongAppTheme
import com.github.wzieba.songapp.domain.Song

@Composable
fun SongItem(modifier: Modifier = Modifier, song: Song) {
    Card {
        Column(
            modifier = modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(text = song.name, style = MaterialTheme.typography.h5)
            Text(text = song.artistName, style = MaterialTheme.typography.body2)
            Text(text = song.releaseYear, style = MaterialTheme.typography.caption)
        }
    }
}

@Preview("SongItem light")
@Composable
fun previewSongItem() {
    SongAppTheme(darkTheme = false) {
        SongItem(song = Song("Song name", "Artist name", "1999"))
    }
}

@Preview("SongItem dark")
@Composable
fun previewDarkSongItem() {
    SongAppTheme(darkTheme = true) {
        SongItem(song = Song("Song name", "Artist name", "1999"))
    }
}
