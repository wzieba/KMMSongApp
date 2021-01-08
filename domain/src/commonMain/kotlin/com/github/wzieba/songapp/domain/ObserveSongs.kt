package com.github.wzieba.songapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class ObserveSongs(
    private val repositories: Set<SongsRepository>,
    private val selectedSongSourcesRepository: SelectedSongSourcesRepository
) {

    fun invoke(): CommonFlow<SongsResult> = selectedSongSourcesRepository.state
        .flatMapConcat { sources ->
            combine(repositories
                .filter { sources.contains(it.type) }
                .map(SongsRepository::observeSongs)) { arrayOfLists ->
                arrayOfLists.flatMap {
                    it
                }
            }.map {
                SongsResult(it)
            }
        }.asCommonFlow()
}

data class SongsResult(val songs: List<Song>)