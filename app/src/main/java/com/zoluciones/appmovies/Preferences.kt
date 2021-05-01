package com.zoluciones.appmovies

import android.content.Context

object Preferences {

    private const val PREF_FILE_KEY = "com.zoluciones.appmovies"
    private const val PREF_PAGE = "PAGE"

    fun setPage(context: Context, page: Int) {
        val sharedPreferences = context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().apply() {
            putInt(PREF_PAGE, page)
            apply()
        }
    }
    fun getPage(context: Context): Int{
        val sharedPreferences = context.getSharedPreferences(PREF_FILE_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(PREF_PAGE, 0)
    }
}