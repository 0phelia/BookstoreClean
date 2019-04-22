package com.arty.domain.interactor

abstract class BaseUseCase<Params, T> {

    protected abstract var useCaseFunction: suspend (Params?) -> T

    open suspend fun execute(params: Params? = null): Result<T> {
        return try {
            Result.Success(useCaseFunction.invoke(params))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }

}