package com.cherrish.android.presentation.calendar

import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class CalendarRefreshEventBus {
    private val _events = MutableSharedFlow<CalendarEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val events = _events.asSharedFlow()

    fun emit(event: CalendarEvent) {
        _events.tryEmit(event)
    }
}

sealed interface CalendarEvent {
    data object RefreshRequired : CalendarEvent
}

val LocalCalendarEventBus = compositionLocalOf { CalendarRefreshEventBus() }