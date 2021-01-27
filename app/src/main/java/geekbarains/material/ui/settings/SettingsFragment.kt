package geekbarains.material.ui.settings


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import geekbarains.material.R
import geekbarains.material.ui.Constants

class SettingsFragment : Fragment(), View.OnClickListener {
    val PREF_SWITCH = "pref_switch"
    val sharedPreferences = context?.getSharedPreferences(PREF_SWITCH, MODE_PRIVATE)
    val editor = sharedPreferences?.edit()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val button1: Button = rootView.findViewById(R.id.button1)
        val button2: Button = rootView.findViewById(R.id.button2)
        val button3: Button = rootView.findViewById(R.id.button3)
        val button4: Button = rootView.findViewById(R.id.button4)
        val swich: Switch = rootView.findViewById(R.id.swich)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        swich.setOnClickListener(this)
// switch не запоминает
        swich.isChecked = sharedPreferences?.getBoolean(PREF_SWITCH, true) == true
        swich.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor?.putBoolean(PREF_SWITCH, swich.isChecked)

                toast("Push Notification ON")

            } else {

                editor?.putBoolean(PREF_SWITCH, false)
                toast("Push Notification Off")

            }
            editor?.commit()
        }

        return rootView

    }

    override fun onClick(v: View?) {
        val buttonIndex = translateIdToIndex(v!!.id)
        context?.getSharedPreferences(Constants.KEY_CUSTOM_THEME_CHECKED, Context.MODE_PRIVATE)!!
            .edit {
                buttonIndex.let {
                    this.putInt(Constants.THEME, it)
                }
            }
        activity?.recreate()
        //       requireActivity() - тормозит
    }

}


private fun translateIdToIndex(id: Int): Int {
    var index = -1
    when (id) {
        R.id.button1 -> index = 1
        R.id.button2 -> index = 2
        R.id.button3 -> index = 3
        R.id.button4 -> index = 4
        R.id.swich -> index = 5
    }
    return index
}

private fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 250)
        show()
    }
}








