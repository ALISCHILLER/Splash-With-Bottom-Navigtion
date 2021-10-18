package com.msa.splashwithbottomnavigtion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration :AppBarConfiguration

    private lateinit var bottom_navigation:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        navController = findNavController(R.id.nav_host)

//        val navHostFragment = supportFragmentManager.findFragmentById(
//            R.id.nav_host
//        ) as NavHostFragment


//        navController = navHostFragment.navController
        // AppBarConfiguration with the correct top-level destinations
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.fragment_A,
                R.id.fragmentB,
            )
        )
        // This allows NavigationUI to decide what label to show in the action bar
        // By using appBarConfig, it will also determine whether to
        // show the up arrow or drawer menu icon
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        // Set destinations to left and bottom navigation
        bottom_navigation.setupWithNavController(navController)

        // Set visibility for NavigationView & Toolbar
        visibilityNavElements()
    }



    // Allows NavigationUI to support proper up navigation or the drawer layout
// drawer menu, depending on the situation
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun visibilityNavElements() {
        findNavController(R.id.nav_host).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    // toolbar.visibility = View.GONE
                    bottom_navigation.visibility = View.GONE
                }
                else -> {
                    /// toolbar.visibility = View.VISIBLE
                    bottom_navigation.visibility = View.VISIBLE
                }
            }
        }
    }
}