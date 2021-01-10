package br.com.loboneto.heroes.ui.heroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.databinding.ActivityHeroesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesBinding

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setNavigation()
    }

    private fun setBinding() {
        binding = ActivityHeroesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setNavigation() {
        appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_heroes))
        navController = findNavController(R.id.nav_app_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
