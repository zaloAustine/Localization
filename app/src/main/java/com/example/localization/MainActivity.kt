package com.example.localization

import android.content.Intent
import android.os.Bundle
import com.example.localization.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next.setOnClickListener { startActivity(Intent(this,SecondActivity::class.java))}
        setUpSwitch()
    }

    private fun setUpSwitch() {
        binding.languageSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                updateAppLocale("ar")
            } else {
                updateAppLocale("en")
            }
        }
    }

    private fun updateAppLocale(locale: String) {
        storage.setPreferredLocale(locale)
        LocaleUtil.applyLocalizedContext(applicationContext, locale)
    }

}