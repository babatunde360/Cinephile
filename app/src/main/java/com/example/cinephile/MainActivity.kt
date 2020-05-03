package com.example.cinephile

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.cinephile.utils.isOnline
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                 R.id.nav_movies, R.id.nav_series)
        )
     navView.setupWithNavController(navController)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(isOnline(this)){
                networkStatus.visibility = View.GONE
            }else{
                networkStatus.visibility = View.VISIBLE
            }
        } else {
            TODO("VERSION.SDK_INT < M")
        }

    }


    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()


}