package com.bmi.calculator.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.bmi.calculator.R
import com.bmi.calculator.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_LandingTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        bottomNavView = binding.bottomNav

        navController = findNavController(R.id.nav_host_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.historyFragment,
                R.id.profileFragment
            )
        )

        val navHostFragment = supportFragmentManager.fragments.first() as? NavHostFragment

        if (navHostFragment != null) {
            NavigationUI.setupWithNavController(binding.bottomNav, navController)
        }

        navController.addOnDestinationChangedListener { controller, destination: NavDestination, arguments ->
            binding.bottomNav.isVisible = destination.id == R.id.homeFragment ||
                    destination.id == R.id.historyFragment ||
                    destination.id == R.id.profileFragment
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}