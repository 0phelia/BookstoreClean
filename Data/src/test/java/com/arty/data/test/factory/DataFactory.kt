package com.arty.data.test.factory

import java.util.*

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(max: Int): Int {
        return (Math.random() * max + 1).toInt()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomFloat(max: Int): Float {
        return (Math.random() * max + 1).toFloat()
    }
}