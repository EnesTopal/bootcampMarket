package com.example.bootcampmarket.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampmarket.ui.components.CustomBottomBar
import com.example.bootcampmarket.ui.components.CustomTopAppBar
import com.example.bootcampmarket.ui.viewmodels.ProfilesViewModel

@Composable
fun ProfilesScreen(navController: NavController, profilesViewModel: ProfilesViewModel) {

    val profiller = profilesViewModel.profiller.observeAsState(listOf())
    val seciliProfil = profilesViewModel.seciliProfil.observeAsState("")

    var showDialog = remember { mutableStateOf(false) }
    var newProfileName = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                "Profiller",
                profiller.value,
                onProfileSelected = {
                    profilesViewModel.seciliProfil.value = it
                    Toast.makeText(
                        navController.context,
                        "Seçilen Profil: ${it}",
                        Toast.LENGTH_SHORT
                    ).show()
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Default.Add, contentDescription = "Yeni Profil Ekle")
            }
        },
        bottomBar = { CustomBottomBar(navController) }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            items(profiller.value) { profil ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = profil)

                        IconButton(onClick = {
                            profilesViewModel.profilSil(profil)
                            profilesViewModel.getProfiles()
                            if (seciliProfil.value == profil) {
                                profilesViewModel.secilenProfiliAta(profiller.value.first())
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Profil Sil"
                            )
                        }
                    }
                }
            }

        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Yeni Profil Ekle") },
            text = {
                TextField(
                    value = newProfileName.value,
                    onValueChange = { newProfileName.value = it },
                    placeholder = { Text("Profil ismi") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (newProfileName.value.isNotBlank()) {
                        profilesViewModel.profilEkle(newProfileName.value.trim())
                        newProfileName.value = ""
                        showDialog.value = false
                    }
                }) {
                    Text("Ekle")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("İptal")
                }
            }
        )
    }
}



