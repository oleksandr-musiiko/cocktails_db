package com.omusiiko.coctaildb

import org.mockito.Mockito
import kotlin.reflect.KClass

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

inline fun <reified T : Any> any(): T {
    return Mockito.any(T::class.java) ?: createInstance()
}

inline fun <reified T : Any> createInstance(): T {
    return createInstance(T::class)
}

fun <T : Any> createInstance(kClass: KClass<T>): T = null as T

