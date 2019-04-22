package com.arty.domain.test

import java.util.*

object DataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomInt(max: Int): Int {
        return (Math.random() * max + 1).toInt()
    }

    fun randomFloat(max: Int): Float {
        return (Math.random() * max + 1).toFloat()
    }


}