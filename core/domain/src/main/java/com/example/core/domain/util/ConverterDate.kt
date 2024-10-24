package com.example.core.domain.util

enum class ConverterDate(val formatter: String) {
    FULL_DATE("dd.MM.yyyy"),
    SQL_DATE("yyyy-MM-dd"),
}