package com.example.bootcampmarket.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "app_prefs"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)
