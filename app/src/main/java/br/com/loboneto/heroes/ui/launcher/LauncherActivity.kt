package br.com.loboneto.heroes.ui.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.loboneto.heroes.databinding.ActivityLauncherBinding
import br.com.loboneto.heroes.ui.heroes.HeroesActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(DELAY)
            startActivity(Intent(this@LauncherActivity, HeroesActivity::class.java))
            finish()
        }
    }

    companion object {
        private const val DELAY = 3000L
    }
}
