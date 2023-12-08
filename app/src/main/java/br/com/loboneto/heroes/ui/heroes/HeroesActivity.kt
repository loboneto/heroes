package br.com.loboneto.heroes.ui.heroes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.databinding.ActivityHeroesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesActivity : AppCompatActivity() {

    private val binding: ActivityHeroesBinding by lazy {
        ActivityHeroesBinding.inflate(layoutInflater)
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.navigation_heroes))
    }

    private val navHost by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_app_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        NavigationUI.setupActionBarWithNavController(this, navHost.navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navHost.navController, appBarConfiguration)
    }
}
