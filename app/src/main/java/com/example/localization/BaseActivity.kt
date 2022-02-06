package com.example.localization

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class BaseActivity : AppCompatActivity() {

    private lateinit var oldPrefLocaleCode : String

    protected val storage : Storage by lazy {
        (application as App).storage
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachBaseContext(newBase: Context) {
        oldPrefLocaleCode = Storage(newBase).getPreferredLocale()
        applyOverrideConfiguration(LocaleUtil.getLocalizedConfiguration(oldPrefLocaleCode))
        super.attachBaseContext(newBase)
    }

    override fun onResume() {
        val currentLocaleCode = Storage(this).getPreferredLocale()
        if(oldPrefLocaleCode != currentLocaleCode){
            recreate() //locale is changed, restart the activity to update
            oldPrefLocaleCode = currentLocaleCode
        }
        super.onResume()
    }
}
