package geekbarains.material.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import geekbarains.material.R
import kotlinx.android.synthetic.main.fragment_settings.*

val sharedPreferences: SharedPreferences = TODO()

class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            when(getIntPreference("theme")){
            1 -> R.style.TestStyleOne
            2 -> R.style.TestStyleTwo
            3 -> R.style.TestStyleThree
            else -> R.style.AppTheme
        }
        }
        light_theme.setOnClickListener {
            setIntPreference("theme",1)
            activity?.recreate()
        }
        dark_theme.setOnClickListener {
            setIntPreference("theme",2)
            activity?.recreate()
        }
        blue_theme.setOnClickListener {
            setIntPreference("theme",3)
            activity?.recreate()
        }
    }

    fun setIntPreference(parameter: String, value: Int) {
        sharedPreferences.edit().putInt(parameter, value).apply()
    }
    fun getIntPreference(parameter: String): Int {
        return sharedPreferences.getInt(parameter, 0)
    }


    fun changeTheme() {
//        switchMaterial.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                // The switch is enabled/checked
//                activity?.recreate()
//            }
//
//        }


    }
}
