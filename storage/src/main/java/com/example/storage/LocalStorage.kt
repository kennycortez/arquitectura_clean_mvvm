package com.example.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.storage.util.Util

/**
 * LocalStorage
 *
 * Created by kenny Cortez on 2020-05-01
 */

class LocalStorage : Storage {

    private val sharedPreferences: SharedPreferences
    private var decrypted: String? = null

    companion object {
        private const val PREFERENCES_NAME = "com.bcp.core.storage"
        private const val SEPARATOR = "*"
    }

    init {
        sharedPreferences =
            Util.context().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Set a String value
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param level security level with which it will be saved
     *
     */
    override fun set(key: String, value: String, level: StorageLevel) = setAny(key, value, level)

    /**
     * Set a Int value
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param level security level with which it will be saved
     *
     */
    override fun set(key: String, value: Int, level: StorageLevel) = setAny(key, value, level)

    /**
     * Set a Float value
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param level security level with which it will be saved
     *
     */
    override fun set(key: String, value: Float, level: StorageLevel) = setAny(key, value, level)

    /**
     * Set a Boolean value
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param level security level with which it will be saved
     *
     */
    override fun set(key: String, value: Boolean, level: StorageLevel) = setAny(key, value, level)

    /**
     * Set a Long value
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param level security level with which it will be saved
     *
     */
    override fun set(key: String, value: Long, level: StorageLevel) = setAny(key, value, level)

    /**
     * Return a String value or null
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     * @return Returns the value if it exists, or null.
     *
     */
    override fun getString(key: String, level: StorageLevel): String? =
        getAny(key, Type.STRING, level) as String?

    /**
     * Return a Int value or null
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     * @return Returns the value if it exists, or null.
     *
     */
    override fun getInt(key: String, level: StorageLevel): Int? =
        getAny(key, Type.INT, level) as Int?

    /**
     * Return a Float value or null
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     * @return Returns the value if it exists, or null.
     *
     */
    override fun getFloat(key: String, level: StorageLevel): Float? =
        getAny(key, Type.FLOAT, level) as Float?

    /**
     * Return a Boolean value or null
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     * @return Returns the value if it exists, or null.
     *
     */
    override fun getBoolean(key: String, level: StorageLevel): Boolean? =
        getAny(key, Type.BOOLEAN, level) as Boolean?

    /**
     * Return a Long value or null
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     * @return Returns the value if it exists, or null.
     *
     */
    override fun getLong(key: String, level: StorageLevel): Long? =
        getAny(key, Type.LONG, level) as Long?

    /**
     * Delete the mapping for the specified key and level
     *
     * @param key key with which the specified value is to be associated
     * @param level security level with which it was saved
     *
     */
    override fun delete(key: String, level: StorageLevel) = deleteAny(key, level)

    /**
     * Remove all objects for a certain level security
     *
     * @param level security level with which the data is stored
     *
     */
    override fun reset(level: StorageLevel) {

        val edit = sharedPreferences.edit()

        sharedPreferences.all.forEach {
            val key = it.key.split(SEPARATOR)[0]

            if (key == level.value.toString()) {
                edit.remove(it.key)
            }
        }

        edit.apply()
    }

    private fun setAny(key: String, value: Any, level: StorageLevel) {

        val newKey = createKey(key, level)
        val edit = sharedPreferences.edit()

        if (StorageLevel.NON_SENSITIVE_NON_PERSIST == level || StorageLevel.NON_SENSITIVE_PERSIST == level) {
            when (value) {
                is Float -> edit.putFloat(newKey, value)
                is String -> edit.putString(newKey, value)
                is Boolean -> edit.putBoolean(newKey, value)
                is Long -> edit.putLong(newKey, value)
                is Int -> edit.putInt(newKey, value)
            }
        }
        edit.apply()
    }

    private fun getAny(key: String, type: Type, level: StorageLevel): Any? {

        val newKey = createKey(key, level)

        if (!sharedPreferences.contains(newKey)) {
            return null
        }

        return if (StorageLevel.NON_SENSITIVE_NON_PERSIST == level || StorageLevel.NON_SENSITIVE_PERSIST == level) {
            when (type) {
                Type.FLOAT -> sharedPreferences.getFloat(newKey, -1f)
                Type.STRING -> sharedPreferences.getString(newKey, null)
                Type.BOOLEAN -> sharedPreferences.getBoolean(newKey, false)
                Type.LONG -> sharedPreferences.getLong(newKey, -1L)
                Type.INT -> sharedPreferences.getInt(newKey, -1)
            }
        } else{
            null
        }
    }

    private fun deleteAny(key: String, level: StorageLevel) {
        val newKey = createKey(key, level)
        sharedPreferences.edit().remove(newKey).apply()
    }

    private fun createKey(key: String, level: StorageLevel): String {
        return when (level) {
            StorageLevel.NON_SENSITIVE_NON_PERSIST -> "${StorageLevel.NON_SENSITIVE_NON_PERSIST.value}$SEPARATOR$key"
            StorageLevel.NON_SENSITIVE_PERSIST -> "${StorageLevel.NON_SENSITIVE_PERSIST.value}$SEPARATOR$key"
        }
    }
}