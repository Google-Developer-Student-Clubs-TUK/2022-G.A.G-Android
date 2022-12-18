package utils


import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    var KR_TIME_DIFF = 9 * 60 * 60 * 1000; //UTC to KST


    fun tomillisecond(time: String): Long {
        return SimpleDateFormat("HH:mm", Locale.KOREA).parse(time).getTime()
    }

    fun toString(millisecond : Long): String{
        return SimpleDateFormat("HH:mm",Locale.KOREA).format(millisecond-KR_TIME_DIFF)
    }
}
