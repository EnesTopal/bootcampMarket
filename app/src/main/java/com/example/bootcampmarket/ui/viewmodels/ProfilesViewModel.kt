package com.example.bootcampmarket.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcampmarket.data.datasources.ProfileStore
import com.example.bootcampmarket.data.repos.urunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilesViewModel @Inject constructor(
    var urunlerRepository: urunlerRepository,
    var profileStore: ProfileStore
) : ViewModel() {
    var profiller = MutableLiveData<List<String>>()

    var seciliProfil = MutableLiveData<String>()

    init {
        getProfiles()
    }

    fun getProfiles() {
        CoroutineScope(Dispatchers.Main).launch {
            profiller.value = profileStore.tumProfilleriGetir()
            if (profiller.value.isNotEmpty() && seciliProfil.value.isNullOrEmpty()) {
                seciliProfil.value = profiller.value.first()
            }
        }
    }

    fun secilenProfiliAta(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            seciliProfil.value = username
            profileStore.secilenProfiliAta(username)
        }
    }

    fun seciliProfiliGetir() {
        CoroutineScope(Dispatchers.Main).launch {
            seciliProfil.value = profileStore.seciliProfiliGetir()
        }
    }

    fun profilEkle(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            profileStore.profilEkle(username)
            getProfiles()
        }
    }

    fun profilSil(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            profileStore.profilSil(username)
            getProfiles()
        }
    }
}