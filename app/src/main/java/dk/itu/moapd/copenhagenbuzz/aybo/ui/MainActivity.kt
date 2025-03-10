package dk.itu.moapd.copenhagenbuzz.aybo.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dk.itu.moapd.copenhagenbuzz.aybo.R
import dk.itu.moapd.copenhagenbuzz.aybo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // A set of private constants used in this class. (exercise 1)
    private lateinit var binding: ActivityMainBinding
    companion object { // Can be used in Log.d, not used right now
        private val TAG = MainActivity::class.qualifiedName
    }

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false) // exercise 1 code
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        bottomNavigationView = binding.bottomNavigation
        topAppBar = binding.topAppBar

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add -> {
                    AddEventBottomSheet().show(supportFragmentManager, AddEventBottomSheet.TAG)
                    true
                }
                R.id.login -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.logout -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_top_app_bar, menu)
        return true
    }

    // Show login or logout button depending on if is logged in or logged out in the top app bar menu
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.findItem(R.id.login).isVisible = !intent.getBooleanExtra("IsLoggedIn", false)
        menu.findItem(R.id.logout).isVisible = intent.getBooleanExtra("IsLoggedIn", false)
        return true
    }
}