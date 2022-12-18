package utils


import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    var KR_TIME_DIFF = 9 * 60 * 60 * 1000; //UTC to KST

    fun getCurTime(): Long {
        return this.tomillisecond(this.toStringMS(System.currentTimeMillis()+KR_TIME_DIFF))
    }

    fun tomillisecond(time: String): Long { //HH:mm 파싱
        return SimpleDateFormat("HH:mm", Locale.KOREA).parse(time).getTime()
    }

    fun tomillisecondLong(time: String) : Long { //yyyy-MM-dd'T'HH:mm:ss 파싱
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA).parse(time).getTime()
    }


    fun toStringMS(millisecond : Long): String{ //hh:mm
        return SimpleDateFormat("HH:mm",Locale.KOREA).format(millisecond-KR_TIME_DIFF)
    }

    fun toStringAMSLong(millisecond : Long): String{ //aa hh:mm (오전, 오후)
        return SimpleDateFormat("MM.dd aa hh:mm",Locale.KOREA).format(millisecond)
    }

    fun toStringAMS(millisecond : Long): String{ //aa hh:mm (오전, 오후)
        return SimpleDateFormat("aa hh:mm",Locale.KOREA).format(millisecond)
    }

    fun toStringKorean(millisecond : Long): String{ //hh시 mm분
        return SimpleDateFormat("H시 mm분",Locale.KOREA).format(millisecond-KR_TIME_DIFF)
    }
}
