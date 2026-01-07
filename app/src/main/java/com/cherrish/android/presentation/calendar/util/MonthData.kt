import com.cherrish.android.presentation.calendar.model.CalendarDay
import com.cherrish.android.presentation.calendar.model.CalendarMonth
import com.cherrish.android.presentation.calendar.model.DownTimeStatus
import com.cherrish.android.presentation.calendar.util.atStartOfMonth
import com.cherrish.android.presentation.calendar.util.daysUntil
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

data class MonthData(
    private val month: YearMonth,
    private val inDays: Int,
    private val procedureCountByDate: Map<LocalDate, Int>,
    private val downtimeByDate: Map<LocalDate, DownTimeStatus>
) {
    private val monthLength = month.lengthOfMonth()
    private val totalDays = inDays + monthLength
    private val totalWeeks = (totalDays + 6) / 7
    private val totalCells = totalWeeks * 7
    private val firstDay = month.atStartOfMonth()
    private val rows = (0 until totalCells).chunked(7)

    val calendarMonth: CalendarMonth = CalendarMonth(
        month,
        rows.map { week ->
            week.map { dayOffset -> getDay(dayOffset) }
        }
    )

    private fun getDay(dayOffset: Int): CalendarDay {
        if (dayOffset < inDays || dayOffset >= inDays + monthLength) {
            return CalendarDay.Empty
        }

        val date = firstDay.plusDays((dayOffset - inDays).toLong())
        val procedureCount = procedureCountByDate[date] ?: 0
        val downtimeStatus = downtimeByDate[date]

        return CalendarDay.Date(
            date = date,
            procedureCount = procedureCount,
            downtimeStatus = downtimeStatus
        )
    }
}

fun generateMonthData(
    yearMonth: YearMonth,
    firstDayOfWeek: DayOfWeek,
    procedureCountByDate: Map<LocalDate, Int>,
    downtimeByDate: Map<LocalDate, DownTimeStatus>
): MonthData {
    val firstDay = yearMonth.atStartOfMonth()
    val inDays = firstDayOfWeek.daysUntil(firstDay.dayOfWeek)
    return MonthData(yearMonth, inDays, procedureCountByDate, downtimeByDate)
}
