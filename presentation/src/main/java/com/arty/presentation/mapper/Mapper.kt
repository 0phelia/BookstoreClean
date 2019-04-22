package com.arty.presentation.mapper

interface Mapper<in D, out V> {

    fun mapToView(type: D): V

}