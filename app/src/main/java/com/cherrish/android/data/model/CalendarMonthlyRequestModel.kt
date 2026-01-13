package com.cherrish.android.data.model

import com.cherrish.android.data.remote.dto.request.CalendarMonthlyRequestDto

data class CalendarMonthlyRequestModel(
    val year: Int,
    val month: Int
)

fun CalendarMonthlyRequestModel.toDto() = CalendarMonthlyRequestDto(
    year = this.year,
    month = this.month
)