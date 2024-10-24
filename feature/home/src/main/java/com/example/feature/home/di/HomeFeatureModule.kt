package com.example.feature.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.feature.home.GamesViewModel

val homeFeatureModule = module {
    viewModelOf(::GamesViewModel)
}