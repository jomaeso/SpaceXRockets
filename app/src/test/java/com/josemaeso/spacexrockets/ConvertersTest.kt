package com.josemaeso.spacexrockets

import com.josemaeso.spacexrockets.data.converters.Converters
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConvertersTest {
    private lateinit var sut: Converters

    @Before
    fun setup() {
        sut = Converters()
    }

    @Test
    fun test_fromStringList_emptyList() {
        val result: String = sut.fromStringList(listOf())

        assertEquals("[]", result)
    }

    @Test
    fun test_fromStringList_NotEmptyList() {
        val result: String = sut.fromStringList(listOf("one", "two", "three"))

        assertEquals("[\"one\",\"two\",\"three\"]", result)
    }

    @Test
    fun test_fromJsonToStringList_emptyList() {
        val result: List<String> = sut.fromJsonToStringList("[]")

        assertEquals(listOf<String>(), result)
    }

    @Test
    fun test_fromJsonToStringList_emptyString() {
        val result: List<String> = sut.fromJsonToStringList("")

        assertEquals(listOf<String>(), result)
    }

    @Test
    fun test_fromJsonToStringList_NotEmptyListString() {
        val result: List<String> = sut.fromJsonToStringList("[\"one\",\"two\",\"three\"]")

        assertEquals(listOf("one", "two", "three"), result)
    }

    @Test
    fun test_fromJsonToStringList_InvalidArrayJsonString() {
        val result: List<String> = sut.fromJsonToStringList("{&%(=?¿")

        assertEquals(listOf<String>(), result)
    }
}