package com.tooploox.local

import com.github.wzieba.songapp.domain.Song
import com.github.wzieba.songapp.domain.SongSource
import com.github.wzieba.songapp.domain.SongsRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LocalSongRepository(
    private val fileProvider: FileProvider,
    private val json: Json,
    private val contextFacade: CoroutinesContextFacade,
) : SongsRepository {

    override val type = SongSource.LOCAL

    override fun observeSongs() = flow {
        emit(fileProvider.provideTextFile())
    }.mapLatest { rawJsonString ->

        val localSongs = json.decodeFromString<List<LocalSong>>(rawJsonString)

        localSongs.mapNotNull { (title, artistName, releaseYear) ->
            if (title == null || artistName == null || releaseYear == null) {
                return@mapNotNull null
            } else {
                Song(title, artistName, releaseYear)
            }
        }
    }.flowOn(contextFacade.io)
}
