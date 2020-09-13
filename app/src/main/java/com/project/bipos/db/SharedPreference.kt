package com.project.bipos.db

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {

    private val PREF_NAME = "bipos-db"
    private val PREF_MODE = 0

    private val preference: SharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    fun saveString(key: String, value: String) {
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return preference.getString(key, "")
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return preference.getBoolean(key, false)
    }
}