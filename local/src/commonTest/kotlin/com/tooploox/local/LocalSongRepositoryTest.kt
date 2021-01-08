package com.tooploox.local

import com.github.wzieba.songapp.domain.Song
import kotlinx.coroutines.flow.collect
import kotlinx.serialization.SerializationException
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.asserter
import kotlin.runCatching

internal class LocalSongRepositoryTest {

    private lateinit var sut: LocalSongRepository

    lateinit var havingJson: String

    @BeforeTest
    fun setUp() {
        sut = LocalSongRepository(
            fileProvider = object : FileProvider {
                override fun provideTextFile() = havingJson
            },
            json = songJson,
            contextFacade = TestCoroutinesContextFacade
        )
    }

    @Test
    fun shouldDeserializeSongWhenSourceIsValid() = runBlockingTest {
        havingJson = valid

        sut.observeSongs().collect { collectedSongs ->
            asserter.assertEquals(
                message = "",
                expected = listOf(Song("Caught Up in You", ".38 Special", "1982")),
                actual = collectedSongs,
            )
        }
    }

    @Test
    fun shouldNotDeserializeWhenTitleIsEmpty() = runBlockingTest {
        havingJson = noTitle

        val result = runCatching { sut.observeSongs().collect() }

        asserter.assertTrue(
            "Exception should be thrown",
            result.exceptionOrNull() is SerializationException
        )
    }

    @Test
    fun shouldNotDeserializeWhenArtistNameIsEmpty() = runBlockingTest {
        havingJson = noArtistName

        val result = kotlin.runCatching { sut.observeSongs().collect() }

        asserter.assertTrue(
            "Exception should be thrown",
            result.exceptionOrNull() is SerializationException
        )
    }

    @Test
    fun shouldNotDeserializeWhenReleaseYearIsEmpty() = runBlockingTest {
        havingJson = noReleaseYear

        val result = kotlin.runCatching { sut.observeSongs().collect() }

        asserter.assertTrue(
            "Exception should be thrown",
            result.exceptionOrNull() is SerializationException
        )
    }
}
