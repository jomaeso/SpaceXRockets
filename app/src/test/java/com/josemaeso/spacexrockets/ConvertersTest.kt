package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.converters.Converters
import junit.framework.Assert.assertEquals
import org.junit.Test

class ConvertersTest {
    @Test
    fun test_fromStringList_emptyList() {
        val sut = makeSUT()

        val result: String = sut.fromStringList(listOf())

        assertEquals("[]", result)
    }

    @Test
    fun test_fromStringList_NotEmptyList() {
        val sut = makeSUT()

        val result: String = sut.fromStringList(listOf("one", "two", "three"))

        assertEquals("[\"one\",\"two\",\"three\"]", result)
    }

    @Test
    fun test_fromJsonToStringList_emptyList() {
        val sut = makeSUT()

        val result: List<String> = sut.fromJsonToStringList("[]")

        assertEquals(listOf<String>(), result)
    }

    @Test
    fun test_fromJsonToStringList_emptyString() {
        val sut = makeSUT()

        val result: List<String> = sut.fromJsonToStringList("")

        assertEquals(listOf<String>(), result)
    }

    @Test
    fun test_fromJsonToStringList_NotEmptyListString() {
        val sut = makeSUT()

        val result: List<String> = sut.fromJsonToStringList("[\"one\",\"two\",\"three\"]")

        assertEquals(listOf("one", "two", "three"), result)
    }

    @Test
    fun test_fromJsonToStringList_InvalidArrayJsonString() {
        val sut = makeSUT()

        val result: List<String> = sut.fromJsonToStringList("{&%(=?¿")

        assertEquals(listOf<String>(), result)
    }

    private fun makeSUT(): Converters {
        return Converters()
    }
}