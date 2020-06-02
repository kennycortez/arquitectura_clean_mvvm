package com.example.storage

enum class StorageLevel(val value: Int) {
    /**
     * Constant used to save non-sensitive information. All data of this level must be deleted at the end of the session.
     */
    NON_SENSITIVE_NON_PERSIST(1),

    /**
     * Constant used to save non-sensitive information. All data of this level must be persist at the end of the session.
     */
    NON_SENSITIVE_PERSIST(2),

}