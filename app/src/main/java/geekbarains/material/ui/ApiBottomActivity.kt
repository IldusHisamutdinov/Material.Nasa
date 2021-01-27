package geekbarains.material.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geekbarains.material.R
import geekbarains.material.ui.api.EarthFragment
import geekbarains.material.ui.api.MarsFragment
import geekbarains.material.ui.api.WeatherFragment
import kotlinx.android.synthetic.main.activity_api_bottom.*

class ApiBottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(
            when (loadSharedPrefs()) {
                1 -> R.style.Robin_Egg_Blue
                2 -> R.style.Persian_Blue
                3 -> R.style.Azalea
                4 -> R.style.AppTheme
                5 -> R.style.Night
                else -> R.style.AppTheme
            }
        )
        setContentView(R.layout.activity_api_bottom)
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
        bottom_navigation_view.selectedItemId = R.id.bottom_view_earth
        bottom_navigation_view.getOrCreateBadge(R.id.bottom_view_earth)
        val badge = bottom_navigation_view.getBadge(R.id.bottom_view_earth)
        badge?.maxCharacterCount = 2
        badge?.number = 999

        bottom_navigation_view.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    //Item tapped
                }
                R.id.bottom_view_mars -> {
                    //Item tapped
                }
                R.id.bottom_view_weather -> {
                    //Item tapped
                }
            }
        }
    }

    private fun loadSharedPrefs(): Int =
        getSharedPreferences(Constants.KEY_CUSTOM_THEME_CHECKED, Context.MODE_PRIVATE)
            .getInt(
                Constants.THEME, R.style.AppTheme
            )
}
