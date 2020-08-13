package com.example.cinephile

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.cinephile.utils.isOnline
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPref.registerOnSharedPreferenceChangeListener { sharedPreferences, _ ->
            if(sharedPreferences.getBoolean("night_mode",false)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        setContentView(R.layout.activity_main)

        PreferenceManager.setDefaultValues(this,R.xml.root_preferences,false)


        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_movies, R.id.nav_series
            )
        )
        navView.setupWithNavController(navController)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isOnline(this)) {
                    networkStatus.visibility = View.GONE
                } else {
                    networkStatus.visibility = View.VISIBLE
                }

            } else {
                TODO("VERSION.SDK_INT < M")
            }
    }


    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()


}