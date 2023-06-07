package com.ujangwahyu.app.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ujangwahyu.app.R
import com.ujangwahyu.app.base.BaseActivity
import com.ujangwahyu.app.databinding.ActivityMainBinding
import com.ujangwahyu.app.utils.hide
import com.ujangwahyu.app.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    override fun setupNavigation(savedInstanceState: Bundle?) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
        onDestinationChanged(navHostFragment.navController)
    }

    private fun onDestinationChanged(navController: NavController){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_git_user -> showNavigation()
                R.id.navigation_my_user -> showNavigation()
                else -> hideNavigation()
            }
        }
    }

    private fun showNavigation() = with(binding){
        bottomNavigation.show()
        bottomAppBar.show()
    }

    private fun hideNavigation() = with(binding) {
        bottomNavigation.hide()
        bottomAppBar.hide()
    }
}