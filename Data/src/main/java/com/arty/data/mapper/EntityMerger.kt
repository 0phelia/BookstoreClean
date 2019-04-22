package com.arty.data.mapper

interface EntityMerger<E1, E2, R> {

    fun merge(entityFirst: E1, entitySecond: E2): R

}
