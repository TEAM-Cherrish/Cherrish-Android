package com.cherrish.android.presentation.home.type

enum class DowntimePhase(
    val phaseName: String
) {
    SENSITIVE("민감기"),
    CAUTION("주의기"),
    RECOVERY("회복기")
}

fun String.toDowntimePhase(): DowntimePhase =
    runCatching { DowntimePhase.valueOf(this) }
        .getOrElse { DowntimePhase.SENSITIVE }
