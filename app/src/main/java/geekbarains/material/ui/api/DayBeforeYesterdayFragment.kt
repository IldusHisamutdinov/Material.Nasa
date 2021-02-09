package geekbarains.material.ui.api

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import geekbarains.material.R
import geekbarains.material.ui.picture.PictureOfTheDayData
import geekbarains.material.ui.picture.PictureOfTheDayViewModel
import geekbarains.material.util.getDate
import kotlinx.android.synthetic.main.fragment_day_before_yesterday.*
import kotlinx.android.synthetic.main.fragment_days_ago.*
import kotlinx.android.synthetic.main.fragment_yesterday.*
import kotlinx.android.synthetic.main.main_fragment.*

class DayBeforeYesterdayFragment : Fragment() {
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_before_yesterday, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.sendServerRequestDate(getDate(-2))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataRequest()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> {
                renderData(it)
            })
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                title_mars.text = serverResponseData.title
                date_mars.text = serverResponseData.date
                val spannable = SpannableStringBuilder(serverResponseData.title)
                spannable.setSpan(
                    UnderlineSpan(),0, spannable.length,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                spannable.setSpan(
                    ForegroundColorSpan(Color.RED),
                    0,1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                title_mars.text = spannable
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    if (serverResponseData.mediaType == "video") {
                        webView2.visibility = View.VISIBLE
                        image_mars.visibility = View.INVISIBLE
                        webView2.clearCache(true);
                        webView2.clearHistory();
                        webView2.getSettings().setJavaScriptEnabled(true);
                        webView2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                        webView2.loadUrl(url)
                    } else {
                        //showSuccess()
                        image_mars.load(url) {
                            lifecycle(this@DayBeforeYesterdayFragment)
                            error(R.drawable.ic_load_error_vector)
                            placeholder(R.drawable.ic_no_photo_vector)
                        }
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                Log.d("myLOG", "renderData: ${data.progress}")
            }
            is PictureOfTheDayData.Error -> {
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}
