package com.example.storage

import androidx.annotation.Keep

/**
 * Created by kenny Cortez on 2020-05-01
 */

interface Storage {

    fun set(key: String, value: String, level: StorageLevel)

    fun set(key: String, value: Int, level: StorageLevel)

    fun set(key: String, value: Float, level: StorageLevel)

    fun set(key: String, value: Boolean, level: StorageLevel)

    fun set(key: String, value: Long, level: StorageLevel)

    fun getString(key: String, level: StorageLevel): String?

    fun getInt(key: String, level: StorageLevel): Int?

    fun getFloat(key: String, level: StorageLevel): Float?

    fun getBoolean(key: String, level: StorageLevel): Boolean?

    fun getLong(key: String, level: StorageLevel): Long?

    fun delete(key: String, level: StorageLevel)

    fun reset(level: StorageLevel)

    @Keep companion object {
        fun newInstance(): Storage {
            return LocalStorage()
        }
    }
}