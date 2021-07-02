package com.geekbrains.tests.espresso

import android.content.Context
import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import geekbarains.material.R
import geekbarains.material.ui.api.YesterdayFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class YesterdayFragmentTest {
    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private lateinit var scenario: FragmentScenario<YesterdayFragment>
    @Before
    fun setup() {
        //Для начала сворачиваем все приложения, если у нас что-то запущено
        uiDevice.pressHome()

        //Запускаем наше приложение
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        //Мы уже проверяли Интент на null в предыдущем тесте, поэтому допускаем, что Интент у нас не null
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)//Чистим бэкстек от запущенных ранее Активити
        context.startActivity(intent)

        //Ждем, когда приложение откроется на смартфоне чтобы начать тестировать его элементы
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
  //      scenario = launchFragmentInContainer()
    }
    @Test
    fun fragment_AssertNotNull() {
        TestCase.assertNotNull(scenario)

    }
    @Test
    fun fragmentTextView_IsCompletelyDisplayed() {
        Espresso.onView(withId(R.id.date_earth))
            .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()))
    }
    //проверяем дату YesterdayFragment : проверяет TextView android:text="30.01.2021"
    @Test
    fun fragmentTextView_HasText() {
        Espresso.onView(withId(R.id.date_earth))
            .check(ViewAssertions.matches(withText("30.01.2021")))
    }
    //проверяем дату вчера YesterdayFragment
    @Test
    fun test_OpenSettings() {
        uiDevice.pressHome()
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
        Espresso.onView(withId(R.id.app_bar_fav)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.date_earth))
            .check(ViewAssertions.matches(withText("2021-07-01")))

    }
    companion object {
        private const val TIMEOUT = 5000L
    }
}