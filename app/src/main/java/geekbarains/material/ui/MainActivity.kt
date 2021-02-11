package geekbarains.material.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbarains.material.R
import geekbarains.material.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(
            when (loadSharedPrefs()) {
                1 -> R.style.Robin_Egg_Blue
                2 -> R.style.Persian_Blue
                3 -> R.style.Azalea
                4 -> R.style.AppTheme
                else -> {
                    when(loadSharedPrefses()){
                       false -> R.style.AppTheme
                        else -> R.style.Night}
                }
            }
        )


        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    private fun loadSharedPrefs(): Int =
        getSharedPreferences(Constants.KEY_CUSTOM_THEME_CHECKED, Context.MODE_PRIVATE)
            .getInt(
                Constants.THEME, R.style.AppTheme
            )
    private fun loadSharedPrefses(): Boolean =
        getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
            .getBoolean(
                Constants.SWITCH, false
            )
}






