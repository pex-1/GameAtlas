package com.example.feature.genres.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.example.feature.genres.GenreViewModel

val genreFeatureModule = module {
    viewModelOf(::GenreViewModel)
}