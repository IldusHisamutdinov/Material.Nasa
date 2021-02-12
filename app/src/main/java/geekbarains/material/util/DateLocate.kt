package geekbarains.material.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@SuppressLint("ConstantLocale")
val locate: Locale = Locale.getDefault()
val formatter = SimpleDateFormat("yyyy-MM-dd", locate)


@RequiresApi(Build.VERSION_CODES.O)
fun getDate(days: Int): String =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    LocalDate.now().plusDays(days.toLong()).toString()
} else {
    val date = Calendar.getInstance(locate).let {
        it.add(Calendar.DATE, days)
        it.time
    }
    formatter.format(date)
}