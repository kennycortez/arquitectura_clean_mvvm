package com.example.helper.themes

import android.app.Activity
import android.content.Intent
import com.example.helper.R

object ThemeManager {

    // Save the current theme
    var CURRENT_THEME = Theme.BCP_THEME
    private var themeList: HashMap<Theme, Int> = LinkedHashMap<Theme, Int>()

    fun changeTheme(activity: Activity, theme: Theme) {
        CURRENT_THEME = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity.javaClass))
        activity.overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }

    fun themeList(list: HashMap<Theme, Int>) {
        themeList = list
    }

    fun setTheme(activity: Activity) {
        val currentTheme = themeList[CURRENT_THEME]

        if (currentTheme != null) {
            activity.setTheme(currentTheme)
        } else {
            activity.setTheme(R.style.BCPTheme)
        }
    }
}