package geekbarains.material.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbarains.material.R
import geekbarains.material.ui.picture.PictureOfTheDayFragment
import geekbarains.material.ui.settings.sharedPreferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(when(getIntPreference("theme")){
            1 -> R.style.TestStyleOne
            2 -> R.style.TestStyleTwo
            3 -> R.style.TestStyleThree
            else -> R.style.AppTheme
        })
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }


    fun getIntPreference(parameter: String): Int {
        return sharedPreferences.getInt(parameter, 0)
    }
}
