package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if (year < other.year) -1
        else if(year > other.year) 1
        else if(year >= other.year && month > other.month) 1
        else if (month < other.month) -1
        else dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    class DateIterator(val dateRange: DateRange): Iterator<MyDate> {

        private var current = dateRange.start

        override fun hasNext(): Boolean = current <= dateRange.endInclusive

        override fun next(): MyDate {
            val nextDate = current
            current = current.nextDay()
            return nextDate
        }

    }

    override fun iterator(): Iterator<MyDate> = DateIterator(DateRange(start,endInclusive))

}



class RepeatedTimeInterval(val interval: TimeInterval, val occurences: Int)

fun TimeInterval.times(i: Int) = RepeatedTimeInterval(this, i)

fun MyDate.plus(ti: TimeInterval, number: Int = 1): MyDate =
    addTimeIntervals(ti, number)

fun MyDate.plus(rti: RepeatedTimeInterval): MyDate =
        addTimeIntervals(rti.interval, rti.occurences)