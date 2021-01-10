package br.com.loboneto.heroes.ui.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.loboneto.heroes.R
import br.com.loboneto.heroes.ui.heroes.HeroesActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@LauncherActivity, HeroesActivity::class.java))
            finish()
        }
    }
}