package com.example.feature.details.di

import com.example.feature.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val detailsFeatureModule = module {
    viewModelOf(::DetailsViewModel)
}