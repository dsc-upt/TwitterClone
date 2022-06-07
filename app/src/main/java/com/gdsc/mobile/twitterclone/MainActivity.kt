package com.gdsc.mobile.twitterclone

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.gdsc.mobile.twitterclone.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNavigation()
        setupNavigationDrawer()
        setupAppBarConfig()
        
        binding.toolbar.setNavigationIcon(R.drawable.bookmarks_icon)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun setupNavigationDrawer() {
        binding.navigationDrawer.setupWithNavController(navController)
    }

    private fun setupAppBarConfig() {
        appBarConfig =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment), binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig)
    }
}