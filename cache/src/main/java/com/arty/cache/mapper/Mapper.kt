package com.arty.cache.mapper

interface Mapper<Q, E, M> {
    fun mapQueryResultToEntity(type: Q): E

    fun mapEntityToModelsBundle(type: E): M
}
