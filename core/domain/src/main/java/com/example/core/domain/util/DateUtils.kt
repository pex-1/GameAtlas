package com.example.core.domain.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun String.toDate(): LocalDate? {
    val formatter = DateTimeFormatter.ofPattern(ConverterDate.SQL_DATE.formatter)
    return LocalDate.parse(this, formatter)
}

fun LocalDate.dateToString(): String {
    val formatter = DateTimeFormatter.ofPattern(ConverterDate.SQL_DATE.formatter)
    return this.format(formatter)
}

fun LocalDate.simpleDateFormat(): String {
    val formatter = DateTimeFormatter.ofPattern(ConverterDate.FULL_DATE.formatter)
    return this.format(formatter)
}

fun LocalDate.monthFormat(): String {
    val month = this.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val year = this.year
    val day = this.dayOfMonth
    return "$month $day, $year"
}


