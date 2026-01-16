package com.cherrish.android.presentation.calendar.model

enum class DownTimeStatus(
    val label: String
) {
    NONE(""),
    SENSITIVE("민감"),
    CAUTION("주의"),
    RECOVERY("회복")
}
