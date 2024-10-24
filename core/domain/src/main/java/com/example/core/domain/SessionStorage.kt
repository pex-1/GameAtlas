package com.example.core.domain

interface SessionStorage {
    suspend fun get(): List<Int>
    suspend fun set(info: List<Int>)
}