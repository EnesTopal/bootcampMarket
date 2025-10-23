package com.example.bootcampmarket.data.datasources

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.bootcampmarket.data.dataStore
import kotlinx.coroutines.flow.first

class ProfileStore(var context: Context) {
    val KEY_PROFILES = stringSetPreferencesKey("profiles")
    val KEY_SELECTED_PROFILE = stringPreferencesKey("selected_profile")

    suspend fun tumProfilleriGetir(): List<String> {
        val prefs = context.dataStore.data.first()
        var set = prefs[KEY_PROFILES] ?: emptySet()

        if (set.isEmpty()) {
            context.dataStore.edit { editPrefs ->
                editPrefs[KEY_PROFILES] = set + "enes_topal"
            }
            set = set + "enes_topal"
        }

        return set.toList()
    }

    suspend fun profilEkle(username: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_PROFILES] ?: emptySet()
            prefs[KEY_PROFILES] = current + username
        }
    }

    suspend fun profilSil(username: String) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_PROFILES] ?: emptySet()
            prefs[KEY_PROFILES] = current - username
        }
    }

    suspend fun seciliProfiliGetir(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[KEY_SELECTED_PROFILE]
    }

    suspend fun secilenProfiliAta(username: String?) {
        context.dataStore.edit { prefs ->
            if (username == null) {
                prefs.remove(KEY_SELECTED_PROFILE)
            } else {
                prefs[KEY_SELECTED_PROFILE] = username
            }
        }
    }

//    suspend fun replaceAll(usernames: Set<String>) {
//        context.dataStore.edit { prefs ->
//            prefs[KEY_PROFILES] = usernames
//        }
//    }
}