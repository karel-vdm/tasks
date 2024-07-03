package com.ordercloud.virginactivetasks.domain.usecase

import java.text.SimpleDateFormat
import java.util.Locale

class GetDateAsTimeStampUseCase {
    suspend fun execute(date: String): Long {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.parse(date)?.time ?: return Long.MIN_VALUE
    }
}