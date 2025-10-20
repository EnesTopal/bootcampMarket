package com.example.bootcampmarket.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.bootcampmarket.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title:String) {
    CenterAlignedTopAppBar(
        title = { Text(text = title)},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.main_color),
            titleContentColor = colorResource(id = R.color.white),
        )
    )
}